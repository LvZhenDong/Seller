package food.xinyuan.seller.mvp.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import food.xinyuan.seller.R;
import food.xinyuan.seller.app.base.AbstractMyBaseActivity;
import food.xinyuan.seller.app.data.bean.response.SellerInfo;
import food.xinyuan.seller.app.utils.DialogUtils;
import food.xinyuan.seller.di.component.DaggerMainComponent;
import food.xinyuan.seller.di.module.MainModule;
import food.xinyuan.seller.mvp.contract.MainContract;
import food.xinyuan.seller.mvp.presenter.MainPresenter;
import food.xinyuan.seller.mvp.ui.fragment.HomeFragment;
import food.xinyuan.seller.mvp.ui.fragment.ShopChooseFragment;

@Route(path = "/app/main")
public class MainActivity extends AbstractMyBaseActivity<MainPresenter> implements MainContract
        .View {
    @Autowired(name = "chooseShop")
    boolean chooseShop;
    @Autowired(name = "shopList")
    String mListStr;

    List<SellerInfo.ShopListBean> mList;

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
        ARouter.getInstance().inject(this);

        if(!TextUtils.isEmpty(mListStr)){
            Gson gson=new Gson();
            Type type = new TypeToken <List<SellerInfo.ShopListBean>>(){}.getType();
            mList = gson.fromJson(mListStr,type);
        }

        if(chooseShop){
            loadRootFragment(R.id.main_content, ShopChooseFragment.newInstance(mList,true));
        }else {
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
        if (getSupportFragmentManager().getBackStackEntryCount() > 1)
            pop();
        else
            DialogUtils.commonChooseDialog(this, "确认退出应用吗？",
                    (dialog, which) -> ArmsUtils.exitApp()).show();

    }
}
