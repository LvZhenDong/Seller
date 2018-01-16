package food.xinyuan.seller.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import food.xinyuan.seller.mvp.contract.ActivitySpecificContract;
import food.xinyuan.seller.mvp.model.ActivitySpecificModel;


@Module
public class ActivitySpecificModule {
    private ActivitySpecificContract.View view;

    /**
     * 构建ActivitySpecificModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public ActivitySpecificModule(ActivitySpecificContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ActivitySpecificContract.View provideActivitySpecificView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    ActivitySpecificContract.Model provideActivitySpecificModel(ActivitySpecificModel model) {
        return model;
    }
}