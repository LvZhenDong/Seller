package food.xinyuan.seller.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import food.xinyuan.seller.di.module.HomeModule;

import food.xinyuan.seller.mvp.ui.fragment.HomeFragment;

@ActivityScope
@Component(modules = HomeModule.class, dependencies = AppComponent.class)
public interface HomeComponent {
    void inject(HomeFragment activity);
}