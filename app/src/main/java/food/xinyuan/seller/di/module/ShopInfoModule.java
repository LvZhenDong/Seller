package food.xinyuan.seller.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import food.xinyuan.seller.mvp.contract.ShopInfoContract;
import food.xinyuan.seller.mvp.model.ShopInfoModel;


@Module
public class ShopInfoModule {
    private ShopInfoContract.View view;

    /**
     * 构建ShopInfoModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public ShopInfoModule(ShopInfoContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ShopInfoContract.View provideShopInfoView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    ShopInfoContract.Model provideShopInfoModel(ShopInfoModel model) {
        return model;
    }
}