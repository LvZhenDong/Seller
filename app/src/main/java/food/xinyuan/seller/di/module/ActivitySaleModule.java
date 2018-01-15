package food.xinyuan.seller.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import food.xinyuan.seller.mvp.contract.ActivitySaleContract;
import food.xinyuan.seller.mvp.model.ActivitySaleModel;


@Module
public class ActivitySaleModule {
    private ActivitySaleContract.View view;

    /**
     * 构建ActivitySaleModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public ActivitySaleModule(ActivitySaleContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ActivitySaleContract.View provideActivitySaleView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    ActivitySaleContract.Model provideActivitySaleModel(ActivitySaleModel model) {
        return model;
    }
}