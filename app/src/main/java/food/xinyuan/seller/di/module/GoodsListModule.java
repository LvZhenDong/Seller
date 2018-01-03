package food.xinyuan.seller.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import food.xinyuan.seller.mvp.contract.GoodsListContract;
import food.xinyuan.seller.mvp.model.GoodsListModel;


@Module
public class GoodsListModule {
    private GoodsListContract.View view;

    /**
     * 构建GoodsListModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public GoodsListModule(GoodsListContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    GoodsListContract.View provideGoodsListView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    GoodsListContract.Model provideGoodsListModel(GoodsListModel model) {
        return model;
    }
}