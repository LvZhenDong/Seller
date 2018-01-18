package food.xinyuan.seller.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import food.xinyuan.seller.R;
import food.xinyuan.seller.app.base.AbstractMyBaseFragment;
import food.xinyuan.seller.app.data.bean.response.Appraise;
import food.xinyuan.seller.app.data.event.EventConstant;
import food.xinyuan.seller.app.data.event.SellerEvent;
import food.xinyuan.seller.app.utils.Constant;
import food.xinyuan.seller.app.utils.DialogUtils;
import food.xinyuan.seller.di.component.DaggerAppraiseListFragmentComponent;
import food.xinyuan.seller.di.module.AppraiseListFragmentModule;
import food.xinyuan.seller.mvp.contract.AppraiseListContract;
import food.xinyuan.seller.mvp.presenter.AppraiseListPresenter;

import food.xinyuan.seller.mvp.ui.adapter.AppraiseAdapter;


public class AppraiseListFragment extends AbstractMyBaseFragment<AppraiseListPresenter> implements AppraiseListContract.View {

    @BindView(R.id.rv_appraise_list)
    RecyclerView rvList;
    @BindView(R.id.srl_appraise)
    SmartRefreshLayout srlAppraise;

    AppComponent mAppComponent;
    private int mType;
    AppraiseAdapter mAdapter;
    MaterialDialog mDialog;
    /**
     * 如果当前fragment还没有显示出来过，则不eventBus不用刷新该list
     */
    boolean hasShown;

    public static AppraiseListFragment newInstance(int type) {
        AppraiseListFragment fragment = new AppraiseListFragment();
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
        mDialog = new MaterialDialog.Builder(getActivity()).content(R.string.waiting).
                progress(true, 0).build();
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
                        mPresenter.addAppraise(input.toString(),mAdapter.getItem(position),position);
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
        if(mDialog != null)
            mDialog.show();
    }

    @Override
    public void hideLoading() {
        if(mDialog != null)
            mDialog.dismiss();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshList(SellerEvent<Integer> event){
        if(TextUtils.equals(event.getKey(), EventConstant.UPDATE_APPRAISE_LIST)){
            if(event.getData() != mType && hasShown){
                mPresenter.refireshList(mType);
            }
        }
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
        ArmsUtils.snackbarText("没有更多数据", Constant.SNACK_WARING);
    }

    @Override
    public void noData() {
        ArmsUtils.snackbarText("没有数据", Constant.SNACK_WARING);
    }

    @Override
    public void stopLoading() {
        srlAppraise.finishLoadmore();
        srlAppraise.finishRefresh();
    }

    @Override
    public void addAppraiseSuc(int pos) {
        if(mType == 2){
            mAdapter.remove(pos);
        }else {
            mAdapter.notifyItemChanged(pos);
        }
    }
}
