package food.xinyuan.seller.app.data.bean.response;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author lzd
 * @CreateDate 2017/12/29
 */
public class GoodsCategory {

    /**
     * goodsCategoryId : 2107
     * goodsCategoryName : 小吃
     * sortOrder : 0
     */

    private int goodsCategoryId;
    private String goodsCategoryName;
    private int sortOrder;

    public int getGoodsCategoryId() {
        return goodsCategoryId;
    }

    public void setGoodsCategoryId(int goodsCategoryId) {
        this.goodsCategoryId = goodsCategoryId;
    }

    public String getGoodsCategoryName() {
        return goodsCategoryName;
    }

    public void setGoodsCategoryName(String goodsCategoryName) {
        this.goodsCategoryName = goodsCategoryName;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }
}
