package food.xinyuan.seller.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import food.xinyuan.seller.di.module.AppraiseListFragmentModule;

import food.xinyuan.seller.mvp.ui.fragment.AppraiseListFragment;

@ActivityScope
@Component(modules = AppraiseListFragmentModule.class, dependencies = AppComponent.class)
public interface AppraiseListFragmentComponent {
    void inject(AppraiseListFragment fragment);
}