package food.xinyuan.seller.app.data.bean.response;

import food.xinyuan.seller.app.utils.Constant;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author lzd
 * @CreateDate 2018/1/18
 */
public class GoodsInfo {
    public GoodsInfo(String goodsContent, String goodsImgUrl, String goodsName, boolean isPutOn) {
        this.goodsContent = goodsContent;
        this.goodsImgUrl = goodsImgUrl;
        this.goodsName = goodsName;
        this.goodsStatus = isPutOn? Constant.PUTAWAY: Constant.SOLD_OUT;
    }

    /**
     * goodsContent : 商品简介
     * goodsImgUrl : /goods/20180102155028772.jpg
     * goodsName : 名称
     * goodsStatus : PUTAWAY
     */

    private String goodsContent;
    private String goodsImgUrl;
    private String goodsName;
    private String goodsStatus;

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

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(String goodsStatus) {
        this.goodsStatus = goodsStatus;
    }
}
