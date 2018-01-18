package food.xinyuan.seller.app.data.bean.response;

import android.text.TextUtils;

import java.util.List;

import food.xinyuan.seller.app.utils.Constant;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author lzd
 * @CreateDate 2017/12/29
 */
public class Goods {

    /**
     * goodsId : 9055
     * shopId : 61
     * goodsName : 珍珠奶茶1
     * goodsPrice : 15
     * goodsAddTime : 1513582645000
     * goodsStatus : PUTAWAY
     * goodsContent : 偶买噶的
     * goodsImgUrl : /goods/20171218153709727.jpg
     * goodsSales : 4
     * goodsRateApprise : 0
     * shopName : 鑫圆共享测试店铺(不对外点餐)
     * showSpecSelecter : false
     * goodsClassNames : 小吃
     * goodsSpecifications : [{"goodsSpecificationId":9055,"goodsId":9055,"goodsSpecificationName":"","goodsSpecificationPrice":15,"infiniteInventory":true,"boxesNumber":1,"boxesMoney":1}]
     * goodsPropertys : [{"goodsPropertyId":234,"goodsId":9055,"goodsPropertyName":"甜度","goodsPropertyValueList":[{"value":"三分甜"},{"value":"正常甜"},{"value":"五分甜"},{"value":"无糖"}]},{"goodsPropertyId":235,"goodsId":9055,"goodsPropertyName":"加料","goodsPropertyValueList":[{"value":"X"},{"value":"加盐巴"},{"value":"加渣渣"}]},{"goodsPropertyId":236,"goodsId":9055,"goodsPropertyName":"fuck","goodsPropertyValueList":[{"value":"F"},{"value":"U"},{"value":"C"},{"value":"K"}]}]
     */

    private Long goodsId;
    private int shopId;
    private String goodsName;
    private double goodsPrice;
    private long goodsAddTime;
    private String goodsStatus;
    private String goodsContent;
    private String goodsImgUrl;
    private int goodsSales;
    private double goodsRateApprise;
    private String shopName;
    private boolean showSpecSelecter;
    private String goodsClassNames;
    private List<GoodsSpec> goodsSpecifications;
    private List<GoodsProperty> goodsPropertys;

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
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

    public long getGoodsAddTime() {
        return goodsAddTime;
    }

    public void setGoodsAddTime(long goodsAddTime) {
        this.goodsAddTime = goodsAddTime;
    }

    public String getGoodsStatus() {
        return goodsStatus;
    }

    public boolean isPutAway(){
        return TextUtils.equals(Constant.PUTAWAY,getGoodsStatus());
    }

    public void setPutAway(boolean isPutAway){
        setGoodsStatus(isPutAway? Constant.PUTAWAY: Constant.SOLD_OUT);
    }

    public void setGoodsStatus(String goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

    public String getGoodsContent() {
        return goodsContent;
    }

    public void setGoodsContent(String goodsContent) {
        this.goodsContent = goodsContent;
    }

    public String getGoodsImgUrl() {
        return goodsImgUrl;
    }

    public void setGoodsImgUrl(String goodsImgUrl) {
        this.goodsImgUrl = goodsImgUrl;
    }

    public int getGoodsSales() {
        return goodsSales;
    }

    public void setGoodsSales(int goodsSales) {
        this.goodsSales = goodsSales;
    }

    public double getGoodsRateApprise() {
        return goodsRateApprise;
    }

    public void setGoodsRateApprise(double goodsRateApprise) {
        this.goodsRateApprise = goodsRateApprise;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public boolean isShowSpecSelecter() {
        return showSpecSelecter;
    }

    public void setShowSpecSelecter(boolean showSpecSelecter) {
        this.showSpecSelecter = showSpecSelecter;
    }

    public String getGoodsClassNames() {
        return goodsClassNames;
    }

    public void setGoodsClassNames(String goodsClassNames) {
        this.goodsClassNames = goodsClassNames;
    }

    public List<GoodsSpec> getGoodsSpecifications() {
        return goodsSpecifications;
    }

    public void setGoodsSpecifications(List<GoodsSpec> goodsSpecs) {
        this.goodsSpecifications = goodsSpecs;
    }

    public List<GoodsProperty> getGoodsPropertys() {
        return goodsPropertys;
    }

    public void setGoodsPropertys(List<GoodsProperty> goodsPropertys) {
        this.goodsPropertys = goodsPropertys;
    }
}
