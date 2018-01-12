package food.xinyuan.seller.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import food.xinyuan.seller.mvp.contract.AppraiseListFragmentContract;
import food.xinyuan.seller.mvp.model.AppraiseListFragmentModel;


@Module
public class AppraiseListFragmentModule {
    private AppraiseListFragmentContract.View view;

    /**
     * 构建AppraiseListFragmentModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public AppraiseListFragmentModule(AppraiseListFragmentContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    AppraiseListFragmentContract.View provideAppraiseListFragmentView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    AppraiseListFragmentContract.Model provideAppraiseListFragmentModel(AppraiseListFragmentModel model) {
        return model;
    }
}