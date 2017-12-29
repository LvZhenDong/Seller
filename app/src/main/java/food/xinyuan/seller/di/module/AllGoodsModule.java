package food.xinyuan.seller.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import food.xinyuan.seller.mvp.contract.AllGoodsContract;
import food.xinyuan.seller.mvp.model.AllGoodsModel;


@Module
public class AllGoodsModule {
    private AllGoodsContract.View view;

    /**
     * 构建AllGoodsModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public AllGoodsModule(AllGoodsContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    AllGoodsContract.View provideAllGoodsView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    AllGoodsContract.Model provideAllGoodsModel(AllGoodsModel model) {
        return model;
    }
}