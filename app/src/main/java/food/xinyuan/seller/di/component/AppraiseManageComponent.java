package food.xinyuan.seller.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import food.xinyuan.seller.di.module.AppraiseManageModule;

import food.xinyuan.seller.mvp.ui.fragment.AppraiseManageFragment;

@ActivityScope
@Component(modules = AppraiseManageModule.class, dependencies = AppComponent.class)
public interface AppraiseManageComponent {
    void inject(AppraiseManageFragment fragment);
}