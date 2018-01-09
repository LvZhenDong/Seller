package food.xinyuan.seller.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import food.xinyuan.seller.di.module.OrderCancelModule;

import food.xinyuan.seller.mvp.ui.fragment.OrderCancelFragment;

@ActivityScope
@Component(modules = OrderCancelModule.class, dependencies = AppComponent.class)
public interface OrderCancelComponent {
    void inject(OrderCancelFragment fragment);
}