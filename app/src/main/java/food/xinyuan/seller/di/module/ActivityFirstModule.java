package food.xinyuan.seller.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import food.xinyuan.seller.mvp.contract.ActivityFirstContract;
import food.xinyuan.seller.mvp.model.ActivityFirstModel;


@Module
public class ActivityFirstModule {
    private ActivityFirstContract.View view;

    /**
     * 构建ActivityFirstModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public ActivityFirstModule(ActivityFirstContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ActivityFirstContract.View provideActivityFirstView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    ActivityFirstContract.Model provideActivityFirstModel(ActivityFirstModel model) {
        return model;
    }
}