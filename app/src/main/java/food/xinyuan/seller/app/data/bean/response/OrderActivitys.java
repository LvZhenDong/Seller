package food.xinyuan.seller.app.data.bean.response;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author lzd
 * @CreateDate 2018/1/9
 */
public class OrderActivitys {

    /**
     * orderId : 5018
     * activityId : 278
     * activityContent : 满减活动优惠
     * activityReduced : 1
     * activityType : DELGOLD
     */

    private long orderId;
    private int activityId;
    private String activityContent;
    private double activityReduced;
    private String activityType;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public String getActivityContent() {
        return activityContent;
    }

    public void setActivityContent(String activityContent) {
        this.activityContent = activityContent;
    }

    public double getActivityReduced() {
        return activityReduced;
    }

    public void setActivityReduced(double activityReduced) {
        this.activityReduced = activityReduced;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }
}
