package food.xinyuan.seller.mvp.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.flyco.tablayout.SlidingTabLayout;
import com.jess.arms.di.component.AppComponent;

import java.util.ArrayList;

import butterknife.BindView;
import food.xinyuan.seller.R;
import food.xinyuan.seller.app.base.AbstractMyBaseFragment;
import food.xinyuan.seller.app.data.bean.response.AppraiseStatistics;
import food.xinyuan.seller.app.utils.CommonUtils;
import food.xinyuan.seller.di.component.DaggerAppraiseManageComponent;
import food.xinyuan.seller.di.module.AppraiseManageModule;
import food.xinyuan.seller.mvp.contract.AppraiseManageContract;
import food.xinyuan.seller.mvp.presenter.AppraiseManagePresenter;
import food.xinyuan.seller.mvp.ui.adapter.ItemTitlePagerAdapter;


public class AppraiseManageFragment extends AbstractMyBaseFragment<AppraiseManagePresenter> implements AppraiseManageContract.View {

    @BindView(R.id.iv_header_left)
    ImageView ivHeaderLeft;
    @BindView(R.id.tv_header_center)
    TextView tvHeaderCenter;
    @BindView(R.id.tv_apprise)
    TextView tvApprise;
    @BindView(R.id.tv_ratio)
    TextView tvRatio;
    @BindView(R.id.tv_all)
    TextView tvAll;
    @BindView(R.id.pb_all)
    ProgressBar pbAll;
    @BindView(R.id.tv_native)
    TextView tvNative;
    @BindView(R.id.pb_native)
    ProgressBar pbNative;
    @BindView(R.id.tl_appraise)
    SlidingTabLayout tlAppraise;
    @BindView(R.id.vp_appraise)
    ViewPager vpAppraise;

    MaterialDialog mDialog;
    private ArrayList<Fragment> fragmentList = new ArrayList<>();


    public static AppraiseManageFragment newInstance() {
        AppraiseManageFragment fragment = new AppraiseManageFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerAppraiseManageComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .appraiseManageModule(new AppraiseManageModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_appraise_manage, container, false);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        tvHeaderCenter.setText("评价管理");
        CommonUtils.setBack(this, ivHeaderLeft);
        mDialog = new MaterialDialog.Builder(getActivity()).content(R.string.waiting).
                progress(true, 0).build();

        String[] strs={"全部评价","已回复","未回复"};

        fragmentList.add(AppraiseListFragment.newInstance(0));
        fragmentList.add(AppraiseListFragment.newInstance(1));
        fragmentList.add(AppraiseListFragment.newInstance(2));
        ItemTitlePagerAdapter adapter=new ItemTitlePagerAdapter(getChildFragmentManager(),fragmentList,strs);
        vpAppraise.setAdapter(adapter);
        vpAppraise.setOffscreenPageLimit(3);
        tlAppraise.setViewPager(vpAppraise);

        mPresenter.getData();
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

    @Override
    public void getAppraiseStatisticsSuc(AppraiseStatistics data) {
        tvApprise.setText(data.getComprehensiveApprise() + "");
        tvRatio.setText("高于商圈" + data.getRatioStr());
        tvAll.setText("近7天评价回复率：" + data.getAllStr());
        tvNative.setText("近7天差评回复率：" + data.getNativeStr());
        pbAll.setProgress(data.getAllProgress());
        pbNative.setProgress(data.getNativeProgress());
    }

}
