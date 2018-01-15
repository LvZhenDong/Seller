package food.xinyuan.seller.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import food.xinyuan.seller.di.module.ActivityDelgoldModule;

import food.xinyuan.seller.mvp.ui.fragment.ActivityDelgoldFragment;

@ActivityScope
@Component(modules = ActivityDelgoldModule.class, dependencies = AppComponent.class)
public interface ActivityDelgoldComponent {
    void inject(ActivityDelgoldFragment fragment);
}