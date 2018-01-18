package food.xinyuan.seller.app.data.bean.response;

import java.util.ArrayList;
import java.util.List;

import food.xinyuan.seller.app.utils.DataUtils;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author lzd
 * @CreateDate 2018/1/18
 */
public class GoodsProperty {
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
    public GoodsProperty(String goodsPropertyName, List<String> strings) {
        this.goodsPropertyName = goodsPropertyName;
        this.goodsPropertyValueList=getPropertyStrings(strings);
    }

    private List<GoodsPropertyValueListBean> getPropertyStrings(List<String> strings){
        List<GoodsPropertyValueListBean> list=new ArrayList<>();
        for (String str:strings) {
            list.add(new GoodsPropertyValueListBean(str));
        }
        return list;
    }

    public List<String> getStrings(){
        List<String> list=new ArrayList<>();
        if(!DataUtils.isEmpty(goodsPropertyValueList)){
            for (GoodsPropertyValueListBean item:goodsPropertyValueList) {
                list.add(item.getValue());
            }
        }
        return list;
    }
    /**
     * goodsPropertyId : 234
     * goodsId : 9055
     * goodsPropertyName : 甜度
     * goodsPropertyValueList : [{"value":"三分甜"},{"value":"正常甜"},{"value":"五分甜"},{"value":"无糖"}]
     */

    private int goodsPropertyId;
    private int goodsId;
    private String goodsPropertyName;
    private List<GoodsPropertyValueListBean> goodsPropertyValueList;

    public int getGoodsPropertyId() {
        return goodsPropertyId;
    }

    public void setGoodsPropertyId(int goodsPropertyId) {
        this.goodsPropertyId = goodsPropertyId;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsPropertyName() {
        return goodsPropertyName;
    }

    public void setGoodsPropertyName(String goodsPropertyName) {
        this.goodsPropertyName = goodsPropertyName;
    }

    public List<GoodsPropertyValueListBean> getGoodsPropertyValueList() {
        return goodsPropertyValueList;
    }

    public void setGoodsPropertyValueList(List<String> strings) {
        this.goodsPropertyValueList = getPropertyStrings(strings);
    }

    public static class GoodsPropertyValueListBean {
        public GoodsPropertyValueListBean(String value) {
            this.value = value;
        }
        /**
         * value : 三分甜
         */

        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
