package food.xinyuan.seller.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import food.xinyuan.seller.di.module.ActivitySpecificModule;

import food.xinyuan.seller.mvp.ui.fragment.ActivitySpecificFragment;

@ActivityScope
@Component(modules = ActivitySpecificModule.class, dependencies = AppComponent.class)
public interface ActivitySpecificComponent {
    void inject(ActivitySpecificFragment fragment);
}