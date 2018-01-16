package food.xinyuan.seller.mvp.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.di.component.AppComponent;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import food.xinyuan.seller.R;
import food.xinyuan.seller.app.base.AbstractMyBaseFragment;
import food.xinyuan.seller.app.data.bean.response.SellerInfo;
import food.xinyuan.seller.app.data.event.EventConstant;
import food.xinyuan.seller.app.utils.CommonUtils;
import food.xinyuan.seller.app.utils.DataUtils;
import food.xinyuan.seller.app.utils.ImageLoaderUtils;
import food.xinyuan.seller.app.utils.MySharePreferencesManager;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author lzd
 * @CreateDate 2018/1/16
 */
public class ShopChooseFragment extends AbstractMyBaseFragment {
    @BindView(R.id.iv_header_left)
    ImageView ivHeaderLeft;
    @BindView(R.id.tv_header_center)
    TextView tvHeaderCenter;
    @BindView(R.id.rv)
    RecyclerView rv;

    AppComponent mAppComponent;
    List<SellerInfo.ShopListBean> mList;
    BaseQuickAdapter<SellerInfo.ShopListBean, BaseViewHolder> mAdapter;

    /**
     * 是登录后选择shop，还是home里切换shop
     */
    private boolean mIsAfterLogin = true;

    public static ShopChooseFragment newInstance(List<SellerInfo.ShopListBean> list, boolean afterLogin) {
        ShopChooseFragment fragment = new ShopChooseFragment();
        fragment.mList = list;
        fragment.mIsAfterLogin = afterLogin;
        return fragment;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        mAppComponent = appComponent;
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        tvHeaderCenter.setText("门店选择");
        CommonUtils.setBack(this, ivHeaderLeft);
        ivHeaderLeft.setVisibility(mIsAfterLogin ? View.GONE : View.VISIBLE);

        mAdapter = new BaseQuickAdapter<SellerInfo.ShopListBean, BaseViewHolder>(R.layout.item_choose_shop) {
            @Override
            protected void convert(BaseViewHolder helper, SellerInfo.ShopListBean item) {
                helper.setText(R.id.tv_shop_name, item.getShopName());
                helper.setText(R.id.tv_shop_address, item.getAddress());
            }
        };
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(mAdapter);
        mAdapter.setNewData(mList);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                SellerInfo.ShopListBean item = mAdapter.getItem(position);
                MySharePreferencesManager.getInstance().putString("shopId", item.getShopId() + "");
                if (mIsAfterLogin) {
                    //启动HOME
                    startWithPop(HomeFragment.newInstance());
                } else {
                    pop();
                    //刷新HOME
                    EventBus.getDefault().post(EventConstant.UPDATE_HOME);
                }

            }
        });

    }
}
