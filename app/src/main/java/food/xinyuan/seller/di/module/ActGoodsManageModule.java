package food.xinyuan.seller.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import food.xinyuan.seller.mvp.contract.ActGoodsManageContract;
import food.xinyuan.seller.mvp.model.ActGoodsManageModel;


@Module
public class ActGoodsManageModule {
    private ActGoodsManageContract.View view;

    /**
     * 构建ActGoodsManageModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public ActGoodsManageModule(ActGoodsManageContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ActGoodsManageContract.View provideActGoodsManageView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    ActGoodsManageContract.Model provideActGoodsManageModel(ActGoodsManageModel model) {
        return model;
    }
}