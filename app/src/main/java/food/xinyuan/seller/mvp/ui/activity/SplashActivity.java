package food.xinyuan.seller.mvp.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.RxLifecycleUtils;

import java.util.concurrent.TimeUnit;

import food.xinyuan.seller.R;
import food.xinyuan.seller.app.ARouterPaths;
import food.xinyuan.seller.app.base.AbstractMyBaseActivity;
import food.xinyuan.seller.app.utils.CommonUtils;
import food.xinyuan.seller.app.utils.SystemBarHelper;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author lzd
 * @CreateDate 2017/12/26
 */
@Route(path = "/app/splsh")
public class SplashActivity extends AbstractMyBaseActivity {

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public int initView(Bundle savedInstanceState) {
        SystemBarHelper.immersiveStatusBar(this, 0);
        return R.layout.activity_splash;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

        Observable.timer(2000, TimeUnit.MILLISECONDS)
                .compose(RxLifecycleUtils.bindToLifecycle(this))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    if (CommonUtils.isAvailable(getApplicationContext())) {
                        ARouter.getInstance().build(ARouterPaths.MAIN).navigation();
                        finish();
                    } else {
                        new AlertDialog.Builder(this)
                                .setTitle("提示信息")
                                .setMessage("未检测到网络连接，请检查网络是否连接")
                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                    }
                                })
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS)
                                        );
                                    }
                                }).show();
                    }

                });
    }
}
