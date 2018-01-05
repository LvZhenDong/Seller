package food.xinyuan.seller.mvp.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import food.xinyuan.seller.R;
import food.xinyuan.seller.app.ARouterPaths;
import food.xinyuan.seller.app.base.AbstractMyBaseActivity;
import food.xinyuan.seller.app.utils.CommonUtils;
import food.xinyuan.seller.di.component.DaggerLoginComponent;
import food.xinyuan.seller.di.module.LoginModule;
import food.xinyuan.seller.mvp.contract.LoginContract;
import food.xinyuan.seller.mvp.presenter.LoginPresenter;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

@Route(path = "/app/login")
public class LoginActivity extends AbstractMyBaseActivity<LoginPresenter> implements LoginContract.View {

    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.tv_send_ver_code)
    TextView tvSendVerCode;
    @BindView(R.id.et_ver_code)
    EditText etVerCode;
    @BindView(R.id.cb_treaty)
    AppCompatCheckBox cbTreaty;
    @BindView(R.id.tv_treaty)
    TextView tvTreaty;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.tv_help)
    TextView tvHelp;

    MaterialDialog mDialog;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerLoginComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .loginModule(new LoginModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_login;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mDialog = new MaterialDialog.Builder(this).content(R.string.logging).
                progress(true, 0).build();

        cbTreaty.setOnCheckedChangeListener((compoundButton, b) -> tvLogin.setEnabled(b));

        initCountDown();
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

    @OnClick({R.id.tv_send_ver_code, R.id.tv_treaty, R.id.tv_login, R.id.tv_help})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_send_ver_code:
                mPresenter.sendVerCode(etPhone.getText().toString().trim());
                break;
            case R.id.tv_treaty:

                break;
            case R.id.tv_login:
                mPresenter.login(etPhone.getText().toString().trim(), etVerCode.getText().toString().trim());
                break;
            case R.id.tv_help:
                break;
            default:

                break;
        }
    }


    @Override
    public void showSnackbarMsg(String msg, int type) {
        ArmsUtils.snackbarText(msg, type);
    }

    @Override
    public void showSnackbarMsg(int msgId, int type) {
        ArmsUtils.snackbarText(getString(msgId), type);
    }

    @Override
    public void hideKeyboard() {
        CommonUtils.toggleKeyboard(this);
    }

    @Override
    public void sendVerCodeSuc() {
        if (mObservable == null) {
            etVerCode.requestFocus();
            mPresenter.sendVerCode(etPhone.getText().toString().trim());
        } else {
            mObservable.subscribe(mObserver);
        }
    }

    @Override
    public void loginSuc() {
        ArmsUtils.makeText(this,"登录成功");
        ARouter.getInstance().build(ARouterPaths.MAIN).navigation();
        finish();
    }

    private Disposable mDisposable;
    private Observer mObserver;
    private Observable mObservable;
    private static final long MAX_COUNT_TIME = 60;

    public void initCountDown() {
        /**
         * RxJava 方式实现
         */
        //它在指定延迟之后先发射一个零值，然后再按照指定的时间间隔发射递增的数字,设置0延迟，每隔1000毫秒发送一条数据
        mObservable = Observable.interval(1, TimeUnit.SECONDS)
                .take(MAX_COUNT_TIME + 1)//设置总共发送的次数,续1s
                .map(new Function<Long, Long>() {//数据转换 long 值是从0到最大，倒计时需要将值倒置
                    @Override
                    public Long apply(Long aLong) throws Exception {//已经过了一秒
                        return MAX_COUNT_TIME - aLong - 1;
                    }
                })
                .subscribeOn(Schedulers.computation())
                .doOnSubscribe(new Consumer<Disposable>() {//执行计时任务前先将 button 设置为不可点击
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        tvSendVerCode.setEnabled(false);
                        tvSendVerCode.setText(MAX_COUNT_TIME + "秒后重试");
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());//显示放在主线程。


        mObserver = new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                mDisposable = d;
            }

            @Override
            public void onNext(Long value) {
                tvSendVerCode.setText(value + "秒后重试");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                tvSendVerCode.setEnabled(true);
                tvSendVerCode.setText("重新发送");
            }
        };

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mDialog != null){
            mDialog.dismiss();
        }
        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }
}
