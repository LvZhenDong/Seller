package food.xinyuan.seller.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import food.xinyuan.seller.di.module.ActivityManageModule;

import food.xinyuan.seller.mvp.ui.fragment.ActivityManageFragment;

@ActivityScope
@Component(modules = ActivityManageModule.class, dependencies = AppComponent.class)
public interface ActivityManageComponent {
    void inject(ActivityManageFragment fragment);
}