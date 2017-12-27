package food.xinyuan.seller.app.data.bean.common;

/**
 * Created by f-x on 2017/12/17  10:47
 * Description
 */

public class DateDifference implements NotObfuscateInterface {
    private long millisecond;
    private long second;
    private long minute;
    private long hour;
    private long day;

    public long getMillisecond() {
        return millisecond;
    }

    public void setMillisecond(long millisecond) {
        this.millisecond = millisecond;
    }

    public long getSecond() {
        return second;
    }

    public void setSecond(long second) {
        this.second = second;
    }

    public long getMinute() {
        return minute;
    }

    public void setMinute(long minute) {
        this.minute = minute;
    }

    public long getHour() {
        return hour;
    }

    public void setHour(long hour) {
        this.hour = hour;
    }

    public long getDay() {
        return day;
    }

    public void setDay(long day) {
        this.day = day;
    }

    @Override
    public String toString() {
        if (hour == 0) {
            if (minute == 0) {
                if (second == 0) {
                    return "没有时间";
                } else {
                    return "0分" + second + "秒";
                }
            } else {
                return minute + "分";
            }
        } else {
            if (minute == 0) {
                return hour + "小时" + "0分";
            } else {
                return hour + "小时" + minute + "分";

            }
        }
    }
}
