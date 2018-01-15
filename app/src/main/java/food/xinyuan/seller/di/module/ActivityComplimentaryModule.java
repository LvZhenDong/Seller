package food.xinyuan.seller.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import food.xinyuan.seller.mvp.contract.ActivityComplimentaryContract;
import food.xinyuan.seller.mvp.model.ActivityComplimentaryModel;


@Module
public class ActivityComplimentaryModule {
    private ActivityComplimentaryContract.View view;

    /**
     * 构建ActivityComplimentaryModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public ActivityComplimentaryModule(ActivityComplimentaryContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ActivityComplimentaryContract.View provideActivityComplimentaryView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    ActivityComplimentaryContract.Model provideActivityComplimentaryModel(ActivityComplimentaryModel model) {
        return model;
    }
}