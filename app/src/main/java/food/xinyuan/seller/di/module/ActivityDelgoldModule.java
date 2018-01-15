package food.xinyuan.seller.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import food.xinyuan.seller.mvp.contract.ActivityDelgoldContract;
import food.xinyuan.seller.mvp.model.ActivityDelgoldModel;


@Module
public class ActivityDelgoldModule {
    private ActivityDelgoldContract.View view;

    /**
     * 构建ActivityDelgoldModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public ActivityDelgoldModule(ActivityDelgoldContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ActivityDelgoldContract.View provideActivityDelgoldView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    ActivityDelgoldContract.Model provideActivityDelgoldModel(ActivityDelgoldModel model) {
        return model;
    }
}