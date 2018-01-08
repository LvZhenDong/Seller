package food.xinyuan.seller.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import food.xinyuan.seller.mvp.contract.OrderListContract;
import food.xinyuan.seller.mvp.model.OrderListModel;


@Module
public class OrderListModule {
    private OrderListContract.View view;

    /**
     * 构建OrderListModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public OrderListModule(OrderListContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    OrderListContract.View provideOrderListView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    OrderListContract.Model provideOrderListModel(OrderListModel model) {
        return model;
    }
}