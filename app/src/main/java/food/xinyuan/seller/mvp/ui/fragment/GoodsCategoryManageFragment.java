package food.xinyuan.seller.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.di.component.AppComponent;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import food.xinyuan.seller.R;
import food.xinyuan.seller.app.base.AbstractMyBaseFragment;
import food.xinyuan.seller.app.data.bean.response.GoodsCategory;
import food.xinyuan.seller.app.utils.CommonUtils;
import food.xinyuan.seller.app.utils.DialogUtils;
import food.xinyuan.seller.di.component.DaggerGoodsCategoryManageComponent;
import food.xinyuan.seller.di.module.GoodsCategoryManageModule;
import food.xinyuan.seller.mvp.contract.GoodsCategoryManageContract;
import food.xinyuan.seller.mvp.presenter.GoodsCategoryManagePresenter;
import food.xinyuan.seller.mvp.ui.widgets.LastDecoration;

/**
 * 商品分类
 */
public class GoodsCategoryManageFragment extends AbstractMyBaseFragment<GoodsCategoryManagePresenter> implements GoodsCategoryManageContract.View {


    @BindView(R.id.iv_header_left)
    ImageView ivHeaderLeft;
    @BindView(R.id.tv_header_center)
    TextView tvHeaderCenter;
    @BindView(R.id.rv_category)
    RecyclerView rvCategory;
    @BindView(R.id.tv_add)
    TextView tvAdd;
    @BindView(R.id.et_add)
    EditText etAdd;

    MaterialDialog mDialog;
    BaseQuickAdapter<GoodsCategory, BaseViewHolder> mAdapter;


    public static GoodsCategoryManageFragment newInstance() {
        GoodsCategoryManageFragment fragment = new GoodsCategoryManageFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerGoodsCategoryManageComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .goodsCategoryManageModule(new GoodsCategoryManageModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_goods_category_manage, container, false);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        tvHeaderCenter.setText("商品分类");
        CommonUtils.setBack(this, ivHeaderLeft);

        mDialog = new MaterialDialog.Builder(getActivity()).content(R.string.waiting).
                progress(true, 0).build();

        initRv();
        mPresenter.getGoodsCategory();
    }

    private void initRv() {
        mAdapter = new BaseQuickAdapter<GoodsCategory, BaseViewHolder>(R.layout.item_goods_category) {
            @Override
            protected void convert(BaseViewHolder helper, GoodsCategory item) {
                helper.setText(R.id.tv_category, item.getGoodsCategoryName());
                helper.addOnClickListener(R.id.tv_del);
            }
        };
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            DialogUtils.commonChooseDialog(getActivity(), "确定要删除该分类？", new MaterialDialog.SingleButtonCallback() {
                @Override
                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                    mPresenter.delGoodsCategory(mAdapter.getData().get(position).getGoodsCategoryId() + "", position);
                }
            }).show();
        });
        rvCategory.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvCategory.addItemDecoration(new LastDecoration(LinearLayoutManager.VERTICAL));
        rvCategory.setAdapter(mAdapter);
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
        mAdapter.setNewData(data);
    }

    @Override
    public void addGoodsCategorySuc() {
        etAdd.setText("");
    }

    @Override
    public void delGoodsCategorySuc(int pos) {
        mAdapter.remove(pos);
    }

    @OnClick(R.id.tv_add)
    public void onViewClicked() {
        mPresenter.addGoodsCategory(etAdd.getText().toString().trim());
    }
}
