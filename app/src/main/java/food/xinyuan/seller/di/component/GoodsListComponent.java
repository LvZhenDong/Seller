package food.xinyuan.seller.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import food.xinyuan.seller.di.module.GoodsListModule;

import food.xinyuan.seller.mvp.ui.fragment.GoodsListFragment;

@ActivityScope
@Component(modules = GoodsListModule.class, dependencies = AppComponent.class)
public interface GoodsListComponent {
    void inject(GoodsListFragment fragment);
}