package food.xinyuan.seller.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import food.xinyuan.seller.mvp.contract.AnalysisChartContract;
import food.xinyuan.seller.mvp.model.AnalysisChartModel;


@Module
public class AnalysisChartModule {
    private AnalysisChartContract.View view;

    /**
     * 构建AnalysisChartModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public AnalysisChartModule(AnalysisChartContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    AnalysisChartContract.View provideAnalysisChartView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    AnalysisChartContract.Model provideAnalysisChartModel(AnalysisChartModel model) {
        return model;
    }
}