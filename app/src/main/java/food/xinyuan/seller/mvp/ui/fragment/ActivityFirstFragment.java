package food.xinyuan.seller.mvp.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bigkoo.pickerview.TimePickerView;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import food.xinyuan.seller.R;
import food.xinyuan.seller.app.base.AbstractMyBaseFragment;
import food.xinyuan.seller.app.data.bean.response.ShopActivity;
import food.xinyuan.seller.app.utils.CommonUtils;
import food.xinyuan.seller.app.utils.TimePickerUtils;
import food.xinyuan.seller.app.utils.XDateUtils;
import food.xinyuan.seller.di.component.DaggerActivityFirstComponent;
import food.xinyuan.seller.di.module.ActivityFirstModule;
import food.xinyuan.seller.mvp.contract.ActivityFirstContract;
import food.xinyuan.seller.mvp.presenter.ActivityFirstPresenter;


/**
 * 首单立减
 */
public class ActivityFirstFragment extends AbstractMyBaseFragment<ActivityFirstPresenter> implements ActivityFirstContract.View {

    @BindView(R.id.iv_header_left)
    ImageView ivHeaderLeft;
    @BindView(R.id.tv_header_center)
    TextView tvHeaderCenter;

    MaterialDialog mDialog;
    @BindView(R.id.tv_start_time)
    TextView tvStartTime;
    @BindView(R.id.rl_start_time)
    RelativeLayout rlStartTime;
    @BindView(R.id.tv_end_time)
    TextView tvEndTime;
    @BindView(R.id.rl_end_time)
    RelativeLayout rlEndTime;
    @BindView(R.id.et_reduce)
    EditText etReduce;
    @BindView(R.id.tv_save)
    TextView tvSave;
    TimePickerUtils mTimePickerUtils;

    private ShopActivity mShopActivity;

    public static ActivityFirstFragment newInstance(ShopActivity shopActivity) {
        ActivityFirstFragment fragment = new ActivityFirstFragment();
        fragment.mShopActivity = shopActivity;
        return fragment;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerActivityFirstComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .activityFirstModule(new ActivityFirstModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_activity_first, container, false);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        tvHeaderCenter.setText("首单立减");
        CommonUtils.setBack(this, ivHeaderLeft);
        mDialog = new MaterialDialog.Builder(getActivity()).content(R.string.waiting).
                progress(true, 0).build();


        if (mShopActivity != null) {
            //如果是修改活动，则填入数据
            tvStartTime.setText(XDateUtils.millis2String(mShopActivity.getBeginTime(), "yyyy-MM-dd"));
            Date startDate = XDateUtils.millis2Date(mShopActivity.getBeginTime());
            long endTime = mShopActivity.getEndTime();
            tvEndTime.setText(endTime <= 0 ? "无限制" : XDateUtils.millis2String(mShopActivity.getEndTime(), "yyyy-MM-dd"));
            Date endDate = XDateUtils.millis2Date(mShopActivity.getEndTime());
            mTimePickerUtils = new TimePickerUtils(startDate, endDate);
            if (mShopActivity.getActivityContent() != null) {
                etReduce.setText(mShopActivity.getActivityContent().getMoney() + "");
            }
        } else {
            //如果是添加活动，仅加载红包list
            mTimePickerUtils = new TimePickerUtils();
            tvStartTime.setText(XDateUtils.date2String(Calendar.getInstance().getTime(),
                    "yyyy-MM-dd"));
        }
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

    @OnClick({R.id.rl_start_time, R.id.rl_end_time, R.id.tv_save})
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
                            etReduce.getText().toString().trim());
                } else {
                    mPresenter.updateActivity(tvStartTime.getText().toString().trim(),
                            tvEndTime.getText().toString().trim(),
                            etReduce.getText().toString().trim(), mShopActivity.getActivityId());
                }

                break;
        }
    }

    @Override
    public void addActivitySuc() {
        pop();
    }
}
