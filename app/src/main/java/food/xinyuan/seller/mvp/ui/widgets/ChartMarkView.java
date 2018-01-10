package food.xinyuan.seller.mvp.ui.widgets;

import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;

import food.xinyuan.seller.R;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author lzd
 * @CreateDate 2018/1/10
 */
public class ChartMarkView extends MarkerView {
    private TextView tvContent;

    /**
     * Constructor. Sets up the MarkerView with a custom layout resource.
     *
     * @param context
     * @param layoutResource the layout resource to use for the MarkerView
     */
    public ChartMarkView(Context context, int layoutResource) {
        super(context, layoutResource);
        tvContent=findViewById(R.id.tv_mark);
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        tvContent.setText(Math.round(e.getVal())+"");
    }

    @Override
    public int getXOffset(float xpos) {
        return 0;
    }

    @Override
    public int getYOffset(float ypos) {
        return 0;
    }
}
