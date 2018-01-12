package food.xinyuan.seller.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import java.util.List;

import butterknife.BindView;
import food.xinyuan.seller.R;
import food.xinyuan.seller.app.base.AbstractMyBaseFragment;
import food.xinyuan.seller.app.data.bean.response.Appraise;
import food.xinyuan.seller.app.utils.CommonUtils;
import food.xinyuan.seller.app.utils.ConstantUtil;
import food.xinyuan.seller.app.utils.DialogUtils;
import food.xinyuan.seller.di.component.DaggerAppraiseListFragmentComponent;
import food.xinyuan.seller.di.module.AppraiseListFragmentModule;
import food.xinyuan.seller.mvp.contract.AppraiseListFragmentContract;
import food.xinyuan.seller.mvp.presenter.AppraiseListFragmentPresenter;

import food.xinyuan.seller.R;
import food.xinyuan.seller.mvp.ui.adapter.AppraiseAdapter;


public class AppraiseListFragmentFragment extends AbstractMyBaseFragment<AppraiseListFragmentPresenter> implements AppraiseListFragmentContract.View {

    @BindView(R.id.rv_appraise_list)
    RecyclerView rvList;
    @BindView(R.id.srl_appraise)
    SmartRefreshLayout srlAppraise;

    AppComponent mAppComponent;
    private int mType;
    AppraiseAdapter mAdapter;
    /**
     * 如果当前fragment还没有显示出来过，则不eventBus不用刷新该list
     */
    boolean hasShown;

    public static AppraiseListFragmentFragment newInstance(int type) {
        AppraiseListFragmentFragment fragment = new AppraiseListFragmentFragment();
        fragment.mType = type;
        return fragment;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        mAppComponent=appComponent;
        DaggerAppraiseListFragmentComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .appraiseListFragmentModule(new AppraiseListFragmentModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_appraise_list, container, false);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        initRv();
    }

    private void initRv(){
        srlAppraise.setRefreshHeader(new ClassicsHeader(getActivity()));
        srlAppraise.setOnRefreshListener(refreshlayout -> mPresenter.refireshList(mType));
        srlAppraise.setOnLoadmoreListener(refreshlayout -> mPresenter.loadMore());

        mAdapter=new AppraiseAdapter(R.layout.item_appraise,mAppComponent);
        //点击回复
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                MaterialDialog dialog=DialogUtils.inputDialog(getActivity(), "回复", "请输入回复内容", InputType.TYPE_CLASS_TEXT, new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {

                    }
                });
                EditText editText=dialog.getInputEditText();
                editText.setSingleLine(false);
                dialog.show();
            }
        });

        rvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvList.setAdapter(mAdapter);
    }

    @Override
    public void setData(Object data) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        hasShown = true;
        srlAppraise.autoRefresh();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void getListSuc(List<Appraise> list) {
        mAdapter.setNewData(list);
    }

    @Override
    public void loadMoreSuc(List<Appraise> data) {
        mAdapter.addData(data);
    }

    @Override
    public void noMoreData() {
        ArmsUtils.snackbarText("没有更多数据", ConstantUtil.SNACK_WARING);
    }

    @Override
    public void noData() {
        ArmsUtils.snackbarText("没有数据", ConstantUtil.SNACK_WARING);
    }

    @Override
    public void stopLoading() {
        srlAppraise.finishLoadmore();
        srlAppraise.finishRefresh();
    }
}
