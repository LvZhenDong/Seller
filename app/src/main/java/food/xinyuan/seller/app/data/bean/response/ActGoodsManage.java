package food.xinyuan.seller.app.data.bean.response;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author lzd
 * @CreateDate 2018/1/19
 */
public class ActGoodsManage {
    private boolean checked;
    private Goods goods;

    private ActGoods actGoods;

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public ActGoods getActGoods() {
        if(actGoods == null){
            actGoods=new ActGoods();
        }
        return actGoods;
    }

    public void setActGoods(ActGoods actGoods) {
        this.actGoods = actGoods;
    }
}
