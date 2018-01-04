package food.xinyuan.seller.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import food.xinyuan.seller.mvp.contract.GoodsCategoryManageContract;
import food.xinyuan.seller.mvp.model.GoodsCategoryManageModel;


@Module
public class GoodsCategoryManageModule {
    private GoodsCategoryManageContract.View view;

    /**
     * 构建GoodsCategoryManageModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public GoodsCategoryManageModule(GoodsCategoryManageContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    GoodsCategoryManageContract.View provideGoodsCategoryManageView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    GoodsCategoryManageContract.Model provideGoodsCategoryManageModel(GoodsCategoryManageModel model) {
        return model;
    }
}