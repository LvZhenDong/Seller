package food.xinyuan.seller.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import food.xinyuan.seller.mvp.contract.AddPrinterContract;
import food.xinyuan.seller.mvp.model.AddPrinterModel;


@Module
public class AddPrinterModule {
    private AddPrinterContract.View view;

    /**
     * 构建AddPrinterModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public AddPrinterModule(AddPrinterContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    AddPrinterContract.View provideAddPrinterView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    AddPrinterContract.Model provideAddPrinterModel(AddPrinterModel model) {
        return model;
    }
}