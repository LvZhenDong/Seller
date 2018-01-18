package food.xinyuan.seller.mvp.ui.fragment;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.jess.arms.di.component.AppComponent;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import food.xinyuan.seller.R;
import food.xinyuan.seller.app.base.AbstractMyBaseFragment;
import food.xinyuan.seller.app.utils.CommonUtils;
import food.xinyuan.seller.app.utils.SpannableStringUtils;
import food.xinyuan.seller.app.utils.TimePickerUtils;
import food.xinyuan.seller.app.utils.XDateUtils;
import food.xinyuan.seller.di.component.DaggerActivityDelgoldComponent;
import food.xinyuan.seller.di.module.ActivityDelgoldModule;
import food.xinyuan.seller.mvp.contract.ActivityDelgoldContract;
import food.xinyuan.seller.mvp.presenter.ActivityDelgoldPresenter;

/**
 * 购满就减
 */
public class ActivityDelgoldFragment extends AbstractMyBaseFragment<ActivityDelgoldPresenter> implements ActivityDelgoldContract.View {

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
    @BindView(R.id.tv_save)
    TextView tvSave;


    MaterialDialog mDialog;
    TimePickerUtils mTimePickerUtils = new TimePickerUtils();
    @BindView(R.id.tv_tips)
    TextView tvTips;

    public static ActivityDelgoldFragment newInstance() {
        ActivityDelgoldFragment fragment = new ActivityDelgoldFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerActivityDelgoldComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .activityDelgoldModule(new ActivityDelgoldModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_activity_delgold, container, false);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        tvHeaderCenter.setText("购满就减");
        CommonUtils.setBack(this, ivHeaderLeft);
        mDialog = new MaterialDialog.Builder(getActivity()).content(R.string.waiting).
                progress(true, 0).build();
        SpannableStringBuilder stringBuilder= SpannableStringUtils.getBuilder(getActivity(),"*").setForegroundColor(getResources().getColor(R.color.tv_red))
                .append(" 活动金额必须由小到大依次设置").create();
        tvTips.setText(stringBuilder);

        tvStartTime.setText(XDateUtils.date2String(Calendar.getInstance().getTime(), "yyyy-MM-dd"));
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

    @OnClick({R.id.rl_start_time, R.id.rl_end_time, R.id.tv_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_start_time:
                mTimePickerUtils.showStart(getActivity(), mTimeCallBack);
                break;
            case R.id.rl_end_time:
                mTimePickerUtils.showEnd(getActivity(), mTimeCallBack);
                break;
            case R.id.tv_save:
                break;
        }
    }

    TimePickerUtils.TimeCallBack mTimeCallBack = new TimePickerUtils.TimeCallBack() {
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
}
