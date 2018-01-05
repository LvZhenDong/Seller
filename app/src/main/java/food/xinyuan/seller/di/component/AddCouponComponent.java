package food.xinyuan.seller.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import food.xinyuan.seller.di.module.AddCouponModule;

import food.xinyuan.seller.mvp.ui.fragment.AddCouponFragment;

@ActivityScope
@Component(modules = AddCouponModule.class, dependencies = AppComponent.class)
public interface AddCouponComponent {
    void inject(AddCouponFragment fragment);
}