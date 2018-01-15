package food.xinyuan.seller.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import food.xinyuan.seller.di.module.ActivitySaleModule;

import food.xinyuan.seller.mvp.ui.fragment.ActivitySaleFragment;

@ActivityScope
@Component(modules = ActivitySaleModule.class, dependencies = AppComponent.class)
public interface ActivitySaleComponent {
    void inject(ActivitySaleFragment fragment);
}