package food.xinyuan.seller.app.data.bean.request;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Description：添加商品
 * </p>
 *
 * @author lzd
 * @CreateDate 2018/1/2
 */
public class AddGoods {

    public AddGoods(InfoBean info, List<Integer> goodsCategoryIdList, List<GoodsPropertysBean> goodsPropertys, List<AddSpecsBean> addSpecs) {
        this.info = info;
        this.goodsCategoryIdList = goodsCategoryIdList;
        this.goodsPropertys = goodsPropertys;
        this.addSpecs = addSpecs;
    }

    /**
     * info : {"goodsContent":"商品简介","goodsImgUrl":"/goods/20180102155028772.jpg","goodsName":"名称","goodsStatus":"PUTAWAY"}
     * goodsCategoryIdList : [2130,2131]
     * goodsPropertys : [{"goodsPropertyName":"属性名称","propValue":"","goodsPropertyValueList":[{"value":"属性1"},{"value":"属性2"}]}]
     * addSpecs : [{"infiniteInventory":true,"boxesMoney":5.5,"boxesNumber":55,"goodsSpecificationName":"规格名字","goodsSpecificationPrice":12,"stock":2}]
     */

    private InfoBean info;
    private List<Integer> goodsCategoryIdList;
    private List<GoodsPropertysBean> goodsPropertys;
    private List<AddSpecsBean> addSpecs;

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public List<Integer> getGoodsCategoryIdList() {
        return goodsCategoryIdList;
    }

    public void setGoodsCategoryIdList(List<Integer> goodsCategoryIdList) {
        this.goodsCategoryIdList = goodsCategoryIdList;
    }

    public List<GoodsPropertysBean> getGoodsPropertys() {
        return goodsPropertys;
    }

    public void setGoodsPropertys(List<GoodsPropertysBean> goodsPropertys) {
        this.goodsPropertys = goodsPropertys;
    }

    public List<AddSpecsBean> getAddSpecs() {
        return addSpecs;
    }

    public void setAddSpecs(List<AddSpecsBean> addSpecs) {
        this.addSpecs = addSpecs;
    }

    public static class InfoBean {
        public InfoBean(String goodsContent, String goodsImgUrl, String goodsName, boolean isPutOn) {
            this.goodsContent = goodsContent;
            this.goodsImgUrl = goodsImgUrl;
            this.goodsName = goodsName;
            this.goodsStatus = isPutOn?"PUTAWAY":"SOLD_OUT";
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

    public static class GoodsPropertysBean {
        public GoodsPropertysBean(String goodsPropertyName, List<String> strings) {
            this.goodsPropertyName = goodsPropertyName;
            goodsPropertyValueList=new ArrayList<>();
            for (String str:strings
                 ) {
                goodsPropertyValueList.add(new GoodsPropertyValueListBean(str));
            }
        }

        /**
         * goodsPropertyName : 属性名称
         * propValue :
         * goodsPropertyValueList : [{"value":"属性1"},{"value":"属性2"}]
         */

        private String goodsPropertyName;
        private String propValue;
        private List<GoodsPropertyValueListBean> goodsPropertyValueList;

        public String getGoodsPropertyName() {
            return goodsPropertyName;
        }

        public void setGoodsPropertyName(String goodsPropertyName) {
            this.goodsPropertyName = goodsPropertyName;
        }

        public String getPropValue() {
            return propValue;
        }

        public void setPropValue(String propValue) {
            this.propValue = propValue;
        }

        public List<GoodsPropertyValueListBean> getGoodsPropertyValueList() {
            return goodsPropertyValueList;
        }

        public void setGoodsPropertyValueList(List<GoodsPropertyValueListBean> goodsPropertyValueList) {
            this.goodsPropertyValueList = goodsPropertyValueList;
        }

        public static class GoodsPropertyValueListBean {
            public GoodsPropertyValueListBean(String value) {
                this.value = value;
            }

            /**
             * value : 属性1
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

    public static class AddSpecsBean {
        public AddSpecsBean(boolean infiniteInventory, double boxesMoney, int boxesNumber, String goodsSpecificationName, double goodsSpecificationPrice, int stock) {
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
}
