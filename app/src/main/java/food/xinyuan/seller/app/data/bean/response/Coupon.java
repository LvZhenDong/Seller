package food.xinyuan.seller.app.data.bean.response;

import android.text.TextUtils;

import food.xinyuan.seller.app.utils.XDateUtils;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author lzd
 * @CreateDate 2018/1/5
 */
public class Coupon {

    /**
     * canPickUp : false
     * couponId : 0
     * couponMoneyType : FIXED
     * couponName : string
     * endTime : 2018-01-05T01:52:01.196Z
     * maxMoney : 0
     * maxPickUpNumber : 0
     * minMoney : 0
     * minimum : 0
     * money : 0
     * pickUpType : CONSUME
     * pickUped : 0
     * startTime : 2018-01-05T01:52:01.196Z
     * useSocpe : SHOP
     */

    private boolean canPickUp;
    private int couponId;
    private String couponMoneyType;
    private String couponName;
    private long endTime;
    private double maxMoney;
    private int maxPickUpNumber;
    private double minMoney;
    private double minimum;
    private double money;
    private String pickUpType;
    private int pickUped;
    private long startTime;
    private String useSocpe;

    public Coupon(String name, boolean isPickUpAuto, boolean isFixed, double money, long startTime,
                  long endTime, int maxPickUpNumber, double minimum) {
        this.couponName = name;
        this.pickUpType = isPickUpAuto ? "HAND" : "CONSUME";
        this.couponMoneyType = isFixed ? "FIXED" : "RANDOM";
        this.money = money;
        this.startTime = startTime;
        this.endTime = endTime;
        this.maxPickUpNumber = maxPickUpNumber;
        this.minimum = minimum;
    }

    public Coupon(String name, boolean isPickUpAuto, boolean isFixed, double minMoney, double maxMoney, long startTime,
                  long endTime, int maxPickUpNumber, double minimum) {
        this.couponName = name;
        this.pickUpType = isPickUpAuto ? "HAND" : "CONSUME";
        this.couponMoneyType = isFixed ? "FIXED" : "RANDOM";
        this.minMoney = minMoney;
        this.maxMoney = maxMoney;
        this.startTime = startTime;
        this.endTime = endTime;
        this.maxPickUpNumber = maxPickUpNumber;
        this.minimum = minimum;
    }

    /**
     * 金额是否随机
     *
     * @return
     */
    public boolean isRandom() {
        return TextUtils.equals("RANDOM", couponMoneyType);
    }

    /**
     * 截止日期
     *
     * @return
     */
    public String getEndTimeStr() {
        if (endTime == 0)
            return "无期限";
        else
            return XDateUtils.millis2String(endTime, "yyyy-MM-dd") + "到期";
    }

    /**
     * 最小金额
     *
     * @return
     */
    public String getMinStr() {
        if (minimum > 0)
            return "满" + minimum + "元可用";
        else
            return "任意金额可用";
    }

    /**
     * 红包金额
     *
     * @return
     */
    public String getMoneyStr() {
        if (!isRandom())
            return "¥" + money;
        else
            return "¥" + minMoney + "~" + "¥" + maxMoney;
    }

    public boolean isCanPickUp() {
        return canPickUp;
    }

    public void setCanPickUp(boolean canPickUp) {
        this.canPickUp = canPickUp;
    }

    public int getCouponId() {
        return couponId;
    }

    public void setCouponId(int couponId) {
        this.couponId = couponId;
    }

    public String getCouponMoneyType() {
        return couponMoneyType;
    }

    public void setCouponMoneyType(String couponMoneyType) {
        this.couponMoneyType = couponMoneyType;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public double getMaxMoney() {
        return maxMoney;
    }

    public void setMaxMoney(double maxMoney) {
        this.maxMoney = maxMoney;
    }

    public int getMaxPickUpNumber() {
        return maxPickUpNumber;
    }

    public void setMaxPickUpNumber(int maxPickUpNumber) {
        this.maxPickUpNumber = maxPickUpNumber;
    }

    public double getMinMoney() {
        return minMoney;
    }

    public void setMinMoney(double minMoney) {
        this.minMoney = minMoney;
    }

    public double getMinimum() {
        return minimum;
    }

    public void setMinimum(double minimum) {
        this.minimum = minimum;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getPickUpType() {
        return pickUpType;
    }

    public void setPickUpType(String pickUpType) {
        this.pickUpType = pickUpType;
    }

    public int getPickUped() {
        return pickUped;
    }

    public void setPickUped(int pickUped) {
        this.pickUped = pickUped;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public String getUseSocpe() {
        return useSocpe;
    }

    public void setUseSocpe(String useSocpe) {
        this.useSocpe = useSocpe;
    }
}
