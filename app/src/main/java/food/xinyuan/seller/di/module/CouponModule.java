package food.xinyuan.seller.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import food.xinyuan.seller.mvp.contract.CouponContract;
import food.xinyuan.seller.mvp.model.CouponModel;


@Module
public class CouponModule {
    private CouponContract.View view;

    /**
     * 构建CouponModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public CouponModule(CouponContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    CouponContract.View provideCouponView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    CouponContract.Model provideCouponModel(CouponModel model) {
        return model;
    }
}