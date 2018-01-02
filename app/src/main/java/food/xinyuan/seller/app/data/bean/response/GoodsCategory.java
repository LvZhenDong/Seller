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

    /**
     * goodsCategoryId : 2107
     * goodsCategoryName : 小吃
     * sortOrder : 0
     */

    private int goodsCategoryId;
    private String goodsCategoryName;
    private int sortOrder;
    //是否被选择
    private boolean checked;

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

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
