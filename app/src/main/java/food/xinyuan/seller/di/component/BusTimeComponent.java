package food.xinyuan.seller.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import food.xinyuan.seller.di.module.BusTimeModule;

import food.xinyuan.seller.mvp.ui.fragment.BusTimeFragment;

@ActivityScope
@Component(modules = BusTimeModule.class, dependencies = AppComponent.class)
public interface BusTimeComponent {
    void inject(BusTimeFragment fragment);
}