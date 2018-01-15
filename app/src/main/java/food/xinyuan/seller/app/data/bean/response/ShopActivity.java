package food.xinyuan.seller.app.data.bean.response;

import android.text.TextUtils;

import food.xinyuan.seller.R;
import food.xinyuan.seller.app.utils.ConstantUtil;
import food.xinyuan.seller.app.utils.DataUtils;
import food.xinyuan.seller.app.utils.XDateUtils;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author lzd
 * @CreateDate 2018/1/15
 */
public class ShopActivity {

    public ShopActivity(String activityType, long beginTime, long endTime ,ActivityContentBean activityContent) {
        this.activityType = activityType;
        this.activityContent = activityContent;
        this.beginTime = beginTime;
        this.endTime = endTime;
    }

    /**
     * activityId : 272
     * shopId : 61
     * activityName : 满50元赠送10张小红包
     * activityType : COMPLIMENTARY
     * activityContent : {"typeName":"sharefood.models.activity.activity.entity.ComplimentaryActivityData","couponId":9,"full":50,"couponCount":10}
     * audit : true
     * isValid : false
     * beginTime : 1514962091000
     * endTime : 1515254400000
     */
    private int type;
    private final static String[] typeStrs= {"购满就送","折扣商品","购满就减","首单立减","其他"};
    private final static String[] iconStrs= {"惠","折","减","首","惠"};
    private final static int[] iconBgs={R.drawable.bg_red_radius_2dp,R.drawable.bg_orange_radius_2dp,
            R.drawable.bg_red_radius_2dp,R.drawable.bg_green_radius_2dp,R.drawable.bg_orange_radius_2dp};
    private int activityId;
    private int shopId;
    private String activityName;
    private String activityType;
    private ActivityContentBean activityContent;
    private boolean audit;
    private boolean isValid;
    private long beginTime;
    private long endTime;

    public int getType(){
        if(TextUtils.equals(activityType,ConstantUtil.ACTIVITY_TYPE_COMPLIMENTARY)){
            return 0;
        }else if(TextUtils.equals(activityType,ConstantUtil.ACTIVITY_TYPE_SALE)){
            return 1;
        }else if(TextUtils.equals(activityType,ConstantUtil.ACTIVITY_TYPE_DELGOLD)){
            return 2;
        }else if(TextUtils.equals(activityType,ConstantUtil.ACTIVITY_TYPE_FIRST)){
            return 3;
        }else {
            return 4;
        }
    }

    public String getTimeStr(){
        String startTimeStr= XDateUtils.millis2String(beginTime,"yyyy-MM-dd");
        String endTimeStr="";
        if(endTime == 0){
            endTimeStr="长期";
        }else {
            endTimeStr=XDateUtils.millis2String(endTime,"yyyy-MM-dd");
        }
        return "有效期:"+startTimeStr+" 至 "+endTimeStr;
    }
    public String getTypeStr(){
        return typeStrs[getType()];
    }

    public String getIconStr(){
        return iconStrs[getType()];
    }

    public int getIconDrawableId(){
        return iconBgs[getType()];
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public ActivityContentBean getActivityContent() {
        return activityContent;
    }

    public void setActivityContent(ActivityContentBean activityContent) {
        this.activityContent = activityContent;
    }

    public boolean isAudit() {
        return audit;
    }

    public void setAudit(boolean audit) {
        this.audit = audit;
    }

    public boolean isIsValid() {
        return isValid;
    }

    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }

    public long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public static class ActivityContentBean {

        public ActivityContentBean(long couponId, double full, int couponCount) {
            this.couponId = couponId;
            this.full = full;
            this.couponCount = couponCount;
        }

        /**
         * typeName : sharefood.models.activity.activity.entity.ComplimentaryActivityData
         * couponId : 9
         * full : 50
         * couponCount : 10
         */

        private String typeName;
        private long couponId;
        private double full;
        private int couponCount;

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public long getCouponId() {
            return couponId;
        }

        public void setCouponId(long couponId) {
            this.couponId = couponId;
        }

        public double getFull() {
            return full;
        }

        public void setFull(double full) {
            this.full = full;
        }

        public int getCouponCount() {
            return couponCount;
        }

        public void setCouponCount(int couponCount) {
            this.couponCount = couponCount;
        }
    }
}
