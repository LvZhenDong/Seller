package food.xinyuan.seller.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import food.xinyuan.seller.mvp.contract.OrderCancelContract;
import food.xinyuan.seller.mvp.model.OrderCancelModel;


@Module
public class OrderCancelModule {
    private OrderCancelContract.View view;

    /**
     * 构建OrderCancelModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public OrderCancelModule(OrderCancelContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    OrderCancelContract.View provideOrderCancelView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    OrderCancelContract.Model provideOrderCancelModel(OrderCancelModel model) {
        return model;
    }
}