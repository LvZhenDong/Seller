package food.xinyuan.seller.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import food.xinyuan.seller.di.module.OrderListModule;

import food.xinyuan.seller.mvp.ui.fragment.OrderListFragment;

@ActivityScope
@Component(modules = OrderListModule.class, dependencies = AppComponent.class)
public interface OrderListComponent {
    void inject(OrderListFragment fragment);
}