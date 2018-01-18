package food.xinyuan.seller.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import food.xinyuan.seller.mvp.contract.AppraiseListContract;
import food.xinyuan.seller.mvp.model.AppraiseListModel;


@Module
public class AppraiseListFragmentModule {
    private AppraiseListContract.View view;

    /**
     * 构建AppraiseListFragmentModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public AppraiseListFragmentModule(AppraiseListContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    AppraiseListContract.View provideAppraiseListFragmentView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    AppraiseListContract.Model provideAppraiseListFragmentModel(AppraiseListModel model) {
        return model;
    }
}