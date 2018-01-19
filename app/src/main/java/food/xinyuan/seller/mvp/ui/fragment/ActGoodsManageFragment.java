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
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.scwang.smartrefresh.layout.util.DensityUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import food.xinyuan.seller.R;
import food.xinyuan.seller.app.base.AbstractMyBaseFragment;
import food.xinyuan.seller.app.data.bean.response.ActGoods;
import food.xinyuan.seller.app.data.bean.response.ActGoodsManage;
import food.xinyuan.seller.app.data.bean.response.Goods;
import food.xinyuan.seller.app.utils.CommonUtils;
import food.xinyuan.seller.app.utils.DataUtils;
import food.xinyuan.seller.di.component.DaggerActGoodsManageComponent;
import food.xinyuan.seller.di.module.ActGoodsManageModule;
import food.xinyuan.seller.mvp.contract.ActGoodsManageContract;
import food.xinyuan.seller.mvp.presenter.ActGoodsManagePresenter;
import food.xinyuan.seller.mvp.ui.adapter.ActGoodsManageAdapter;
import food.xinyuan.seller.mvp.ui.widgets.SurroundDecoration;


public class ActGoodsManageFragment extends AbstractMyBaseFragment<ActGoodsManagePresenter> implements ActGoodsManageContract.View {

    @BindView(R.id.iv_header_left)
    ImageView ivHeaderLeft;
    @BindView(R.id.tv_header_center)
    TextView tvHeaderCenter;
    @BindView(R.id.rv_goods)
    RecyclerView rvGoods;
    @BindView(R.id.tv_save)
    TextView tvSave;

    MaterialDialog mDialog;
    ActGoodsManageAdapter mAdapter;
    List<ActGoods> mActGoods = new ArrayList<>();
    List<ActGoodsManage> mActGoodsManageList = new ArrayList<>();
    Map<String, ActGoods> mMap = new HashMap<>();
    long activityId;

    public static ActGoodsManageFragment newInstance(List<ActGoods> list,long activityId) {
        ActGoodsManageFragment fragment = new ActGoodsManageFragment();
        fragment.mActGoods = list;
        fragment.activityId=activityId;
        return fragment;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerActGoodsManageComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .actGoodsManageModule(new ActGoodsManageModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_act_goods_manage, container, false);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        tvHeaderCenter.setText("商品管理");
        CommonUtils.setBack(this, ivHeaderLeft);
        mDialog = new MaterialDialog.Builder(getActivity()).content(R.string.waiting).
                progress(true, 0).build();

        initRv();
        mPresenter.getGoods();
    }

    private void initRv() {
        rvGoods.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvGoods.addItemDecoration(new SurroundDecoration.NoTop(DensityUtil.dp2px(1)));
        mAdapter = new ActGoodsManageAdapter(R.layout.item_act_goods_manage,activityId);
        rvGoods.setAdapter(mAdapter);
    }


    @Override
    public void showLoading() {
        mDialog.show();
    }

    @Override
    public void hideLoading() {
        mDialog.dismiss();
    }

    @Override
    public void getGoodsSuc(List<Goods> list) {
        if (!DataUtils.isEmpty(mActGoods) && !DataUtils.isEmpty(list)) {

            for (Goods goods : list) {
                ActGoodsManage actGoodsManage = new ActGoodsManage();
                actGoodsManage.setGoods(goods);
                for (ActGoods actGoods : mActGoods) {
                    if (actGoods.getGoodsId() == goods.getGoodsId()) {
                        actGoodsManage.setChecked(true);
                        actGoodsManage.setActGoods(actGoods);
                    }
                }
                mActGoodsManageList.add(actGoodsManage);
            }

            mAdapter.setNewData(mActGoodsManageList);
        }
    }

    @Override
    public void setActGoodsSuc() {
        pop();
    }

    @OnClick(R.id.tv_save)
    public void onViewClicked() {

        for (ActGoodsManage item:mActGoodsManageList){
            if(item.isChecked()){
                ActGoods actGoods=item.getActGoods();
                if(actGoods.getDiscount()<=0 || actGoods.getDiscount() >= 10){
                    ArmsUtils.makeText(getActivity(),"折扣必须小于10并且大于0");

                    return;
                }
                mMap.put(item.getGoods().getGoodsId()+"",item.getActGoods());
            }
        }
        mPresenter.setActGoods(activityId,mMap);
    }
}
