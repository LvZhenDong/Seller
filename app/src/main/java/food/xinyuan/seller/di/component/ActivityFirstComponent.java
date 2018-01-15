package food.xinyuan.seller.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import food.xinyuan.seller.di.module.ActivityFirstModule;

import food.xinyuan.seller.mvp.ui.fragment.ActivityFirstFragment;

@ActivityScope
@Component(modules = ActivityFirstModule.class, dependencies = AppComponent.class)
public interface ActivityFirstComponent {
    void inject(ActivityFirstFragment fragment);
}