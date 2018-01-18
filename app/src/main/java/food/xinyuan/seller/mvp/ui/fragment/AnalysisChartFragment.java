package food.xinyuan.seller.mvp.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.jess.arms.di.component.AppComponent;
import com.scwang.smartrefresh.layout.util.DensityUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import food.xinyuan.seller.R;
import food.xinyuan.seller.app.base.AbstractMyBaseFragment;
import food.xinyuan.seller.app.data.bean.response.Goods;
import food.xinyuan.seller.app.data.bean.response.NewCustomer;
import food.xinyuan.seller.app.utils.CommonUtils;
import food.xinyuan.seller.app.utils.DataUtils;
import food.xinyuan.seller.app.utils.XDateUtils;
import food.xinyuan.seller.di.component.DaggerAnalysisChartComponent;
import food.xinyuan.seller.di.module.AnalysisChartModule;
import food.xinyuan.seller.mvp.contract.AnalysisChartContract;
import food.xinyuan.seller.mvp.presenter.AnalysisChartPresenter;
import food.xinyuan.seller.mvp.ui.widgets.ChartMarkView;


public class AnalysisChartFragment extends AbstractMyBaseFragment<AnalysisChartPresenter> implements AnalysisChartContract.View {

    @BindView(R.id.iv_header_left)
    ImageView ivHeaderLeft;
    @BindView(R.id.tv_header_center)
    TextView tvHeaderCenter;
    @BindView(R.id.lineChart)
    LineChart mLineChart;
    @BindView(R.id.tv_count_tips)
    TextView tvCountTips;
    @BindView(R.id.rv_chart)
    RecyclerView rvChart;
    @BindView(R.id.tv_tips)
    TextView tvTips;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.ll_search)
    LinearLayout llSearch;


    MaterialDialog mDialog;
    BaseQuickAdapter<NewCustomer, BaseViewHolder> mAdapter;
    ChartInfo mChartInfo;

    //搜索popupWindow
    PopupWindow mPw;
    BaseQuickAdapter<Goods,BaseViewHolder> mSearchResultAdapter;

    public static AnalysisChartFragment newInstance(int type) {
        AnalysisChartFragment fragment = new AnalysisChartFragment();
        switch (type) {
            case 0:
                fragment.mChartInfo = new ChartInfo(type, R.color.tv_red, "新客户趋势图",
                        "新客户量", "新客户：第一次再本店进行下单的客户");
                break;
            case 1:
                fragment.mChartInfo = new ChartInfo(type, R.color.colorPrimary, "订单量趋势图",
                        "订单量", "订单量：只统计已完成的订单数量");
                break;
            case 2:
                fragment.mChartInfo = new ChartInfo(type, R.color.bg_yellow, "营业额趋势图",
                        "营业额", "营业额：只统计已完成的订单的订单金额");
                break;
            case 3:
                fragment.mChartInfo = new ChartInfo(type, R.color.blue, "销售量趋势图",
                        "销售量", "销售量：只统计已完成的订单的商品数量");
                break;

        }
        return fragment;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerAnalysisChartComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .analysisChartModule(new AnalysisChartModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_analysis_chart, container, false);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        tvHeaderCenter.setText(mChartInfo.getTitle());
        CommonUtils.setBack(this, ivHeaderLeft);
        mDialog = new MaterialDialog.Builder(getActivity()).content(R.string.waiting).
                progress(true, 0).build();

        tvCountTips.setText(mChartInfo.getCountStr());
        initPw();
        initChart();
        initRv();
        tvTips.setText(mChartInfo.getTips());
        switch (mChartInfo.getType()) {
            case 0:
                mPresenter.getNewCustomer();
                break;
            case 1:
                mPresenter.getOrderQuantity();
                break;
            case 2:
                mPresenter.getTurnover();
                break;
            case 3:
                llSearch.setVisibility(View.VISIBLE);
                mPresenter.getGoodsSales(null);
                break;
        }

    }

    /**
     * Y轴上的动画时间
     */
    private static final int ANIMATEY_TIME = 2000;

    private void initChart() {
        mLineChart.setNoDataText("没有数据");
        mLineChart.getLegend().setWordWrapEnabled(true);    //label自动换行
        mLineChart.animateY(ANIMATEY_TIME);
        mLineChart.setMaxVisibleValueCount(Integer.MAX_VALUE);

        mLineChart.setDescription("");

        YAxis rightAxis = mLineChart.getAxisRight();
        rightAxis.setEnabled(false);    //右边Y轴不显示

        YAxis leftAxis = mLineChart.getAxisLeft();
        leftAxis.setAxisMinValue(0.0f); //Y轴从0开始
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisLineWidth(2f);

        XAxis xAxis = mLineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setSpaceBetweenLabels(1);
        xAxis.setGridLineWidth(1f);
        xAxis.setAxisLineWidth(2f);
    }

    private void initRv() {
        mAdapter = new BaseQuickAdapter<NewCustomer, BaseViewHolder>(R.layout.item_chart) {
            @Override
            protected void convert(BaseViewHolder helper, NewCustomer item) {
                helper.setText(R.id.tv_date, XDateUtils.millis2String(item.getFinishDay(), "yyyy/MM/dd"));
                //因为有的接口返回值是int,有的是float
                if (mChartInfo.isInt()) {
                    helper.setText(R.id.tv_count, item.getIntData() + "");
                } else {
                    helper.setText(R.id.tv_count, item.getFloatData() + "");
                }
            }
        };
        rvChart.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvChart.setAdapter(mAdapter);

    }

    /**
     * 初始化搜索goods结果popupWindow
     */
    private void initPw() {
        //TODO pw宽带优化
        View view = getLayoutInflater().inflate(R.layout.view_search_pw, null, false);
        mPw = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, DensityUtil.dp2px(200), true);

        RecyclerView rvResult=view.findViewById(R.id.rv_result);
        rvResult.setLayoutManager(new LinearLayoutManager(getActivity()));
        mSearchResultAdapter=new BaseQuickAdapter<Goods, BaseViewHolder>(R.layout.item_search_goods_result) {
            @Override
            protected void convert(BaseViewHolder helper, Goods item) {
                helper.setText(R.id.tv_result,item.getGoodsName());
            }
        };
        mSearchResultAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mPw.dismiss();
                Goods item=mSearchResultAdapter.getItem(position);
                etSearch.setText(item.getGoodsName());
                mPresenter.getGoodsSales(item.getGoodsId());
            }
        });
        rvResult.setAdapter(mSearchResultAdapter);
    }


    @Override
    public void showLoading() {
        if (mDialog != null)
            mDialog.show();
    }

    @Override
    public void hideLoading() {
        if (mDialog != null)
            mDialog.dismiss();
    }

    @Override
    public void getNewCustomerSuc(List<NewCustomer> data) {
        mAdapter.setNewData(data);

        List<String> strs = new ArrayList<>();
        List<Entry> yVals = new ArrayList<>();
        for (NewCustomer item : data) {
            int i = data.indexOf(item);
            //因为有的接口返回值是int,有的是float
            yVals.add(new Entry(mChartInfo.isInt() ? item.getIntData() : item.getFloatData(), i));
            strs.add(XDateUtils.millis2String(item.getFinishDay(), "MM-dd"));
        }
        LineDataSet dataSet = new LineDataSet(yVals, mChartInfo.getTitle());
        setDataSetStyle(dataSet);

        LineData lineData = new LineData(strs, dataSet);

        MarkerView markerView = new ChartMarkView(getActivity(), R.layout.view_chart_mark, strs, mChartInfo.getCountStr(), mLineChart.getHeight());
        mLineChart.setMarkerView(markerView);
        mLineChart.setData(lineData);
        mLineChart.notifyDataSetChanged();
        mLineChart.invalidate();

    }

    @Override
    public void searchGoodsSuc(List<Goods> result) {
        if(mPw == null){
            initPw();
        }
        mPw.showAsDropDown(llSearch,200,10);
        mSearchResultAdapter.setNewData(result);
    }

    private void setDataSetStyle(LineDataSet dataSet) {
        dataSet.setColor(getResources().getColor(mChartInfo.getColorId()));
        dataSet.setCircleColor(getResources().getColor(mChartInfo.getColorId()));
        dataSet.setLineWidth(2f);
        dataSet.setCircleRadius(3f);
        dataSet.setDrawCircleHole(true);  //点是实心的
        dataSet.setValueTextSize(9f);
        dataSet.setDrawFilled(false);  //单纯的line，line下面不覆盖颜色
    }


    @OnClick(R.id.iv_search)
    public void onViewClicked() {
        mPresenter.searchGoods(etSearch.getText().toString().trim());
    }

    public static class ChartInfo {
        public ChartInfo(int type, int colorId, String title, String countStr, String tips) {
            this.type = type;
            this.colorId = colorId;
            this.title = title;
            this.countStr = countStr;
            this.tips = tips;
        }

        int type;
        int colorId;
        String title, countStr, tips;

        public boolean isInt() {
            return type == 0 || type == 1 || type == 3;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getColorId() {
            return colorId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCountStr() {
            return countStr;
        }

        public String getTips() {
            return tips;
        }

    }

}
