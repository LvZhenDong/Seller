package food.xinyuan.seller.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import food.xinyuan.seller.di.module.SettingModule;

import food.xinyuan.seller.mvp.ui.fragment.SettingFragment;

@ActivityScope
@Component(modules = SettingModule.class, dependencies = AppComponent.class)
public interface SettingComponent {
    void inject(SettingFragment fragment);
}