package food.xinyuan.seller.mvp.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.di.component.AppComponent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import food.xinyuan.seller.R;
import food.xinyuan.seller.app.base.AbstractMyBaseFragment;
import food.xinyuan.seller.app.data.bean.MainItem;
import food.xinyuan.seller.app.utils.CommonUtils;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author lzd
 * @CreateDate 2018/1/15
 */
public class ActivityAddFragment extends AbstractMyBaseFragment {
    @BindView(R.id.iv_header_left)
    ImageView ivHeaderLeft;
    @BindView(R.id.tv_header_center)
    TextView tvHeaderCenter;
    @BindView(R.id.rv)
    RecyclerView rv;

    BaseQuickAdapter<MainItem, BaseViewHolder> mAdapter;

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {

    }

    public static ActivityAddFragment newInstance() {

        ActivityAddFragment fragment = new ActivityAddFragment();
        return fragment;
    }
    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_activity_add, container, false);

    }

    int[] mImgs = {R.drawable.bg_activity1, R.drawable.bg_activity2, R.drawable.bg_activity3,
            R.drawable.bg_activity4, R.drawable.bg_activity5};

    @Override
    public void initData(Bundle savedInstanceState) {
        tvHeaderCenter.setText("添加活动");
        CommonUtils.setBack(this, ivHeaderLeft);

        List<MainItem> list = new ArrayList<>();
        for (int i = 0; i < mImgs.length; i++) {
            list.add(new MainItem(mImgs[i]));
        }
        mAdapter = new BaseQuickAdapter<MainItem, BaseViewHolder>(R.layout.item_activity_type, list) {
            @Override
            protected void convert(BaseViewHolder helper, MainItem item) {
                helper.setBackgroundRes(R.id.iv_activity_type,item.getImgId());
            }
        };
        rv.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        rv.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position){
                    case 0:
                        //购满就送
                        start(ActivityComplimentaryFragment.newInstance(null));
                        break;
                    case 1:
                        //首单立减
                        start(ActivityFirstFragment.newInstance(null));
                        break;
                    case 2:
                        //购满就减
                        start(ActivityDelgoldFragment.newInstance(null));
                        break;
                    case 3:
                        //折扣商品
                        start(ActivitySaleFragment.newInstance(null));
                        break;
                    case 4:
                        //其他
                        start(ActivitySpecificFragment.newInstance(null));
                        break;
                }
            }
        });
    }


    @Override
    public void setData(Object data) {

    }
}
