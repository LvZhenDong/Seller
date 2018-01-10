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
 * @CreateDate 2018/1/9
 */
public class AnalysisFragment extends AbstractMyBaseFragment {
    @BindView(R.id.iv_header_left)
    ImageView ivHeaderLeft;
    @BindView(R.id.tv_header_center)
    TextView tvHeaderCenter;
    @BindView(R.id.rv_analysis)
    RecyclerView rv;

    BaseQuickAdapter<MainItem, BaseViewHolder> mAdapter;

    public static AnalysisFragment newInstance() {

        AnalysisFragment fragment = new AnalysisFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {

    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_analysis, container, false);
    }

    String[] mTitles = {"新客户", "订单量", "营业额",
            "销售量", "销售量排行"};
    int[] mImgs = {R.drawable.ic_ana1, R.drawable.ic_ana2, R.drawable.ic_ana3,
            R.drawable.ic_ana4, R.drawable.ic_ana5};

    @Override
    public void initData(Bundle savedInstanceState) {
        tvHeaderCenter.setText("营业分析");
        CommonUtils.setBack(this, ivHeaderLeft);

        List<MainItem> list = new ArrayList<>();
        for (int i = 0; i < mTitles.length; i++) {
            list.add(new MainItem(mTitles[i], mImgs[i]));
        }
        mAdapter = new BaseQuickAdapter<MainItem, BaseViewHolder>(R.layout.item_analysis, list) {
            @Override
            protected void convert(BaseViewHolder helper, MainItem item) {
                helper.setText(R.id.tv_analysis, item.getTitle());
                helper.setBackgroundRes(R.id.iv_analysis,item.getImgId());
            }
        };
        rv.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        rv.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position){
                    case 0:
                        start(AnalysisChartFragment.newInstance());
                        break;
                    case 1:

                        break;
                    case 2:

                        break;
                    case 3:

                        break;
                    case 4:

                        break;
                }
            }
        });

    }

    @Override
    public void setData(Object data) {

    }


}
