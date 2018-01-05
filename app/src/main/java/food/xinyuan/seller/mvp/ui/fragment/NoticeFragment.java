package food.xinyuan.seller.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
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
import food.xinyuan.seller.app.data.bean.response.Notice;
import food.xinyuan.seller.app.utils.CommonUtils;
import food.xinyuan.seller.app.utils.ConstantUtil;
import food.xinyuan.seller.app.utils.DialogUtils;
import food.xinyuan.seller.di.component.DaggerNoticeComponent;
import food.xinyuan.seller.di.module.NoticeModule;
import food.xinyuan.seller.mvp.contract.NoticeContract;
import food.xinyuan.seller.mvp.presenter.NoticePresenter;
import food.xinyuan.seller.mvp.ui.widgets.LastDecoration;
import food.xinyuan.seller.mvp.ui.widgets.NormalDecoration;

/**
 * 通知中心
 */
public class NoticeFragment extends AbstractMyBaseFragment<NoticePresenter> implements NoticeContract.View {
    @BindView(R.id.iv_header_left)
    ImageView ivHeaderLeft;
    @BindView(R.id.tv_header_center)
    TextView tvHeaderCenter;
    @BindView(R.id.rv_notice)
    RecyclerView rvNotice;
    @BindView(R.id.srl_notification)
    SmartRefreshLayout srlNotification;

    MaterialDialog mDialog;
    BaseQuickAdapter<Notice, BaseViewHolder> mAdapter;


    public static NoticeFragment newInstance() {
        NoticeFragment fragment = new NoticeFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerNoticeComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .noticeModule(new NoticeModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notice, container, false);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        tvHeaderCenter.setText("通知中心");
        CommonUtils.setBack(this, ivHeaderLeft);

        mDialog = new MaterialDialog.Builder(getActivity()).content(R.string.waiting).
                progress(true, 0).build();

        initRv();
        srlNotification.autoRefresh();
    }

    private void initRv() {
        srlNotification.setRefreshHeader(new ClassicsHeader(getActivity()));
        srlNotification.setOnRefreshListener(refreshlayout -> mPresenter.refreshNotice());
        srlNotification.setOnLoadmoreListener(refreshlayout -> mPresenter.loadMoreNotice());

        mAdapter = new BaseQuickAdapter<Notice, BaseViewHolder>(R.layout.item_notice) {
            @Override
            protected void convert(BaseViewHolder helper, Notice item) {
                helper.setText(R.id.tv_title, item.getTitle());
                helper.setText(R.id.tv_time,item.getCreateTimeStr());
                helper.setText(R.id.tv_content,item.getContent());
                helper.setBackgroundRes(R.id.iv_notice,item.getStatusResId());

                helper.addOnClickListener(R.id.tv_del);
            }
        };
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            DialogUtils.commonChooseDialog(getActivity(), "确定要删除该消息？", (dialog, which) -> {
                mPresenter.delNotice(mAdapter.getData().get(position).getNoticeId(),position);
            }).show();
        });
        rvNotice.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvNotice.addItemDecoration(new NormalDecoration(LinearLayoutManager.VERTICAL));
        rvNotice.setAdapter(mAdapter);
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
    public void getNoticesSuc(List<Notice> data) {
        mAdapter.setNewData(data);
    }

    @Override
    public void loadMoreSuc(List<Notice> data) {
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
        srlNotification.finishLoadmore();
        srlNotification.finishRefresh();
    }

    @Override
    public void delNoticeSuc(int pos) {
        //FIXME 这里删除后，如果上拉加载会造成少一条数据
        mAdapter.remove(pos);
    }
}
