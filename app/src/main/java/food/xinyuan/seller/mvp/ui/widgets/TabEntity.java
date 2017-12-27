package food.xinyuan.seller.mvp.ui.widgets;

import com.flyco.tablayout.listener.CustomTabEntity;

/**
 * 指示器
 *
 * @author LvZhenDong
 *         created on 2017/11/16 14:58
 */
public class TabEntity implements CustomTabEntity {
    String title;
    public int selectedIcon;
    public int unSelectedIcon;

    public TabEntity(String title) {
        this.title = title;
    }

    public TabEntity(String title, int selectedIcon, int unSelectedIcon) {
        this.title = title;
        this.selectedIcon = selectedIcon;
        this.unSelectedIcon = unSelectedIcon;
    }

    @Override
    public String getTabTitle() {
        return title;
    }

    @Override
    public int getTabSelectedIcon() {
        return selectedIcon;
    }

    @Override
    public int getTabUnselectedIcon() {
        return unSelectedIcon;
    }
}
