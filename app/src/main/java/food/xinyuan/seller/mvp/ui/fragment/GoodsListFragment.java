package food.xinyuan.seller.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import java.util.List;

import butterknife.BindView;
import food.xinyuan.seller.R;
import food.xinyuan.seller.app.base.AbstractMyBaseFragment;
import food.xinyuan.seller.app.data.bean.response.Goods;
import food.xinyuan.seller.app.utils.Constant;
import food.xinyuan.seller.app.utils.DialogUtils;
import food.xinyuan.seller.di.component.DaggerGoodsListComponent;
import food.xinyuan.seller.di.module.GoodsListModule;
import food.xinyuan.seller.mvp.contract.GoodsListContract;
import food.xinyuan.seller.mvp.presenter.GoodsListPresenter;
import food.xinyuan.seller.mvp.ui.adapter.GoodsAdapter;


public class GoodsListFragment extends AbstractMyBaseFragment<GoodsListPresenter> implements GoodsListContract.View {


    @BindView(R.id.rv_goods_list)
    RecyclerView rvGoodsList;
    @BindView(R.id.srl_goods)
    SmartRefreshLayout srlGoods;

    GoodsAdapter mAdapter;
    AppComponent mAppComponent;
    MaterialDialog mDialog;
    int mGroupId;


    public static GoodsListFragment newInstance(int id) {
        GoodsListFragment fragment = new GoodsListFragment();
        fragment.mGroupId = id;
        return fragment;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        mAppComponent = appComponent;
        DaggerGoodsListComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .goodsListModule(new GoodsListModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_goods_list, container, false);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mDialog = new MaterialDialog.Builder(getActivity()).content(R.string.waiting).
                progress(true, 0).build();

        srlGoods.setRefreshHeader(new ClassicsHeader(getActivity()));
        srlGoods.setOnRefreshListener(refreshlayout -> mPresenter.refreshGoods(mGroupId));
        srlGoods.setOnLoadmoreListener(refreshlayout -> mPresenter.loadMoreGoods(mGroupId));

        rvGoodsList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new GoodsAdapter(R.layout.item_goods, mAppComponent);
        rvGoodsList.setAdapter(mAdapter);
        mAdapter.setOnGoodsClickListener(new GoodsAdapter.OnGoodsClickListener() {
            @Override
            public void onEdit(Goods goods) {
                start(AddGoodsFragment.newInstance(goods));
            }

            @Override
            public void onSoldOut(Goods item, int pos) {
                DialogUtils.commonChooseDialog(getActivity(), "确定下架该商品?",
                        (dialog, which) -> mPresenter.soldOutGoods(item,pos)).show();
            }

            @Override
            public void onPutAway(Goods item, int pos) {
                DialogUtils.commonChooseDialog(getActivity(), "确定上架该商品?",
                        (dialog, which) -> mPresenter.putawayGoods(item, pos)).show();
            }

            @Override
            public void onDel(int goodsId,int pos) {
                DialogUtils.commonChooseDialog(getActivity(), "确定删除该商品?",
                        (dialog, which) -> mPresenter.delGoods(goodsId,pos)).show();
            }
        });
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        srlGoods.autoRefresh();
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
    public void getGoodsSuc(List<Goods> data) {
        mAdapter.setNewData(data);
    }

    @Override
    public void loadMoreSuc(List<Goods> data) {
        mAdapter.addData(data);
    }

    @Override
    public void noMoreData() {
        ArmsUtils.snackbarText("没有更多数据", Constant.SNACK_WARING);
    }

    @Override
    public void noData() {
        ArmsUtils.snackbarText("没有数据", Constant.SNACK_WARING);
    }

    @Override
    public void stopLoading() {
        srlGoods.finishLoadmore();
        srlGoods.finishRefresh();
    }

    @Override
    public void soldOutGoodsSuc(int pos) {
        mAdapter.notifyItemChanged(pos);
    }

    @Override
    public void delSuc(int pos) {
        //FIXME 这里删除后，如果上拉加载会造成少一条数据
        mAdapter.remove(pos);
    }

    @Override
    public void putAwaySuc(int pos) {
        mAdapter.notifyItemChanged(pos);
    }

}
