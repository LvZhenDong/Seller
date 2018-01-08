package food.xinyuan.seller.mvp.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.jess.arms.di.component.AppComponent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import food.xinyuan.seller.R;
import food.xinyuan.seller.app.base.AbstractMyBaseFragment;
import food.xinyuan.seller.app.utils.CommonUtils;
import food.xinyuan.seller.app.utils.ConstantUtil;
import food.xinyuan.seller.mvp.ui.adapter.ItemTitlePagerAdapter;
import food.xinyuan.seller.mvp.ui.widgets.TabEntity;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author lzd
 * @CreateDate 2018/1/8
 */
public class OrderFragment extends AbstractMyBaseFragment {

    @BindView(R.id.iv_header_left)
    ImageView ivHeaderLeft;
    @BindView(R.id.tv_header_center)
    TextView tvHeaderCenter;
    @BindView(R.id.tl_order)
    SlidingTabLayout tlOrder;
    @BindView(R.id.vp_order)
    ViewPager vpOrder;

    private ArrayList<Fragment> fragmentList = new ArrayList<>();

    public static OrderFragment newInstance() {
        OrderFragment fragment = new OrderFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {

    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order, container, false);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        tvHeaderCenter.setText("订单列表");
        CommonUtils.setBack(this, ivHeaderLeft);

        String[] strs={"全部","已接单","配送中","已完成","已取消"};

        fragmentList.add(OrderListFragment.newInstance(""));
        fragmentList.add(OrderListFragment.newInstance(ConstantUtil.ORDER_STATUS_RECEIPT));    //已接单
        fragmentList.add(OrderListFragment.newInstance(ConstantUtil.ORDER_STATUS_SHIPPING));   //配送中
        fragmentList.add(OrderListFragment.newInstance(ConstantUtil.ORDER_STATUS_FINISHED));   //已完成
        fragmentList.add(OrderListFragment.newInstance(ConstantUtil.ORDER_STATUS_CANCELED));   //已取消

        ItemTitlePagerAdapter adapter=new ItemTitlePagerAdapter(getChildFragmentManager(),fragmentList,strs);
        vpOrder.setAdapter(adapter);
        vpOrder.setOffscreenPageLimit(5);
        tlOrder.setViewPager(vpOrder);
    }

    @Override
    public void setData(Object data) {

    }

}
