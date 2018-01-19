package food.xinyuan.seller.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import food.xinyuan.seller.di.module.ActGoodsManageModule;

import food.xinyuan.seller.mvp.ui.fragment.ActGoodsManageFragment;

@ActivityScope
@Component(modules = ActGoodsManageModule.class, dependencies = AppComponent.class)
public interface ActGoodsManageComponent {
    void inject(ActGoodsManageFragment fragment);
}