package food.xinyuan.seller.mvp.presenter;

import android.app.Application;
import android.text.TextUtils;

import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.utils.ArmsUtils;

import java.util.List;

import food.xinyuan.seller.app.config.applyOptions.factory.TransFactory;
import food.xinyuan.seller.app.data.bean.request.AddGoods;
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

    public void uploadImg(String imgUrl){
        mModel.upLoadImg(RequestUtils.getPhoto(imgUrl))
                .compose(TransFactory.commonTrans(mRootView))
                .subscribe(new ErrorHandleSubscriber<UploadFile>(mErrorHandler) {
                    @Override
                    public void onNext(UploadFile uploadFile) {
                        mRootView.showSnackbarMsg("上传图片成功", Constant.SNACK_NORMAL);
                        mRootView.uploadImgSuc(uploadFile.getOriginalUrl());
                    }
                });
    }

    public void addGoods(String name, String imgUrl, String brief, boolean isPutOn, List<Integer> categoryList,
                         List<AddGoods.GoodsPropertysBean> propertysBeanList,List<AddGoods.AddSpecsBean> specsBeanList){
        if(TextUtils.isEmpty(imgUrl)){
            mRootView.showSnackbarMsg("请上传商品图片", Constant.SNACK_WARING);
        }else if(TextUtils.isEmpty(name)){
            mRootView.showSnackbarMsg("请上输入商品名称", Constant.SNACK_WARING);
        }else if(DataUtils.isEmpty(categoryList)){
            mRootView.showSnackbarMsg("请选择商品分类", Constant.SNACK_WARING);
        }else if(DataUtils.isEmpty(specsBeanList)){
            mRootView.showSnackbarMsg("请选择商品规格", Constant.SNACK_WARING);
        }else if(DataUtils.isEmpty(propertysBeanList)){
            mRootView.showSnackbarMsg("请选择商品属性", Constant.SNACK_WARING);
        }else if(TextUtils.isEmpty(brief)){
            mRootView.showSnackbarMsg("请输入商品简介", Constant.SNACK_WARING);
        }else {
            AddGoods.InfoBean infoBean=new AddGoods.InfoBean(brief,imgUrl,name,isPutOn);
            mModel.addGoods(new AddGoods(infoBean,categoryList,propertysBeanList,specsBeanList))
                    .compose(TransFactory.commonTrans(mRootView))
                    .subscribe(new ErrorHandleSubscriber<AddGoods>(mErrorHandler) {
                        @Override
                        public void onNext(AddGoods addGoods) {
                            ArmsUtils.makeText(mAppManager.getCurrentActivity(),"上传商品成功");
                            mRootView.addGoodsSuc();
                        }
                    });
        }

    }

}
