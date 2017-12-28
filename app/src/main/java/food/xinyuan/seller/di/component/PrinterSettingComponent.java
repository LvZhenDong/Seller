package food.xinyuan.seller.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import food.xinyuan.seller.di.module.PrinterSettingModule;

import food.xinyuan.seller.mvp.ui.fragment.PrinterSettingFragment;

@ActivityScope
@Component(modules = PrinterSettingModule.class, dependencies = AppComponent.class)
public interface PrinterSettingComponent {
    void inject(PrinterSettingFragment fragment);
}