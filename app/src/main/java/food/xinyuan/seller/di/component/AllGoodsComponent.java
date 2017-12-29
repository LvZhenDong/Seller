package food.xinyuan.seller.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;
import food.xinyuan.seller.di.module.AllGoodsModule;
import food.xinyuan.seller.mvp.ui.fragment.AllGoodsFragment;

@ActivityScope
@Component(modules = AllGoodsModule.class, dependencies = AppComponent.class)
public interface AllGoodsComponent {
    void inject(AllGoodsFragment fragment);
}