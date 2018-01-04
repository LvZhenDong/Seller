package food.xinyuan.seller.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import food.xinyuan.seller.mvp.contract.NoticeContract;
import food.xinyuan.seller.mvp.model.NoticeModel;


@Module
public class NoticeModule {
    private NoticeContract.View view;

    /**
     * 构建NoticeModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public NoticeModule(NoticeContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    NoticeContract.View provideNoticeView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    NoticeContract.Model provideNoticeModel(NoticeModel model) {
        return model;
    }
}