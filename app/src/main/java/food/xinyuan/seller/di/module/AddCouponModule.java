package food.xinyuan.seller.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import food.xinyuan.seller.mvp.contract.AddCouponContract;
import food.xinyuan.seller.mvp.model.AddCouponModel;


@Module
public class AddCouponModule {
    private AddCouponContract.View view;

    /**
     * 构建AddCouponModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public AddCouponModule(AddCouponContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    AddCouponContract.View provideAddCouponView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    AddCouponContract.Model provideAddCouponModel(AddCouponModel model) {
        return model;
    }
}