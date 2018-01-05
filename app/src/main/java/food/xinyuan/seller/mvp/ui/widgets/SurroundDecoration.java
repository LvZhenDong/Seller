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
public class SurroundDecoration extends RecyclerView.ItemDecoration {
    private final int decoration;//边距大小 px

    public SurroundDecoration(int decoration) {
        this.decoration = decoration;
    }

    public SurroundDecoration() {
        this.decoration = 5;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        final RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        final int lastPosition = state.getItemCount() - 1;//整个RecyclerView最后一个item的position
        final int current = parent.getChildLayoutPosition(view);//获取当前要进行布局的item的position
        if (current == -1) return;//holder出现异常时，可能为-1

        if (current == 0)
            outRect.set(decoration * 2, decoration*2, decoration * 2, decoration);
        else
            outRect.set(decoration * 2, decoration, decoration * 2, decoration);


    }

}
