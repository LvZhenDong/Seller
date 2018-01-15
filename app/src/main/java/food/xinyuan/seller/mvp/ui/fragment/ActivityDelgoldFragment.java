package food.xinyuan.seller.mvp.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.jess.arms.di.component.AppComponent;

import butterknife.BindView;
import food.xinyuan.seller.R;
import food.xinyuan.seller.app.base.AbstractMyBaseFragment;
import food.xinyuan.seller.app.utils.CommonUtils;
import food.xinyuan.seller.di.component.DaggerActivityDelgoldComponent;
import food.xinyuan.seller.di.module.ActivityDelgoldModule;
import food.xinyuan.seller.mvp.contract.ActivityDelgoldContract;
import food.xinyuan.seller.mvp.presenter.ActivityDelgoldPresenter;

import food.xinyuan.seller.R;


public class ActivityDelgoldFragment extends AbstractMyBaseFragment<ActivityDelgoldPresenter> implements ActivityDelgoldContract.View {

    @BindView(R.id.iv_header_left)
    ImageView ivHeaderLeft;
    @BindView(R.id.tv_header_center)
    TextView tvHeaderCenter;

    MaterialDialog mDialog;

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

}
