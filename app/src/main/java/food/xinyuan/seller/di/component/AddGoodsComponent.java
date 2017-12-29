package food.xinyuan.seller.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import food.xinyuan.seller.di.module.AddGoodsModule;

import food.xinyuan.seller.mvp.ui.fragment.AddGoodsFragment;

@ActivityScope
@Component(modules = AddGoodsModule.class, dependencies = AppComponent.class)
public interface AddGoodsComponent {
    void inject(AddGoodsFragment fragment);
}