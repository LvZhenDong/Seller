package food.xinyuan.seller.mvp.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bigkoo.pickerview.TimePickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.di.component.AppComponent;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import food.xinyuan.seller.R;
import food.xinyuan.seller.app.base.AbstractMyBaseFragment;
import food.xinyuan.seller.app.data.bean.response.SalesRank;
import food.xinyuan.seller.app.utils.CommonUtils;
import food.xinyuan.seller.app.utils.XDateUtils;
import food.xinyuan.seller.di.component.DaggerSalesRankComponent;
import food.xinyuan.seller.di.module.SalesRankModule;
import food.xinyuan.seller.mvp.contract.SalesRankContract;
import food.xinyuan.seller.mvp.presenter.SalesRankPresenter;


public class SalesRankFragment extends AbstractMyBaseFragment<SalesRankPresenter> implements SalesRankContract.View {

    @BindView(R.id.iv_header_left)
    ImageView ivHeaderLeft;
    @BindView(R.id.tv_header_center)
    TextView tvHeaderCenter;
    @BindView(R.id.rv_sales_rank)
    RecyclerView rvSalesRank;
    @BindView(R.id.tv_date)
    TextView tvDate;

    MaterialDialog mDialog;
    BaseQuickAdapter<SalesRank, BaseViewHolder> mAdapter;
    String mDate;


    public static SalesRankFragment newInstance() {
        SalesRankFragment fragment = new SalesRankFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerSalesRankComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .salesRankModule(new SalesRankModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sales_rank, container, false);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        tvHeaderCenter.setText("销售量排行");
        CommonUtils.setBack(this, ivHeaderLeft);
        mDialog = new MaterialDialog.Builder(getActivity()).content(R.string.waiting).
                progress(true, 0).build();


        mDate = XDateUtils.date2String(Calendar.getInstance().getTime(), "yyyy-MM-dd");
        tvDate.setText(mDate);
        initRv();

        mPresenter.getSalesRank(mDate);
    }

    private void initRv() {
        mAdapter = new BaseQuickAdapter<SalesRank, BaseViewHolder>(R.layout.item_chart) {
            @Override
            protected void convert(BaseViewHolder helper, SalesRank item) {
                helper.setText(R.id.tv_date, item.getGoodsName());
                helper.setText(R.id.tv_count, item.getSalesCount() + "");
            }
        };
        rvSalesRank.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvSalesRank.setAdapter(mAdapter);
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
    public void getSalesRankSuc(List<SalesRank> data) {
        mAdapter.setNewData(data);
    }

    boolean[] types = {true, true, true, false, false, false};

    @OnClick(R.id.tv_date)
    public void onViewClicked() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(XDateUtils.string2Date(mDate,"yyyy-MM-dd"));
        TimePickerView pvTime = new TimePickerView.Builder(getActivity(), new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                mDate = XDateUtils.date2String(date, "yyyy-MM-dd");
                tvDate.setText(mDate);
                mPresenter.getSalesRank(mDate);
            }
        }).setType(types).setRangDate(null,  Calendar.getInstance()).build();
        pvTime.setDate(calendar);
        pvTime.show();

    }
}
