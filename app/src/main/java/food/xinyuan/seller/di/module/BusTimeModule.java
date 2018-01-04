package food.xinyuan.seller.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import food.xinyuan.seller.mvp.contract.BusTimeContract;
import food.xinyuan.seller.mvp.model.BusTimeModel;


@Module
public class BusTimeModule {
    private BusTimeContract.View view;

    /**
     * 构建BusTimeModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public BusTimeModule(BusTimeContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    BusTimeContract.View provideBusTimeView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    BusTimeContract.Model provideBusTimeModel(BusTimeModel model) {
        return model;
    }
}