package food.xinyuan.seller.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.jess.arms.di.component.AppComponent;

import butterknife.BindView;
import butterknife.OnClick;
import food.xinyuan.seller.R;
import food.xinyuan.seller.app.ARouterPaths;
import food.xinyuan.seller.app.base.AbstractMyBaseFragment;
import food.xinyuan.seller.app.data.bean.response.LoginResponse;
import food.xinyuan.seller.app.utils.CommonUtils;
import food.xinyuan.seller.app.utils.DataUtils;
import food.xinyuan.seller.di.component.DaggerSettingComponent;
import food.xinyuan.seller.di.module.SettingModule;
import food.xinyuan.seller.mvp.contract.SettingContract;
import food.xinyuan.seller.mvp.presenter.SettingPresenter;
import food.xinyuan.seller.mvp.ui.activity.LoginActivity;


public class SettingFragment extends AbstractMyBaseFragment<SettingPresenter> implements SettingContract.View {

    @BindView(R.id.iv_header_left)
    ImageView ivHeaderLeft;
    @BindView(R.id.tv_header_center)
    TextView tvHeaderCenter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rl_account)
    RelativeLayout rlAccount;
    @BindView(R.id.tv_printer)
    TextView tvPrint;
    @BindView(R.id.tv_about_us)
    TextView tvAboutUs;
    @BindView(R.id.rl_version)
    RelativeLayout rlVersion;
    @BindView(R.id.tv_clear_cache)
    TextView tvClearCache;
    @BindView(R.id.tv_service)
    TextView tvService;
    @BindView(R.id.tv_login_out)
    TextView tvLoginOut;
    @BindView(R.id.tv_account)
    TextView tvAccount;
    @BindView(R.id.tv_version)
    TextView tvVersion;

    public static SettingFragment newInstance() {
        SettingFragment fragment = new SettingFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerSettingComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .settingModule(new SettingModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        tvHeaderCenter.setText(R.string.setting);
        CommonUtils.setBack(this, ivHeaderLeft);

        LoginResponse loginResponse= DataUtils.getUser(getActivity());
        if(!DataUtils.isEmpty(loginResponse) && !DataUtils.isEmpty(loginResponse.getSeller())){
            tvAccount.setText(loginResponse.getSeller().getSellerName());
        }
    }

    @Override
    public void setData(Object data) {

    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @OnClick(R.id.iv_header_left)
    public void onViewClicked() {

    }

    @OnClick({R.id.rl_account, R.id.tv_printer, R.id.tv_about_us, R.id.rl_version, R.id.tv_clear_cache,
            R.id.tv_service, R.id.tv_login_out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_account:
                break;
            case R.id.tv_printer:   //打印设置
                start(PrinterSettingFragment.newInstance());
                break;
            case R.id.tv_about_us:
                break;
            case R.id.rl_version:
                break;
            case R.id.tv_clear_cache:
                break;
            case R.id.tv_service:
                break;
            case R.id.tv_login_out:
                //TODO 将LoginActivity改为Fragment
                DataUtils.setToken(getContext(), null);
                DataUtils.setUser(getContext(), null);
                ARouter.getInstance().build(ARouterPaths.LOGIN).navigation();
                getActivity().finish();
                break;
            default:
                break;
        }
    }
}
