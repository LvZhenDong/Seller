package food.xinyuan.seller.mvp.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;

import java.util.List;

import butterknife.BindView;
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
    @BindView(R.id.rv_goods)
    RecyclerView rvGoods;
    @BindView(R.id.tl_goods_category)
    VerticalTabLayout tlGoodsCategory;

    AppComponent mAppComponent;
    GoodsAdapter mAdapter;

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

        rvGoods.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new GoodsAdapter(R.layout.item_goods, mAppComponent);
        rvGoods.setAdapter(mAdapter);

        mPresenter.getInitData();


    }

    private void initTabLayout() {

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

    @Override
    public void getGoodsCategorySuc(List<GoodsCategory> data) {
        int textColor = getResources().getColor(R.color.tv_name);
        for (GoodsCategory item : data) {
            QTabView view = new QTabView(getActivity());
            view.setTitle(new TabView.TabTitle.Builder()
                    .setContent(item.getGoodsCategoryName())
                    .setTextColor(textColor, textColor)
                    .setTextSize(12)
                    .build());
            tlGoodsCategory.addTab(view);
        }

        tlGoodsCategory.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tab, int position) {
                mPresenter.getGoodsList(data.get(position).getGoodsCategoryId());
            }

            @Override
            public void onTabReselected(TabView tab, int position) {

            }
        });

    }

    @Override
    public void getGoodsSuc(List<Goods> data) {
        mAdapter.setNewData(data);
    }
}
