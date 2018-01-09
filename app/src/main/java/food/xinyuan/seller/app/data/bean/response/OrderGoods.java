package food.xinyuan.seller.app.data.bean.response;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author lzd
 * @CreateDate 2018/1/9
 */
public class OrderGoods {

    /**
     * orderGoodsId : 7759
     * orderId : 5018
     * goodsSpecificationId : 9354
     * goodsId : 9095
     * goodsName : 商品名称(规格名)
     * goodsPrice : 234.0
     * goodsCount : 1
     * amount : 234.0
     * oldAmount : 234.0
     * addTime : 1515485273000
     * goodsContent : 属性值1 / 33
     */

    private long orderGoodsId;
    private long orderId;
    private int goodsSpecificationId;
    private int goodsId;
    private String goodsName;
    private double goodsPrice;
    private int goodsCount;
    private double amount;
    private double oldAmount;
    private long addTime;
    private String goodsContent;

    public long getOrderGoodsId() {
        return orderGoodsId;
    }

    public void setOrderGoodsId(long orderGoodsId) {
        this.orderGoodsId = orderGoodsId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public int getGoodsSpecificationId() {
        return goodsSpecificationId;
    }

    public void setGoodsSpecificationId(int goodsSpecificationId) {
        this.goodsSpecificationId = goodsSpecificationId;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
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

    public int getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(int goodsCount) {
        this.goodsCount = goodsCount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getOldAmount() {
        return oldAmount;
    }

    public void setOldAmount(double oldAmount) {
        this.oldAmount = oldAmount;
    }

    public long getAddTime() {
        return addTime;
    }

    public void setAddTime(long addTime) {
        this.addTime = addTime;
    }

    public String getGoodsContent() {
        return goodsContent;
    }

    public void setGoodsContent(String goodsContent) {
        this.goodsContent = goodsContent;
    }
}
