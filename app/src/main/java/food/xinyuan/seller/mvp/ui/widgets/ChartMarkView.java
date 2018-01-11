package food.xinyuan.seller.mvp.ui.widgets;

import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;

import java.util.List;

import food.xinyuan.seller.R;
import food.xinyuan.seller.app.utils.L;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author lzd
 * @CreateDate 2018/1/10
 */
public class ChartMarkView extends MarkerView {
    private TextView tvDate;
    private TextView tvCount;
    List<String> mDates;
    String mHint;

    int xIndex;
    int mChartHeight;

    /**
     * Constructor. Sets up the MarkerView with a custom layout resource.
     *
     * @param context
     * @param layoutResource the layout resource to use for the MarkerView
     */
    public ChartMarkView(Context context, int layoutResource, List<String> dates, String hint, int chartHeight) {
        super(context, layoutResource);
        tvDate = findViewById(R.id.tv_mark_date);
        tvCount = findViewById(R.id.tv_mark_count);
        mDates = dates;
        mHint = hint;
        mChartHeight = chartHeight;
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        if(e.getVal()%1==0){    //整数
            tvCount.setText(mHint + ":" + Math.round(e.getVal()));
        }else {                 //浮点数
            tvCount.setText(mHint + ":" + e.getVal());
        }

        tvDate.setText(mDates.get(e.getXIndex()));
        xIndex = highlight.getXIndex();

    }

    @Override
    public int getXOffset(float xpos) {
        if (xIndex > 3)
            return -getWidth()-20;
        else
            return 10;
    }

    @Override
    public int getYOffset(float ypos) {
        if (ypos > mChartHeight / 2)
            return -getHeight()-20;
        else
            return 0;
    }
}
