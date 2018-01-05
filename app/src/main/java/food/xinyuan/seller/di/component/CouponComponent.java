package food.xinyuan.seller.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import food.xinyuan.seller.di.module.CouponModule;

import food.xinyuan.seller.mvp.ui.fragment.CouponFragment;

@ActivityScope
@Component(modules = CouponModule.class, dependencies = AppComponent.class)
public interface CouponComponent {
    void inject(CouponFragment fragment);
}