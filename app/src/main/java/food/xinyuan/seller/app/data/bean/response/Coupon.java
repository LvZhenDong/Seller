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
    private int maxMoney;
    private int maxPickUpNumber;
    private int minMoney;
    private int minimum;
    private int money;
    private String pickUpType;
    private int pickUped;
    private long startTime;
    private String useSocpe;

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
            return XDateUtils.millis2String(endTime,"yyyy-MM-dd HH:mm") + "到期";
    }

    /**
     * 最小金额
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
     * @return
     */
    public String getMoneyStr(){
        if(!isRandom())
            return "¥"+money;
        else
            return "¥"+minMoney+"~"+"¥"+maxMoney;
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

    public int getMaxMoney() {
        return maxMoney;
    }

    public void setMaxMoney(int maxMoney) {
        this.maxMoney = maxMoney;
    }

    public int getMaxPickUpNumber() {
        return maxPickUpNumber;
    }

    public void setMaxPickUpNumber(int maxPickUpNumber) {
        this.maxPickUpNumber = maxPickUpNumber;
    }

    public int getMinMoney() {
        return minMoney;
    }

    public void setMinMoney(int minMoney) {
        this.minMoney = minMoney;
    }

    public int getMinimum() {
        return minimum;
    }

    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
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
