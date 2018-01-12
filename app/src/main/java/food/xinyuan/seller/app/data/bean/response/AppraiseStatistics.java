package food.xinyuan.seller.app.data.bean.response;

import food.xinyuan.seller.app.utils.DoubleUtil;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author lzd
 * @CreateDate 2018/1/12
 */
public class AppraiseStatistics {

    /**
     * shopId : 61
     * reversionRate : 0.67
     * reviewRate : 0
     * comprehensiveApprise : 5
     * businessCircleRatio : 0.9884
     */

    private int shopId;
    private double reversionRate;
    private double reviewRate;
    private int comprehensiveApprise;
    private double businessCircleRatio;

    public String getRatioStr() {
        return DoubleUtil.mul(businessCircleRatio, 100d) + "%";
    }

    public String getAllStr() {
        return DoubleUtil.mul(reversionRate, 100d) + "%";
    }

    public String getNativeStr() {
        return DoubleUtil.mul(reviewRate, 100d) + "%";
    }

    public int getAllProgress() {
        return (int) Math.round(DoubleUtil.mul(reversionRate, 100d));
    }

    public int getNativeProgress() {
        return (int) Math.round(DoubleUtil.mul(reviewRate, 100d));
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public double getReversionRate() {
        return reversionRate;
    }

    public void setReversionRate(double reversionRate) {
        this.reversionRate = reversionRate;
    }

    public double getReviewRate() {
        return reviewRate;
    }

    public void setReviewRate(double reviewRate) {
        this.reviewRate = reviewRate;
    }

    public int getComprehensiveApprise() {
        return comprehensiveApprise;
    }

    public void setComprehensiveApprise(int comprehensiveApprise) {
        this.comprehensiveApprise = comprehensiveApprise;
    }

    public double getBusinessCircleRatio() {
        return businessCircleRatio;
    }

    public void setBusinessCircleRatio(double businessCircleRatio) {
        this.businessCircleRatio = businessCircleRatio;
    }
}
