package food.xinyuan.seller.mvp.ui.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import food.xinyuan.seller.R;
import food.xinyuan.seller.app.base.AbstractMyBaseFragment;
import food.xinyuan.seller.app.data.bean.MainItem;
import food.xinyuan.seller.app.data.bean.response.LoginResponse;
import food.xinyuan.seller.app.data.bean.response.ShopDetail;
import food.xinyuan.seller.app.data.bean.response.ShopStatistics;
import food.xinyuan.seller.app.utils.DataUtils;
import food.xinyuan.seller.app.utils.ImageLoaderUtils;
import food.xinyuan.seller.di.component.DaggerHomeComponent;
import food.xinyuan.seller.di.module.HomeModule;
import food.xinyuan.seller.mvp.contract.HomeContract;
import food.xinyuan.seller.mvp.presenter.HomePresenter;


public class HomeFragment extends AbstractMyBaseFragment<HomePresenter> implements HomeContract.View {
    @BindView(R.id.tv_status_shop)
    TextView tvStatusShop;
    @BindView(R.id.tv_status_bind)
    TextView tvStatusBind;
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_check_info)
    TextView tvCheckInfo;
    @BindView(R.id.tv_turnover)
    TextView tvTurnover;
    @BindView(R.id.tv_yesterday_turnover)
    TextView tvYesterdayTurnover;
    @BindView(R.id.tv_order_quantity)
    TextView tvOrderQuantity;
    @BindView(R.id.tv_yesterday_order_quantity)
    TextView tvYesterdayOrderQuantity;
    @BindView(R.id.tv_balance)
    TextView tvBalance;
    @BindView(R.id.tv_drawal)
    TextView tvDrawal;
    @BindView(R.id.rv_main)
    RecyclerView rv;

    MaterialDialog mDialog;

    LoginResponse mLoginResponse;
    BaseQuickAdapter<MainItem, BaseViewHolder> mAdapter;
    AppComponent mAppComponent;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        mAppComponent = appComponent;
        DaggerHomeComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .homeModule(new HomeModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mDialog = new MaterialDialog.Builder(getActivity()).content(R.string.waiting).
                progress(true, 0).build();

        mLoginResponse = DataUtils.getUser(getActivity());
        if (mLoginResponse != null) {
            tvUserName.setText(DataUtils.getUser(getActivity()).getShopName());
        }
        initItemRv();

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

    String[] mTitles = {"商品", "订单", "评价",
            "营业分析", "门店信息", "活动",
            "商品分类", "设置", "通知中心",
            "打印机", "红包"};
    int[] mImgs = {R.drawable.ic_main_goods, R.drawable.ic_main_order, R.drawable.ic_main_evaluate,
            R.drawable.ic_main_analyze, R.drawable.ic_main_shop, R.drawable.ic_main_activity,
            R.drawable.ic_main_type, R.drawable.ic_main_settting, R.drawable.ic_main_msg_center,
            R.drawable.ic_main_settting, R.drawable.ic_main_red_package};

    private void initItemRv() {
        List<MainItem> list = new ArrayList<>();
        for (int i = 0; i < mTitles.length; i++) {
            list.add(new MainItem(mTitles[i], mImgs[i]));
        }
        mAdapter = new BaseQuickAdapter<MainItem, BaseViewHolder>(R.layout.item_main, list) {
            @Override
            protected void convert(BaseViewHolder helper, MainItem item) {
                helper.setText(R.id.tv_title, item.getTitle());
                TextView tv = helper.getView(R.id.tv_title);
                Drawable top = ContextCompat.getDrawable(mContext, item.getImgId());
                tv.setCompoundDrawablesWithIntrinsicBounds(null, top, null, null);
            }
        };
        rv.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        rv.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    //全部商品
                    case 0:
                        start(AllGoodsFragment.newInstance());
                        break;
                    case 1:

                        break;
                    case 2:

                        break;
                    case 3:

                        break;
                    case 4:

                        break;
                    case 5:

                        break;
                    case 6:

                        break;
                    //设置
                    case 7:
                        start(SettingFragment.newInstance());
                        break;
                    case 8:

                        break;
                    case 9:

                        break;
                    case 10:

                        break;
                    case 11:

                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public void getShopStatisticsSuc(ShopStatistics shopStatistics) {
        if (!DataUtils.isEmpty(shopStatistics)) {
            tvTurnover.setText(shopStatistics.getTodayTurnover() + "");
            tvYesterdayTurnover.setText("昨日" + shopStatistics.getYesterdayTurnover());
            tvOrderQuantity.setText(shopStatistics.getTodayOrderQuantity() + "");
            tvYesterdayOrderQuantity.setText("昨日" + shopStatistics.getYesterdayOrderQuantity());
            tvBalance.setText(shopStatistics.getAvailableBalance() + "");
            tvDrawal.setText("可提现" + shopStatistics.getAmountWithdrawal());
        }

    }

    @Override
    public void getShopDetailSuc(ShopDetail shopDetail) {
        ImageLoaderUtils.loadCirImg(mAppComponent,shopDetail.getShopFaceUrl(),ivHead);
    }

    @OnClick({R.id.tv_status_shop, R.id.tv_status_bind, R.id.tv_check_info})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_status_shop:
                break;
            case R.id.tv_status_bind:
                break;
            case R.id.tv_check_info:
                start(ShopInfoFragment.newInstance());
                break;
        }
    }
}
