package food.xinyuan.seller.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import food.xinyuan.seller.di.module.ShopInfoModule;

import food.xinyuan.seller.mvp.ui.fragment.ShopInfoFragment;

@ActivityScope
@Component(modules = ShopInfoModule.class, dependencies = AppComponent.class)
public interface ShopInfoComponent {
    void inject(ShopInfoFragment fragment);
}