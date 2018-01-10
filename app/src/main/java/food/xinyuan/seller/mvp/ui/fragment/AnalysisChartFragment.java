package food.xinyuan.seller.mvp.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.jess.arms.di.component.AppComponent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import food.xinyuan.seller.R;
import food.xinyuan.seller.app.base.AbstractMyBaseFragment;
import food.xinyuan.seller.app.data.bean.response.NewCustomer;
import food.xinyuan.seller.app.utils.CommonUtils;
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

    MaterialDialog mDialog;

    public static AnalysisChartFragment newInstance() {
        AnalysisChartFragment fragment = new AnalysisChartFragment();
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
        tvHeaderCenter.setText("新客户趋势图");
        CommonUtils.setBack(this, ivHeaderLeft);
        mDialog = new MaterialDialog.Builder(getActivity()).content(R.string.waiting).
                progress(true, 0).build();

        initChart();
        mPresenter.getNewCustomer();
    }
    /**
     * Y轴上的动画时间
     */
    private static final int ANIMATEY_TIME = 2000;

    private void initChart(){
        mLineChart.setNoDataText("没有数据");
        mLineChart.getLegend().setWordWrapEnabled(true);    //label自动换行
        mLineChart.animateY(ANIMATEY_TIME);
        mLineChart.setMaxVisibleValueCount(Integer.MAX_VALUE);

        mLineChart.setDescription("");
        MarkerView markerView=new ChartMarkView(getActivity(),R.layout.view_chart_mark);
        mLineChart.setMarkerView(markerView);
        YAxis rightAxis = mLineChart.getAxisRight();
        rightAxis.setEnabled(false);    //右边Y轴不显示

        YAxis leftAxis = mLineChart.getAxisLeft();
        leftAxis.setAxisMinValue(0.0f); //Y轴从0开始
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisLineWidth(2f);

        XAxis xAxis=mLineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setSpaceBetweenLabels(1);
        xAxis.setGridLineWidth(1f);
        xAxis.setAxisLineWidth(2f);
    }

    @Override
    public void setData(Object data) {

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
        List<String> strs=new ArrayList<>();


        List<Entry> yVals = new ArrayList<>();
        for (NewCustomer item:data
             ) {
            int i=data.indexOf(item);
            yVals.add(new Entry(item.getNewCustomerCount(),i));
            strs.add(XDateUtils.millis2String(item.getFinishDay(),"MM-dd"));
        }
        LineDataSet dataSet=new LineDataSet(yVals,"新客户趋势图");

        dataSet.setColor(getResources().getColor(R.color.tv_red));
        dataSet.setCircleColor(getResources().getColor(R.color.tv_red));
        dataSet.setLineWidth(2f);
        dataSet.setCircleRadius(3f);
        dataSet.setDrawCircleHole(true);  //点是实心的
        dataSet.setValueTextSize(9f);
        dataSet.setDrawFilled(false);  //单纯的line，line下面不覆盖颜色

        LineData lineData=new LineData(strs,dataSet);

        mLineChart.setData(lineData);
        mLineChart.animateY(ANIMATEY_TIME);
        mLineChart.notifyDataSetChanged();
        mLineChart.invalidate();
    }
}
