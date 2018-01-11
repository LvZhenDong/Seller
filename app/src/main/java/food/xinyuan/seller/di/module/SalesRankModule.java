package food.xinyuan.seller.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import food.xinyuan.seller.mvp.contract.SalesRankContract;
import food.xinyuan.seller.mvp.model.SalesRankModel;


@Module
public class SalesRankModule {
    private SalesRankContract.View view;

    /**
     * 构建SalesRankModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public SalesRankModule(SalesRankContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    SalesRankContract.View provideSalesRankView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    SalesRankContract.Model provideSalesRankModel(SalesRankModel model) {
        return model;
    }
}