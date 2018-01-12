package food.xinyuan.seller.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import food.xinyuan.seller.mvp.contract.AppraiseManageContract;
import food.xinyuan.seller.mvp.model.AppraiseManageModel;


@Module
public class AppraiseManageModule {
    private AppraiseManageContract.View view;

    /**
     * 构建AppraiseManageModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public AppraiseManageModule(AppraiseManageContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    AppraiseManageContract.View provideAppraiseManageView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    AppraiseManageContract.Model provideAppraiseManageModel(AppraiseManageModel model) {
        return model;
    }
}