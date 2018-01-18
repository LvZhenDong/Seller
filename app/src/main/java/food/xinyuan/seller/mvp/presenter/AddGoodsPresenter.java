package food.xinyuan.seller.mvp.presenter;

import android.app.Application;
import android.text.TextUtils;

import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.utils.ArmsUtils;

import java.util.ArrayList;
import java.util.List;

import food.xinyuan.seller.app.config.applyOptions.factory.TransFactory;
import food.xinyuan.seller.app.data.bean.request.AddGoods;
import food.xinyuan.seller.app.data.bean.request.UpdateGoods;
import food.xinyuan.seller.app.data.bean.response.Goods;
import food.xinyuan.seller.app.data.bean.response.GoodsDetail;
import food.xinyuan.seller.app.data.bean.response.GoodsInfo;
import food.xinyuan.seller.app.data.bean.response.GoodsProperty;
import food.xinyuan.seller.app.data.bean.response.GoodsSpec;
import food.xinyuan.seller.app.data.bean.response.UploadFile;
import food.xinyuan.seller.app.utils.Constant;
import food.xinyuan.seller.app.utils.DataUtils;
import food.xinyuan.seller.app.utils.RequestUtils;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

import food.xinyuan.seller.mvp.contract.AddGoodsContract;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;


@ActivityScope
public class AddGoodsPresenter extends BasePresenter<AddGoodsContract.Model, AddGoodsContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    @Inject
    public AddGoodsPresenter(AddGoodsContract.Model model, AddGoodsContract.View rootView
            , RxErrorHandler handler, Application application
            , ImageLoader imageLoader, AppManager appManager) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mImageLoader = imageLoader;
        this.mAppManager = appManager;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }

    public void uploadImg(String imgUrl) {
        mModel.upLoadImg(RequestUtils.getPhoto(imgUrl))
                .compose(TransFactory.commonTrans(mRootView))
                .subscribe(new ErrorHandleSubscriber<UploadFile>(mErrorHandler) {
                    @Override
                    public void onNext(UploadFile uploadFile) {
                        ArmsUtils.snackbarText("上传图片成功", Constant.SNACK_NORMAL);
                        mRootView.uploadImgSuc(uploadFile.getOriginalUrl());
                    }
                });
    }

    /**
     * 添加商品
     *
     * @param name
     * @param imgUrl
     * @param brief
     * @param isPutOn
     * @param categoryList
     * @param propertysBeanList
     * @param specsBeanList
     */
    public void addGoods(String name, String imgUrl, String brief, boolean isPutOn, List<Long> categoryList,
                         List<GoodsProperty> propertysBeanList, List<GoodsSpec> specsBeanList) {
        //TODO 接口问题：规格名称没有填入
        GoodsInfo goodsInfo = createGoodsInfo(name, imgUrl, brief, isPutOn, categoryList, propertysBeanList, specsBeanList);
        if (goodsInfo != null) {
            mModel.addGoods(new AddGoods(goodsInfo, categoryList, propertysBeanList, specsBeanList))
                    .compose(TransFactory.commonTrans(mRootView))
                    .subscribe(new ErrorHandleSubscriber<AddGoods>(mErrorHandler) {
                        @Override
                        public void onNext(AddGoods addGoods) {
                            ArmsUtils.makeText(mApplication, "上传商品成功");
                            mRootView.addGoodsSuc();
                        }
                    });
        }

    }

    /**
     * 修改商品
     *
     * @param name
     * @param imgUrl
     * @param brief
     * @param isPutOn
     * @param categoryList
     * @param propertysBeanList
     * @param addSpecs
     */
    public void updateGoods(long goodsId, String name, String imgUrl, String brief, boolean isPutOn, List<Long> categoryList,
                            List<GoodsProperty> propertysBeanList, List<GoodsSpec> addSpecs, Goods goods) {
        GoodsInfo goodsInfo = createGoodsInfo(name, imgUrl, brief, isPutOn, categoryList, propertysBeanList, addSpecs);
        if (goodsInfo != null) {
            mModel.updateGoods(goodsId, new UpdateGoods(goodsInfo, categoryList, propertysBeanList, addSpecs, getDelSpecIds(goods.getGoodsSpecifications()), null))
                    .compose(TransFactory.commonTrans(mRootView))
                    .subscribe(new ErrorHandleSubscriber<UpdateGoods>(mErrorHandler) {
                        @Override
                        public void onNext(UpdateGoods addGoods) {
                            ArmsUtils.makeText(mApplication, "修改成功");
                            mRootView.addGoodsSuc();
                        }
                    });
        }
    }

    public void getGoods(long id) {
        mModel.getGoods(id)
                .compose(TransFactory.commonTrans(mRootView))
                .subscribe(new ErrorHandleSubscriber<GoodsDetail>(mErrorHandler) {
                    @Override
                    public void onNext(GoodsDetail data) {
                        mRootView.getGoodsSuc(data);
                    }
                });
    }

    private GoodsInfo createGoodsInfo(String name, String imgUrl, String brief, boolean isPutOn, List<Long> categoryList,
                                      List<GoodsProperty> propertysBeanList, List<GoodsSpec> specsBeanList) {
        if (TextUtils.isEmpty(imgUrl)) {
            ArmsUtils.snackbarText("请上传商品图片", Constant.SNACK_WARING);
        } else if (TextUtils.isEmpty(name)) {
            ArmsUtils.snackbarText("请上输入商品名称", Constant.SNACK_WARING);
        } else if (DataUtils.isEmpty(categoryList)) {
            ArmsUtils.snackbarText("请选择商品分类", Constant.SNACK_WARING);
        } else if (DataUtils.isEmpty(specsBeanList)) {
            ArmsUtils.snackbarText("请选择商品规格", Constant.SNACK_WARING);
        } else if (DataUtils.isEmpty(propertysBeanList)) {
            ArmsUtils.snackbarText("请选择商品属性", Constant.SNACK_WARING);
        } else if (TextUtils.isEmpty(brief)) {
            ArmsUtils.snackbarText("请输入商品简介", Constant.SNACK_WARING);
        } else {
            return new GoodsInfo(brief, imgUrl, name, isPutOn);
        }
        return null;
    }

    private List<Long> getDelSpecIds(List<GoodsSpec> goodsSpecList) {
        List<Long> result = new ArrayList<>();
        for (GoodsSpec item : goodsSpecList) {
            result.add(item.getGoodsSpecificationId());
        }
        return result;
    }

}
