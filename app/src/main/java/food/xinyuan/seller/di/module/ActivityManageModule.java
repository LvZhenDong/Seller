package food.xinyuan.seller.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import food.xinyuan.seller.mvp.contract.ActivityManageContract;
import food.xinyuan.seller.mvp.model.ActivityManageModel;


@Module
public class ActivityManageModule {
    private ActivityManageContract.View view;

    /**
     * 构建ActivityManageModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public ActivityManageModule(ActivityManageContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ActivityManageContract.View provideActivityManageView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    ActivityManageContract.Model provideActivityManageModel(ActivityManageModel model) {
        return model;
    }
}