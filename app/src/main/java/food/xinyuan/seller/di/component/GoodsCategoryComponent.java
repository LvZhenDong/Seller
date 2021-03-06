package food.xinyuan.seller.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import food.xinyuan.seller.di.module.GoodsCategoryModule;

import food.xinyuan.seller.mvp.ui.fragment.GoodsCategoryFragment;

@ActivityScope
@Component(modules = GoodsCategoryModule.class, dependencies = AppComponent.class)
public interface GoodsCategoryComponent {
    void inject(GoodsCategoryFragment fragment);
}