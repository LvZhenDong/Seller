package food.xinyuan.seller.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import food.xinyuan.seller.R;
import food.xinyuan.seller.app.base.AbstractMyBaseActivity;
import food.xinyuan.seller.di.component.DaggerMainComponent;
import food.xinyuan.seller.di.module.MainModule;
import food.xinyuan.seller.mvp.contract.MainContract;
import food.xinyuan.seller.mvp.presenter.MainPresenter;
import food.xinyuan.seller.mvp.ui.fragment.HomeFragment;

@Route(path = "/app/main")
public class MainActivity extends AbstractMyBaseActivity<MainPresenter> implements MainContract.View {

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        if (findFragment(HomeFragment.class) == null) {
            loadRootFragment(R.id.main_content, HomeFragment.newInstance());
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onBackPressedSupport() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            pop();
        } else {
            new MaterialDialog.Builder(this)
                    .title("退出应用")
                    .content("确认退出应用吗？")
                    .positiveText("确定")
                    .negativeText("取消")
                    .positiveColor(getResources().getColor(R.color.colorPrimary))
                    .negativeColor(getResources().getColor(R.color.tv_red))
                    .onAny(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            if (which == DialogAction.POSITIVE) {
                                ArmsUtils.exitApp();
                            } else if (which == DialogAction.NEGATIVE) {

                            }

                        }
                    }).show();
        }
    }
}
