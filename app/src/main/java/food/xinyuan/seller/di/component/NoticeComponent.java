package food.xinyuan.seller.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import food.xinyuan.seller.di.module.NoticeModule;

import food.xinyuan.seller.mvp.ui.fragment.NoticeFragment;

@ActivityScope
@Component(modules = NoticeModule.class, dependencies = AppComponent.class)
public interface NoticeComponent {
    void inject(NoticeFragment fragment);
}