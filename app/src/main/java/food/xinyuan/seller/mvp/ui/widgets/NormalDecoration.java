package food.xinyuan.seller.mvp.ui.widgets;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author lzd
 * @CreateDate 2018/1/4
 */
public class NormalDecoration extends RecyclerView.ItemDecoration {
    private static final int HORIZONTAL = LinearLayoutManager.HORIZONTAL;//水平方向
    private static final int VERTICAL = LinearLayoutManager.VERTICAL;//垂直方向
    private int orientation;//方向
    private final int decoration;//边距大小 px

    public NormalDecoration(@LinearLayoutCompat.OrientationMode int orientation, int decoration) {
        this.orientation = orientation;
        this.decoration = decoration;
    }

    public NormalDecoration(@LinearLayoutCompat.OrientationMode int orientation) {
        this.orientation = orientation;
        this.decoration = 5;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        final RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        final int lastPosition = state.getItemCount() - 1;//整个RecyclerView最后一个item的position
        final int current = parent.getChildLayoutPosition(view);//获取当前要进行布局的item的position
        if (current == -1) return;//holder出现异常时，可能为-1
        if (layoutManager instanceof LinearLayoutManager && !(layoutManager instanceof GridLayoutManager)) {//LinearLayoutManager
            if (orientation == LinearLayoutManager.VERTICAL) {//垂直
                outRect.set(0, 0, 0, decoration);
            } else {//水平
                outRect.set(0, 0, decoration, 0);
            }
        }
    }

}
