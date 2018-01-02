package food.xinyuan.seller.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import food.xinyuan.seller.R;
import food.xinyuan.seller.app.base.AbstractMyBaseFragment;
import food.xinyuan.seller.app.data.bean.request.AddGoods;
import food.xinyuan.seller.app.data.bean.response.GoodsCategory;
import food.xinyuan.seller.app.data.event.GoodsCategoryEvent;
import food.xinyuan.seller.app.utils.CommonUtils;
import food.xinyuan.seller.app.utils.ImageLoaderUtils;
import food.xinyuan.seller.app.utils.L;
import food.xinyuan.seller.di.component.DaggerAddGoodsComponent;
import food.xinyuan.seller.di.module.AddGoodsModule;
import food.xinyuan.seller.mvp.contract.AddGoodsContract;
import food.xinyuan.seller.mvp.presenter.AddGoodsPresenter;


public class AddGoodsFragment extends AbstractMyBaseFragment<AddGoodsPresenter>
        implements AddGoodsContract.View {

    public final static int REQUEST_IMAGE_PICKER = 24;

    @BindView(R.id.iv_header_left)
    ImageView ivHeaderLeft;
    @BindView(R.id.tv_header_center)
    TextView tvHeaderCenter;
    @BindView(R.id.rl_goods_category)
    RelativeLayout rlGoodsCategory;
    @BindView(R.id.tv_goods_spec)
    RelativeLayout tvGoodsSpec;
    @BindView(R.id.rl_goods_property)
    RelativeLayout tvGoodsProperty;
    @BindView(R.id.tv_goods_category)
    TextView tvGoodsCategory;
    @BindView(R.id.iv_add_img)
    ImageView ivAddImg;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.rb_off)
    AppCompatRadioButton rbOff;
    @BindView(R.id.rb_on)
    AppCompatRadioButton rbOn;
    @BindView(R.id.rg_status)
    RadioGroup rgStatus;
    @BindView(R.id.et_brief)
    EditText etBrief;
    @BindView(R.id.rl_goods_brief)
    RelativeLayout rlGoodsBrief;
    @BindView(R.id.tv_save)
    TextView tvSave;
    @BindView(R.id.rv_spec)
    RecyclerView rvSpec;

    AppComponent mAppComponent;
    MaterialDialog mDialog;
    BaseQuickAdapter<AddGoods.AddSpecsBean,BaseViewHolder> mSpecAdapter;
    List<AddGoods.AddSpecsBean> mSpecList=new ArrayList<>();

    public static AddGoodsFragment newInstance() {
        AddGoodsFragment fragment = new AddGoodsFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        mAppComponent = appComponent;
        DaggerAddGoodsComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .addGoodsModule(new AddGoodsModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_goods, container, false);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        tvHeaderCenter.setText(R.string.add_goods);
        CommonUtils.setBack(this, ivHeaderLeft);

        rbOn.setChecked(true);
        mDialog = new MaterialDialog.Builder(getActivity()).content(R.string.waiting).
                progress(true, 0).build();

        mSpecAdapter=new BaseQuickAdapter(R.layout.item_spec) {
            @Override
            protected void convert(BaseViewHolder helper, Object item) {

            }
        };
        mSpecAdapter.setNewData(mSpecList);
        rvSpec.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvSpec.setAdapter(mSpecAdapter);

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

    @OnClick({R.id.rl_goods_category, R.id.tv_goods_spec, R.id.iv_add_img, R.id.rl_goods_property,
            R.id.tv_save})
    public void onViewClicked(View view) {
        CommonUtils.hideSoftInput(getActivity());
        switch (view.getId()) {
            case R.id.rl_goods_category:
                start(GoodsCategoryFragment.newInstance());
                break;
            case R.id.tv_goods_spec:
                start(GoodsSpecFragment.newInstance());
                break;
            case R.id.rl_goods_property:
                start(GoodsPropertyFragment.newInstance());
                break;
            case R.id.iv_add_img:
                ImagePicker imagePicker = ImagePicker.getInstance();
                imagePicker.setMultiMode(false);
                imagePicker.setCrop(true);
                imagePicker.setStyle(CropImageView.Style.RECTANGLE);
                Intent imgIntent = new Intent(getActivity(), ImageGridActivity.class);
                startActivityForResult(imgIntent, REQUEST_IMAGE_PICKER);
                break;
            case R.id.tv_save:
                mPresenter.addGoods(etName.getText().toString().trim(), mImgUrl, etBrief.getText().toString().trim(), rbOn.isChecked(),
                        mCategoryList, mGoodsPropertysBeans, mSpecs);
                break;
            default:
                break;
        }
    }

    /**
     * 添加分类
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAddCategory(GoodsCategoryEvent event) {
        if (event.getList() != null) {
            L.i(event.getList().size());
            String category = "";
            for (GoodsCategory item : event.getList()) {
                mCategoryList.add(item.getGoodsCategoryId());
                category = category + (TextUtils.isEmpty(category) ? "" : "、") + item.getGoodsCategoryName();
            }
            tvGoodsCategory.setText(category);
        }
    }

    /**
     * 添加属性
     *
     * @param bean
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAddProperty(AddGoods.GoodsPropertysBean bean) {
        mGoodsPropertysBeans.add(bean);
    }

    /**
     * 添加规格
     *
     * @param bean
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAddSpec(AddGoods.AddSpecsBean bean) {
        mSpecs.add(bean);
        mSpecList.add(bean);
        mSpecAdapter.notifyDataSetChanged();
    }


    @Override
    public void onStart() {
        EventBus.getDefault().register(this);
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    String mImgStr;
    String mImgUrl;
    List<Integer> mCategoryList=new ArrayList<>();
    List<AddGoods.GoodsPropertysBean> mGoodsPropertysBeans=new ArrayList<>();
    List<AddGoods.AddSpecsBean> mSpecs=new ArrayList<>();

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == REQUEST_IMAGE_PICKER) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra
                        (ImagePicker.EXTRA_RESULT_ITEMS);
                mImgStr = images.get(0).path;
                ImageLoaderUtils.loadFileImg(mAppComponent, mImgStr, ivAddImg);
                mPresenter.uploadImg(mImgStr);
            }
        }
    }

    @Override
    public void showSnackbarMsg(String msg, int type) {
        ArmsUtils.snackbarText(msg, type);
    }

    @Override
    public void showSnackbarMsg(int msgId, int type) {
        ArmsUtils.snackbarText(getString(msgId), type);
    }

    @Override
    public void addGoodsSuc() {
        pop();
    }

    @Override
    public void uploadImgSuc(String imgUrl) {
        mImgUrl = imgUrl;
    }
}