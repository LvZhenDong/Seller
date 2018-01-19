package food.xinyuan.seller.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jess.arms.di.component.AppComponent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import food.xinyuan.seller.R;
import food.xinyuan.seller.app.base.AbstractMyBaseFragment;
import food.xinyuan.seller.app.data.bean.response.ShopActivity;
import food.xinyuan.seller.app.data.event.EventConstant;
import food.xinyuan.seller.app.utils.CommonUtils;
import food.xinyuan.seller.app.utils.DialogUtils;
import food.xinyuan.seller.di.component.DaggerActivityManageComponent;
import food.xinyuan.seller.di.module.ActivityManageModule;
import food.xinyuan.seller.mvp.contract.ActivityManageContract;
import food.xinyuan.seller.mvp.presenter.ActivityManagePresenter;
import food.xinyuan.seller.mvp.ui.adapter.ActivityListAdapter;

/**
 * 活动管理
 */
public class ActivityManageFragment extends AbstractMyBaseFragment<ActivityManagePresenter> implements ActivityManageContract.View {

    @BindView(R.id.iv_header_left)
    ImageView ivHeaderLeft;
    @BindView(R.id.tv_header_center)
    TextView tvHeaderCenter;
    @BindView(R.id.rv_activity)
    RecyclerView rvActivity;
    @BindView(R.id.tv_header_right)
    TextView tvHeaderRight;

    MaterialDialog mDialog;
    ActivityListAdapter mAdapter;

    public static ActivityManageFragment newInstance() {
        ActivityManageFragment fragment = new ActivityManageFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerActivityManageComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .activityManageModule(new ActivityManageModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_activity_manage, container, false);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        tvHeaderCenter.setText("活动管理");
        CommonUtils.setBack(this, ivHeaderLeft);
        tvHeaderRight.setVisibility(View.VISIBLE);
        tvHeaderRight.setTextSize(26);
        tvHeaderRight.setText("+");


        mDialog = new MaterialDialog.Builder(getContext()).content(R.string.waiting).
                progress(true, 0).build();

        initRv();
        mPresenter.getList();
    }

    private void initRv() {
        mAdapter = new ActivityListAdapter(R.layout.item_activity);
        rvActivity.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvActivity.setAdapter(mAdapter);
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_del:
                        DialogUtils.commonChooseDialog(getActivity(), "确定要删除该活动？", new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                mPresenter.delActivity(mAdapter.getItem(position).getActivityId(), position);
                            }
                        }).show();
                        break;
                    case R.id.rl_activity_content:
                        //这里因为swipeLayout的侧滑事件要触发BaseQuickAdapter的item click事件，所以将item点击事件写在了这里
                        ShopActivity item = mAdapter.getItem(position);
                        switch (item.getType()) {

                            case 0:
                                //购满就送
                                start(ActivityComplimentaryFragment.newInstance(item));
                                break;
                            case 1:

                                break;
                            case 2:
                                //购满就减
                                start(ActivityDelgoldFragment.newInstance(item));
                                break;
                            case 3:
                                //首单立减
                                start(ActivityFirstFragment.newInstance(item));
                                break;
                            case 4:
                                //其他活动
                                start(ActivitySpecificFragment.newInstance(item));
                                break;
                        }
                        break;
                }


            }
        });
    }

    @Override
    public void setData(Object data) {

    }

    @Subscribe
    public void update(String event) {
        if (TextUtils.equals(event, EventConstant.UPDATE_ACTIVITY_LIST)) {
            mPresenter.refresh();
        }
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

    @OnClick(R.id.tv_header_right)
    public void onViewClicked() {
        start(ActivityAddFragment.newInstance());
    }

    @Override
    public void getListSuc(List<ShopActivity> list) {
        mAdapter.setNewData(list);
    }

    @Override
    public void delActivitySuc(int pos) {
        mAdapter.remove(pos);
    }
}
