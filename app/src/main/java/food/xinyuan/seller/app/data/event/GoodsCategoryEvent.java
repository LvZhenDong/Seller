package food.xinyuan.seller.app.data.event;

import java.util.List;

import food.xinyuan.seller.app.data.bean.response.GoodsCategory;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author lzd
 * @CreateDate 2018/1/1
 */
public class GoodsCategoryEvent {
    List<GoodsCategory> list;

    public GoodsCategoryEvent(List<GoodsCategory> list) {
        this.list = list;
    }

    public List<GoodsCategory> getList() {
        return list;
    }

    public void setList(List<GoodsCategory> list) {
        this.list = list;
    }
}
