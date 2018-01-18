package food.xinyuan.seller.mvp.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.di.component.AppComponent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import food.xinyuan.seller.R;
import food.xinyuan.seller.app.base.AbstractMyBaseFragment;
import food.xinyuan.seller.app.data.bean.response.GoodsCategory;
import food.xinyuan.seller.app.data.event.GoodsCategoryEvent;
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
    @BindView(R.id.tv_ensure)
    TextView tvEnsure;

    private final static int MAX_CATEGORY = 5;
    MaterialDialog mDialog;
    BaseQuickAdapter<GoodsCategory, BaseViewHolder> mAdapter;

    List<GoodsCategory> mList;
    List<Long> mSelectedIds=new ArrayList<>();

    public static GoodsCategoryFragment newInstance(List<Long> selectedIds) {
        GoodsCategoryFragment fragment = new GoodsCategoryFragment();
        fragment.mSelectedIds=selectedIds;
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
                helper.setText(R.id.rl_goods_category, item.getGoodsCategoryName());
                for (Long id: mSelectedIds) {
                    //将已选择的显示出来
                    if(item.getGoodsCategoryId() == id)
                        item.setChecked(true);
                }

                CheckBox checkBox = helper.getView(R.id.cb_goods_category);
                checkBox.setButtonDrawable(isMax() ? R.drawable.ic_check_disable_selector : R.drawable.ic_check_selector);
                checkBox.setChecked(item.isChecked());
            }
        };
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            GoodsCategory item = mList.get(position);
            if (!isMax() || item.isChecked()) {
                item.setChecked(!item.isChecked());
                mAdapter.notifyDataSetChanged();
            }
        });
        rvChooseGoodsCategory.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvChooseGoodsCategory.setAdapter(mAdapter);

        mPresenter.getInitData();
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
        mList = data;
        mAdapter.setNewData(data);
    }

    private boolean isMax() {
        int count = 0;
        for (GoodsCategory item : mList) {
            if (item.isChecked()) {
                count++;
            }
        }
        return count >= MAX_CATEGORY;

    }

    @OnClick(R.id.tv_ensure)
    public void onViewClicked() {

        EventBus.getDefault().post(getData());
        pop();
    }

    private List<GoodsCategory> getData() {
        if (mList == null) {
            return null;
        } else {
            List<GoodsCategory> list = new ArrayList<>();
            for (GoodsCategory item : mList) {
                if (item.isChecked()) {
                    list.add(item);
                }
            }
            return list;
        }

    }
}
