package food.xinyuan.seller.app.config;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.text.TextUtils;

import com.alibaba.android.arouter.launcher.ARouter;
import com.jess.arms.base.App;
import com.jess.arms.base.delegate.AppLifecycles;
import com.jess.arms.di.component.AppComponent;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.view.CropImageView;

import butterknife.ButterKnife;
import food.xinyuan.seller.BuildConfig;
import food.xinyuan.seller.app.api.Api;
import food.xinyuan.seller.app.utils.DataUtils;
import food.xinyuan.seller.app.utils.ImagePickerLoader;
import food.xinyuan.seller.app.utils.MySharePreferencesManager;
import food.xinyuan.seller.app.utils.PrefUtils;
import food.xinyuan.seller.app.utils.ScreenAdaptation;
import me.jessyan.retrofiturlmanager.RetrofitUrlManager;
import me.yokeyword.fragmentation.Fragmentation;
import me.yokeyword.fragmentation.helper.ExceptionHandler;
import timber.log.Timber;


/**
 * Created by f-x on 2017/12/9  10:19
 * Description Application观察
 */

public class MyApplicationLifecycles implements AppLifecycles {
    private Application mApplication;
    public static ImagePicker imagePicker;

    @Override
    public void onCreate(Application application) {
        this.mApplication = application;
        initScreen();
        initTimber();
        initARouter(application);
        initData();
        initFragmentation();
        getImageSelect();
        RetrofitUrlManager.getInstance().putDomain(Api.APP_DOMAIN_NAME, Api.APP_DOMAIN);
        RetrofitUrlManager.getInstance().putDomain(Api.APP_APKNAME, Api.APP_APK);
        initSp();

    }

    private void initSp(){
        MySharePreferencesManager.getInstance().init(mApplication);//secure sp init
    }



    private void initData() {
        AppComponent appComponent = ((App) mApplication).getAppComponent();
        String token = PrefUtils.getInstance(mApplication).getString("token", "");
        if (!TextUtils.isEmpty(token)) {
            try {
                DataUtils.setToken(mApplication, token);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void initScreen() {
        new ScreenAdaptation(mApplication, 720, 1280).register();
    }

    private void initTimber() {
        if (BuildConfig.LOG_DEBUG) {
            //Timber日志打印
            Timber.plant(new Timber.DebugTree());
            ButterKnife.setDebug(true);
        }
    }

    private void initFragmentation() {
        Fragmentation.builder()
                // 设置 栈视图 模式为 悬浮球模式   SHAKE: 摇一摇唤出  默认NONE：隐藏， 仅在Debug环境生效
                .stackViewMode(Fragmentation.BUBBLE)
                // 开发环境：true时，遇到异常："Can not perform this action after onSaveInstanceState!"时，抛出，并Crash;
                // 生产环境：false时，不抛出，不会Crash，会捕获，可以在handleException()里监听到
                .debug(BuildConfig.DEBUG)
                // 生产环境时，捕获上述异常（避免crash），会捕获
                // 建议在回调处上传下面异常到崩溃监控服务器
                .handleException(new ExceptionHandler() {
                    @Override
                    public void onException(Exception e) {
                        // 以Bugtags为例子: 把捕获到的 Exception 传到 Bugtags 后台。
                        // Bugtags.sendException(e);
                    }
                })
                .install();
    }

    private void initARouter(Application application) {
        if (BuildConfig.LOG_DEBUG) {
            ARouter.openDebug();
            ARouter.openLog();
            ARouter.printStackTrace(); // 打印日志的时候打印线程堆栈
        }
        ARouter.init(application);
    }

    /**
     * 设置图片选择器
     */
    private void getImageSelect() {
        imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new ImagePickerLoader());   //设置图片加载器
        imagePicker.setMultiMode(true);
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setCrop(true);        //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true); //是否按矩形区域保存
        imagePicker.setSelectLimit(1);    //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(800);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(800);//保存文件的高度。单位像素
    }


    @Override
    public void attachBaseContext(Context base) {
        MultiDex.install(mApplication);
    }


    @Override
    public void onTerminate(Application application) {

    }
}
