package food.xinyuan.seller.mvp.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bigkoo.pickerview.TimePickerView;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;
import food.xinyuan.seller.R;
import food.xinyuan.seller.app.base.AbstractMyBaseFragment;
import food.xinyuan.seller.app.data.bean.request.ChangeBusTime;
import food.xinyuan.seller.app.utils.CommonUtils;
import food.xinyuan.seller.app.utils.XDateUtils;
import food.xinyuan.seller.di.component.DaggerBusTimeComponent;
import food.xinyuan.seller.di.module.BusTimeModule;
import food.xinyuan.seller.mvp.contract.BusTimeContract;
import food.xinyuan.seller.mvp.presenter.BusTimePresenter;


public class BusTimeFragment extends AbstractMyBaseFragment<BusTimePresenter> implements
        BusTimeContract.View {

    @BindView(R.id.iv_header_left)
    ImageView ivHeaderLeft;
    @BindView(R.id.tv_header_center)
    TextView tvHeaderCenter;
    @BindView(R.id.tv_bus_start_time)
    TextView tvBusStartTime;
    @BindView(R.id.rl_bus_start_time)
    RelativeLayout mRlBusStartTime;
    @BindView(R.id.tv_bus_end_time)
    TextView tvBusEndTime;
    @BindView(R.id.rl_bus_end_time)
    RelativeLayout rlBusEndTime;
    @BindView(R.id.tv_save)
    TextView tvSave;

    MaterialDialog mDialog;

    String mStartTime, mEndTime;

    public static BusTimeFragment newInstance(String startTime, String endTime) {
        BusTimeFragment fragment = new BusTimeFragment();
        fragment.mStartTime = startTime;
        fragment.mEndTime = endTime;
        return fragment;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerBusTimeComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .busTimeModule(new BusTimeModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bus_time, container, false);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        tvHeaderCenter.setText("营业时间设置");
        CommonUtils.setBack(this, ivHeaderLeft);

        mDialog = new MaterialDialog.Builder(getActivity()).content(R.string.waiting).
                progress(true, 0).build();
        tvBusStartTime.setText(mStartTime);
        tvBusEndTime.setText(mEndTime);
    }

    @Override
    public void setData(Object data) {

    }

    @OnClick({R.id.rl_bus_start_time, R.id.rl_bus_end_time, R.id.tv_save})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.rl_bus_start_time:
                showTimerPicker(mStartTime, (date, v) -> tvBusStartTime.setText(XDateUtils
                        .date2String
                                (date, "HH:mm")));
                break;
            case R.id.rl_bus_end_time:
                showTimerPicker(mEndTime, (date, v) -> tvBusEndTime.setText(XDateUtils.date2String
                        (date, "HH:mm")));
                break;
            case R.id.tv_save:
                mPresenter.changeBusTime(tvBusStartTime.getText().toString(), tvBusEndTime.getText
                        ().toString());
                break;
        }
    }

    boolean[] types = {false, false, false, true, true, false};

    private void showTimerPicker(String time, TimePickerView.OnTimeSelectListener listener) {
        TimePickerView pvTime = new TimePickerView.Builder(getActivity(), listener).setType(types)
                .build();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(XDateUtils.string2Date(time, "HH:mm"));
        pvTime.setDate(calendar);
        pvTime.show();
    }


    @Override
    public void showLoading() {
        if (mDialog != null) mDialog.show();
    }

    @Override
    public void hideLoading() {
        if (mDialog != null) mDialog.dismiss();
    }

    @Override
    public void changeBusTimeSuc(ChangeBusTime busTime) {
        EventBus.getDefault().post(busTime);
        ArmsUtils.makeText(getActivity(),"修改时间成功");
        pop();
    }
}
