package food.xinyuan.seller.mvp.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.di.component.AppComponent;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import food.xinyuan.seller.R;
import food.xinyuan.seller.app.base.AbstractMyBaseFragment;
import food.xinyuan.seller.app.data.bean.response.GoodsCategory;
import food.xinyuan.seller.app.utils.CommonUtils;
import food.xinyuan.seller.di.component.DaggerGoodsCategoryComponent;
import food.xinyuan.seller.di.module.GoodsCategoryModule;
import food.xinyuan.seller.mvp.contract.GoodsCategoryContract;
import food.xinyuan.seller.mvp.presenter.GoodsCategoryPresenter;


public class GoodsCategoryFragment extends AbstractMyBaseFragment<GoodsCategoryPresenter> implements GoodsCategoryContract.View {


    @BindView(R.id.iv_header_left)
    ImageView ivHeaderLeft;
    @BindView(R.id.tv_header_center)
    TextView tvHeaderCenter;
    @BindView(R.id.rv_choose_goods_category)
    RecyclerView rvChooseGoodsCategory;

    MaterialDialog mDialog;
    BaseQuickAdapter<GoodsCategory, BaseViewHolder> mAdapter;

    public static GoodsCategoryFragment newInstance() {
        GoodsCategoryFragment fragment = new GoodsCategoryFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerGoodsCategoryComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .goodsCategoryModule(new GoodsCategoryModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_goods_category, container, false);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mDialog = new MaterialDialog.Builder(getActivity()).content(R.string.waiting).
                progress(true, 0).build();

        tvHeaderCenter.setText(R.string.goods_category);
        CommonUtils.setBack(this, ivHeaderLeft);

        mAdapter = new BaseQuickAdapter<GoodsCategory, BaseViewHolder>(R.layout.item_choose_goods_category) {
            @Override
            protected void convert(BaseViewHolder helper, GoodsCategory item) {
                helper.setText(R.id.tv_goods_category, item.getGoodsCategoryName());
            }
        };
        rvChooseGoodsCategory.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvChooseGoodsCategory.setAdapter(mAdapter);

        mPresenter.getInitData();
    }

    @Override
    public void setData(Object data) {

    }


    @Override
    public void showLoading() {
        if (mDialog != null) {
            mDialog.show();
        }
    }

    @Override
    public void hideLoading() {
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }

    @Override
    public void getGoodsCategorySuc(List<GoodsCategory> data) {
        mAdapter.setNewData(data);
    }
}
