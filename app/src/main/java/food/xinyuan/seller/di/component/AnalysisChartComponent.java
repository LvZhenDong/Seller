package food.xinyuan.seller.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import food.xinyuan.seller.di.module.AnalysisChartModule;

import food.xinyuan.seller.mvp.ui.fragment.AnalysisChartFragment;

@ActivityScope
@Component(modules = AnalysisChartModule.class, dependencies = AppComponent.class)
public interface AnalysisChartComponent {
    void inject(AnalysisChartFragment fragment);
}