package food.xinyuan.seller.app.data.bean.request;

/**
 * Created by lvzhendong on 2018/1/3.
 */

public class ChangeBusTime {

    public ChangeBusTime(String busBeginTime, String busEndTime) {
        this.busBeginTime = busBeginTime;
        this.busEndTime = busEndTime;
    }

    /**
     * busBeginTime : 08:30
     * busEndTime : 23:00
     */

    private String busBeginTime;
    private String busEndTime;

    public String getBusBeginTime() {
        return busBeginTime;
    }

    public void setBusBeginTime(String busBeginTime) {
        this.busBeginTime = busBeginTime;
    }

    public String getBusEndTime() {
        return busEndTime;
    }

    public void setBusEndTime(String busEndTime) {
        this.busEndTime = busEndTime;
    }
}
