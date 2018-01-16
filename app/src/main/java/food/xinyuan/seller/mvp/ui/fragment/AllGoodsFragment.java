package food.xinyuan.seller.mvp.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.jess.arms.di.component.AppComponent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import food.xinyuan.seller.R;
import food.xinyuan.seller.app.base.AbstractMyBaseFragment;
import food.xinyuan.seller.app.data.bean.response.Goods;
import food.xinyuan.seller.app.data.bean.response.GoodsCategory;
import food.xinyuan.seller.app.utils.CommonUtils;
import food.xinyuan.seller.di.component.DaggerAllGoodsComponent;
import food.xinyuan.seller.di.module.AllGoodsModule;
import food.xinyuan.seller.mvp.contract.AllGoodsContract;
import food.xinyuan.seller.mvp.presenter.AllGoodsPresenter;
import food.xinyuan.seller.mvp.ui.adapter.GoodsAdapter;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.widget.QTabView;
import q.rorbin.verticaltablayout.widget.TabView;


public class AllGoodsFragment extends AbstractMyBaseFragment<AllGoodsPresenter> implements AllGoodsContract.View {


    @BindView(R.id.iv_header_left)
    ImageView ivHeaderLeft;
    @BindView(R.id.tv_header_center)
    TextView tvHeaderCenter;
    @BindView(R.id.tl_goods_category)
    VerticalTabLayout tlGoodsCategory;

    AppComponent mAppComponent;
    GoodsAdapter mAdapter;
    @BindView(R.id.tv_add_goods)
    TextView tvAddGoods;

    MaterialDialog mDialog;

    public static AllGoodsFragment newInstance() {
        AllGoodsFragment fragment = new AllGoodsFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        mAppComponent = appComponent;
        DaggerAllGoodsComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .allGoodsModule(new AllGoodsModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_all_goods, container, false);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        tvHeaderCenter.setText(R.string.all_goods);
        CommonUtils.setBack(this, ivHeaderLeft);

        mDialog = new MaterialDialog.Builder(getActivity()).content(R.string.waiting).
                progress(true, 0).build();

        mPresenter.getInitData();

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
    public void getGoodsCategorySuc(List<GoodsCategory> data) {
        int textColor = getResources().getColor(R.color.tv_name);
        for (GoodsCategory item : data) {   //初始化竖直TabLayout
            QTabView view = new QTabView(getActivity());
            view.setTitle(new TabView.TabTitle.Builder()
                    .setContent(item.getGoodsCategoryName())
                    .setTextColor(textColor, textColor)
                    .setTextSize(12)
                    .build());
            tlGoodsCategory.addTab(view);
            fragments.add(GoodsListFragment.newInstance(item.getGoodsCategoryId()));
        }

        tlGoodsCategory.setupWithFragment(getChildFragmentManager(), R.id.fl_fragment, fragments);
    }

    List<Fragment> fragments=new ArrayList<>();

    @OnClick(R.id.tv_add_goods)
    public void onViewClicked() {
        start(AddGoodsFragment.newInstance());
    }
}
