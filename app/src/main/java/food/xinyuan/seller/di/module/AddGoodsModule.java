package food.xinyuan.seller.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import food.xinyuan.seller.mvp.contract.AddGoodsContract;
import food.xinyuan.seller.mvp.model.AddGoodsModel;


@Module
public class AddGoodsModule {
    private AddGoodsContract.View view;

    /**
     * 构建AddGoodsModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public AddGoodsModule(AddGoodsContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    AddGoodsContract.View provideAddGoodsView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    AddGoodsContract.Model provideAddGoodsModel(AddGoodsModel model) {
        return model;
    }
}