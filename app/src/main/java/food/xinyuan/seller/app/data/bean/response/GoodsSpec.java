package food.xinyuan.seller.app.data.bean.response;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author lzd
 * @CreateDate 2018/1/18
 */
public class GoodsSpec {
    private boolean isUpdate;   //是修改的规格，还是添加的规格
    private int updatePos;

    public int getUpdatePos() {
        return updatePos;
    }

    public void setUpdatePos(int updatePos) {
        this.updatePos = updatePos;
    }

    public boolean isUpdate() {
        return isUpdate;
    }

    public void setUpdate(boolean update) {
        isUpdate = update;
    }

    public GoodsSpec(boolean infiniteInventory, double boxesMoney, int boxesNumber, String goodsSpecificationName, double goodsSpecificationPrice, int stock) {
        this.infiniteInventory = infiniteInventory;
        this.boxesMoney = boxesMoney;
        this.boxesNumber = boxesNumber;
        this.goodsSpecificationName = goodsSpecificationName;
        this.goodsSpecificationPrice = goodsSpecificationPrice;
        this.stock = stock;
    }

    /**
     * infiniteInventory : true
     * boxesMoney : 5.5
     * boxesNumber : 55
     * goodsSpecificationName : 规格名字
     * goodsSpecificationPrice : 12
     * stock : 2
     */

    private boolean infiniteInventory;
    private double boxesMoney;
    private int boxesNumber;
    private String goodsSpecificationName;
    private double goodsSpecificationPrice;
    private int stock;
    private long goodsSpecificationId;

    public long getGoodsSpecificationId() {
        return goodsSpecificationId;
    }

    public void setGoodsSpecificationId(long goodsSpecificationId) {
        this.goodsSpecificationId = goodsSpecificationId;
    }

    public boolean isInfiniteInventory() {
        return infiniteInventory;
    }

    public void setInfiniteInventory(boolean infiniteInventory) {
        this.infiniteInventory = infiniteInventory;
    }

    public double getBoxesMoney() {
        return boxesMoney;
    }

    public void setBoxesMoney(double boxesMoney) {
        this.boxesMoney = boxesMoney;
    }

    public int getBoxesNumber() {
        return boxesNumber;
    }

    public void setBoxesNumber(int boxesNumber) {
        this.boxesNumber = boxesNumber;
    }

    public String getGoodsSpecificationName() {
        return goodsSpecificationName;
    }

    public void setGoodsSpecificationName(String goodsSpecificationName) {
        this.goodsSpecificationName = goodsSpecificationName;
    }

    public double getGoodsSpecificationPrice() {
        return goodsSpecificationPrice;
    }

    public void setGoodsSpecificationPrice(double goodsSpecificationPrice) {
        this.goodsSpecificationPrice = goodsSpecificationPrice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
