package food.xinyuan.seller.app.utils;

import android.content.Context;
import android.view.View;

import com.bigkoo.pickerview.TimePickerView;
import com.jess.arms.utils.ArmsUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author lzd
 * @CreateDate 2018/1/18
 */
public class TimePickerUtils {

    private Date startDate = Calendar.getInstance().getTime();
    private Date endDate;
    //只显示年月日
    boolean[] types = {true, true, true, false, false, false};

    public void showStart(Context context, TimeCallBack callBack) {
        showTimerPicker(context, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                if (endDate != null && date.after(endDate)) {
                    ArmsUtils.makeText(context, "结束时间不能小于开始时间");
                } else {
                    startDate = date;
                    if (callBack != null)
                        callBack.onStartTimeSelect(date);
                }
            }
        });
    }

    public void showEnd(Context context, TimeCallBack callBack) {
        showTimerPicker(context, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                if (startDate.after(date)) {
                    ArmsUtils.makeText(context, "结束时间不能小于开始时间");
                } else {
                    endDate = date;
                    if (callBack != null)
                        callBack.onEndTimeSelect(date);
                }
            }
        });
    }


    private void showTimerPicker(Context context, TimePickerView.OnTimeSelectListener listener) {
        Calendar calendar = Calendar.getInstance();
        //如果已经选择了开始时间，则以选定的时间做为开始，否则以当前系统时间开始
        if (startDate != null)
            calendar.setTime(startDate);
        TimePickerView pvTime = new TimePickerView.Builder(context, listener)
                .setRangDate(calendar, null)
                .setType(types)
                .build();

        pvTime.show();
    }

    public interface TimeCallBack {
        void onStartTimeSelect(Date startDate);

        void onEndTimeSelect(Date endDate);
    }
}
