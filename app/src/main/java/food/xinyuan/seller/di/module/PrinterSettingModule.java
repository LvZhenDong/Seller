package food.xinyuan.seller.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import food.xinyuan.seller.mvp.contract.PrinterSettingContract;
import food.xinyuan.seller.mvp.model.PrinterSettingModel;


@Module
public class PrinterSettingModule {
    private PrinterSettingContract.View view;

    /**
     * 构建PrinterSettingModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public PrinterSettingModule(PrinterSettingContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    PrinterSettingContract.View providePrinterSettingView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    PrinterSettingContract.Model providePrinterSettingModel(PrinterSettingModel model) {
        return model;
    }
}