package food.xinyuan.seller.mvp.ui.widgets;

import android.graphics.Canvas;
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
    protected final int decoration;//边距大小 px

    public SurroundDecoration(int decoration) {
        this.decoration = decoration;
    }

    public SurroundDecoration() {
        this.decoration = 6;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        final RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        final int lastPosition = state.getItemCount() - 1;//整个RecyclerView最后一个item的position
        final int current = parent.getChildLayoutPosition(view);//获取当前要进行布局的item的position
        if (current == -1) return;//holder出现异常时，可能为-1

        if (current == 0)
            dealWithTop(outRect);
        else if(current == lastPosition)
            outRect.set(decoration , decoration/2, decoration , decoration);
        else
            outRect.set(decoration , decoration/2, decoration , decoration/2);
    }

    protected void dealWithTop(Rect outRect){
        outRect.set(decoration , decoration, decoration , decoration/2);
    }

    public static class NoTop extends SurroundDecoration{
        public NoTop(int decoration) {
            super(decoration);
        }

        @Override
        protected void dealWithTop(Rect outRect) {
            outRect.set(decoration , 0, decoration , decoration/2);
        }
    }

}
