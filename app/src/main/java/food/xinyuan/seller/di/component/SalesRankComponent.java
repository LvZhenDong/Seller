package food.xinyuan.seller.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import food.xinyuan.seller.di.module.SalesRankModule;

import food.xinyuan.seller.mvp.ui.fragment.SalesRankFragment;

@ActivityScope
@Component(modules = SalesRankModule.class, dependencies = AppComponent.class)
public interface SalesRankComponent {
    void inject(SalesRankFragment fragment);
}