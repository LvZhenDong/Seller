package food.xinyuan.seller.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import food.xinyuan.seller.di.module.GoodsCategoryManageModule;

import food.xinyuan.seller.mvp.ui.fragment.GoodsCategoryManageFragment;

@ActivityScope
@Component(modules = GoodsCategoryManageModule.class, dependencies = AppComponent.class)
public interface GoodsCategoryManageComponent {
    void inject(GoodsCategoryManageFragment fragment);
}