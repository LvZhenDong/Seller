package food.xinyuan.seller.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import food.xinyuan.seller.mvp.contract.GoodsCategoryContract;
import food.xinyuan.seller.mvp.model.GoodsCategoryModel;


@Module
public class GoodsCategoryModule {
    private GoodsCategoryContract.View view;

    /**
     * 构建GoodsCategoryModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public GoodsCategoryModule(GoodsCategoryContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    GoodsCategoryContract.View provideGoodsCategoryView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    GoodsCategoryContract.Model provideGoodsCategoryModel(GoodsCategoryModel model) {
        return model;
    }
}