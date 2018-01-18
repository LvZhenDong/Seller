package food.xinyuan.seller.app.data.bean.response;

import java.util.List;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author lzd
 * @CreateDate 2018/1/18
 */
public class GoodsDetail {
    private Goods goods;
    private long goodsId;
    private List<Long> goodsCategoryIdList;

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(long goodsId) {
        this.goodsId = goodsId;
    }

    public List<Long> getGoodsCategoryIdList() {
        return goodsCategoryIdList;
    }

    public void setGoodsCategoryIdList(List<Long> goodsCategoryIdList) {
        this.goodsCategoryIdList = goodsCategoryIdList;
    }
}
