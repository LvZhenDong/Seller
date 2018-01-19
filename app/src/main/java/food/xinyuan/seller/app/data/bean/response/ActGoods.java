package food.xinyuan.seller.app.data.bean.response;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author lzd
 * @CreateDate 2018/1/19
 */
public class ActGoods {

    /**
     * goodsId : 9057
     * activityId : 315
     * discount : 2.0
     * limitedQuantity : 3
     * goodsName : 土豆
     * goodsPrice : 12.0
     * valid : false
     */

    private long goodsId;
    private long activityId;
    private double discount;
    private int limitedQuantity;
    private String goodsName;
    private double goodsPrice;
    private Boolean valid;

    public long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(long goodsId) {
        this.goodsId = goodsId;
    }

    public long getActivityId() {
        return activityId;
    }

    public void setActivityId(long activityId) {
        this.activityId = activityId;
    }

    public double getDiscount() {
        return discount;
    }

    public String getDiscountStr() {
        if (discount <= 0) {
            return "";
        } else {
            return discount + "";
        }
    }

    public String getLimitedQuantityStr() {
        if (limitedQuantity <= 0) {
            return "";
        } else {
            return limitedQuantity + "";
        }
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getLimitedQuantity() {
        return limitedQuantity;
    }

    public void setLimitedQuantity(int limitedQuantity) {
        this.limitedQuantity = limitedQuantity;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Boolean isValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }
}
