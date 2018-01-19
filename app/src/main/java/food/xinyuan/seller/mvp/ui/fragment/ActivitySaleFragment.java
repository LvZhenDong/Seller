package food.xinyuan.seller.mvp.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.jess.arms.di.component.AppComponent;
import com.scwang.smartrefresh.layout.util.DensityUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import food.xinyuan.seller.R;
import food.xinyuan.seller.app.base.AbstractMyBaseFragment;
import food.xinyuan.seller.app.data.bean.response.ActGoods;
import food.xinyuan.seller.app.data.bean.response.ShopActivity;
import food.xinyuan.seller.app.data.event.EventConstant;
import food.xinyuan.seller.app.utils.CommonUtils;
import food.xinyuan.seller.app.utils.Constant;
import food.xinyuan.seller.app.utils.TimePickerUtils;
import food.xinyuan.seller.app.utils.XDateUtils;
import food.xinyuan.seller.di.component.DaggerActivitySaleComponent;
import food.xinyuan.seller.di.module.ActivitySaleModule;
import food.xinyuan.seller.mvp.contract.ActivitySaleContract;
import food.xinyuan.seller.mvp.presenter.ActivitySalePresenter;
import food.xinyuan.seller.mvp.ui.adapter.ActGoodsAdapter;
import food.xinyuan.seller.mvp.ui.widgets.SurroundDecoration;

/**
 * 折扣商品
 */
public class ActivitySaleFragment extends AbstractMyBaseFragment<ActivitySalePresenter> implements ActivitySaleContract.View {

    @BindView(R.id.iv_header_left)
    ImageView ivHeaderLeft;
    @BindView(R.id.tv_header_center)
    TextView tvHeaderCenter;
    @BindView(R.id.tv_start_time)
    TextView tvStartTime;
    @BindView(R.id.rl_start_time)
    RelativeLayout rlStartTime;
    @BindView(R.id.tv_end_time)
    TextView tvEndTime;
    @BindView(R.id.rl_end_time)
    RelativeLayout rlEndTime;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.tv_save)
    TextView tvSave;
    @BindView(R.id.rv_act_goods)
    RecyclerView rvActGoods;
    @BindView(R.id.tv_goods_manage)
    TextView tvGoodsManage;

    MaterialDialog mDialog;
    ShopActivity mShopActivity;
    TimePickerUtils mTimePickerUtils;
    ActGoodsAdapter mAdapter;
    List<ActGoods> mList=new ArrayList<>();

    public static ActivitySaleFragment newInstance(ShopActivity shopActivity) {
        ActivitySaleFragment fragment = new ActivitySaleFragment();
        fragment.mShopActivity = shopActivity;
        return fragment;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerActivitySaleComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .activitySaleModule(new ActivitySaleModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_activity_sale, container, false);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        tvHeaderCenter.setText("折扣商品");
        CommonUtils.setBack(this, ivHeaderLeft);
        mDialog = new MaterialDialog.Builder(getActivity()).content(R.string.waiting).
                progress(true, 0).build();

        init();
        initRv();
    }

    private void initRv() {
        rvActGoods.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvActGoods.addItemDecoration(new SurroundDecoration(DensityUtil.dp2px(1)));
        mAdapter = new ActGoodsAdapter(R.layout.item_act_goods);
        rvActGoods.setAdapter(mAdapter);
    }

    private void init() {
        if (mShopActivity != null) {
            //如果是修改活动，则填入数据
            tvStartTime.setText(XDateUtils.millis2String(mShopActivity.getBeginTime(), "yyyy-MM-dd"));
            Date startDate = XDateUtils.millis2Date(mShopActivity.getBeginTime());
            long endTime = mShopActivity.getEndTime();
            tvEndTime.setText(endTime <= 0 ? "无限制" : XDateUtils.millis2String(mShopActivity.getEndTime(), "yyyy-MM-dd"));
            Date endDate = XDateUtils.millis2Date(mShopActivity.getEndTime());
            mTimePickerUtils = new TimePickerUtils(startDate, endDate);
            if (mShopActivity.getActivityContent() != null) {
                etName.setText(mShopActivity.getActivityName());
            }

            mPresenter.getActGoods(mShopActivity.getActivityId());
        } else {
            rvActGoods.setVisibility(View.GONE);
            tvGoodsManage.setVisibility(View.GONE);
            mTimePickerUtils = new TimePickerUtils();
            tvStartTime.setText(XDateUtils.date2String(Calendar.getInstance().getTime(),
                    "yyyy-MM-dd"));
        }
    }

    TimePickerUtils.TimeCallBack callBack = new TimePickerUtils.TimeCallBack() {
        @Override
        public void onStartTimeSelect(Date startDate) {
            tvStartTime.setText(XDateUtils
                    .date2String(startDate, "yyyy-MM-dd"));
        }

        @Override
        public void onEndTimeSelect(Date endDate) {
            tvEndTime.setText(XDateUtils
                    .date2String(endDate, "yyyy-MM-dd"));
        }
    };

    @OnClick({R.id.rl_start_time, R.id.rl_end_time, R.id.tv_save,R.id.tv_goods_manage})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_start_time:
                hideSoftInput();
                mTimePickerUtils.showStart(getActivity(), callBack);
                break;
            case R.id.rl_end_time:
                hideSoftInput();
                mTimePickerUtils.showEnd(getActivity(), callBack);
                break;
            case R.id.tv_save:
                if (mShopActivity == null) {
                    mPresenter.addActivity(tvStartTime.getText().toString().trim(),
                            tvEndTime.getText().toString().trim(),
                            etName.getText().toString().trim());
                } else {
                    mPresenter.updateActivity(tvStartTime.getText().toString().trim(),
                            tvEndTime.getText().toString().trim(),
                            etName.getText().toString().trim(),mShopActivity.getActivityId());
                }
                break;
            case R.id.tv_goods_manage:
                start(ActGoodsManageFragment.newInstance(mList,mShopActivity.getActivityId()));
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refresh(String event){
        if(TextUtils.equals(event, EventConstant.UPDATE_ACT_GOODS)){
            mPresenter.getActGoods(mShopActivity.getActivityId());
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void showLoading() {
        mDialog.show();
    }

    @Override
    public void hideLoading() {
        mDialog.dismiss();
    }

    @Override
    public void addActivitySuc() {
        pop();
    }

    @Override
    public void getActGoodsSuc(List<ActGoods> list) {
        mList=list;
        mAdapter.setNewData(list);
    }
}
