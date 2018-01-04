package food.xinyuan.seller.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import food.xinyuan.seller.di.module.AddPrinterModule;

import food.xinyuan.seller.mvp.ui.fragment.AddPrinterFragment;

@ActivityScope
@Component(modules = AddPrinterModule.class, dependencies = AppComponent.class)
public interface AddPrinterComponent {
    void inject(AddPrinterFragment fragment);
}