package food.xinyuan.seller.mvp.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.jess.arms.di.component.AppComponent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import food.xinyuan.seller.R;
import food.xinyuan.seller.app.base.AbstractMyBaseActivity;
import food.xinyuan.seller.app.data.bean.MainItem;
import food.xinyuan.seller.app.data.bean.response.LoginResponse;
import food.xinyuan.seller.app.data.bean.response.ShopStatistics;
import food.xinyuan.seller.app.utils.DataUtils;
import food.xinyuan.seller.di.component.DaggerMainComponent;
import food.xinyuan.seller.di.module.MainModule;
import food.xinyuan.seller.mvp.contract.MainContract;
import food.xinyuan.seller.mvp.presenter.MainPresenter;
import food.xinyuan.seller.mvp.ui.adapter.MainItemAdapter;

@Route(path = "/app/main")
public class MainActivity extends AbstractMyBaseActivity<MainPresenter> implements MainContract.View {

    @BindView(R.id.tv_status_shop)
    TextView tvStatusShop;
    @BindView(R.id.tv_status_bind)
    TextView tvStatusBind;
    @BindView(R.id.iv_head)
    CircleImageView ivHead;
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
    MainItemAdapter mAdapter;


    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mDialog = new MaterialDialog.Builder(this).content(R.string.waiting).
                progress(true, 0).build();

        mLoginResponse = DataUtils.getUser(this);
        if (mLoginResponse != null) {
            tvUserName.setText(DataUtils.getUser(this).getShopName());
        }
        initItemRv();

        mPresenter.getInitData();

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
        mAdapter = new MainItemAdapter(list);
        rv.setLayoutManager(new GridLayoutManager(this, 3));
        rv.setAdapter(mAdapter);
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
    public void getInitDataSuc(ShopStatistics shopStatistics) {
        if (!DataUtils.isEmpty(shopStatistics)) {
            tvTurnover.setText(shopStatistics.getTodayTurnover() + "");
            tvYesterdayTurnover.setText("昨日" + shopStatistics.getYesterdayTurnover());
            tvOrderQuantity.setText(shopStatistics.getTodayOrderQuantity() + "");
            tvYesterdayOrderQuantity.setText("昨日" + shopStatistics.getYesterdayOrderQuantity());
            tvBalance.setText(shopStatistics.getAvailableBalance() + "");
            tvDrawal.setText("可提现" + shopStatistics.getAmountWithdrawal());
        }

    }
}
