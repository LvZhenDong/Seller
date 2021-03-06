package food.xinyuan.seller.app.data.bean.response;

import java.io.Serializable;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author lzd
 * @CreateDate 2017/12/29
 */
public class GoodsCategory implements Serializable {

    public GoodsCategory(String goodsCategoryName) {
        this.goodsCategoryName = goodsCategoryName;
    }

    /**
     * goodsCategoryId : 2107
     * goodsCategoryName : 小吃
     * sortOrder : 0
     */

    private long goodsCategoryId;
    private String goodsCategoryName;
    private int sortOrder;
    //是否被选择
    private boolean checked;

    public long getGoodsCategoryId() {
        return goodsCategoryId;
    }

    public void setGoodsCategoryId(long goodsCategoryId) {
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

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
