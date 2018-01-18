package food.xinyuan.seller.app.data.bean.request;

import java.util.List;

import food.xinyuan.seller.app.data.bean.response.GoodsInfo;
import food.xinyuan.seller.app.data.bean.response.GoodsProperty;
import food.xinyuan.seller.app.data.bean.response.GoodsSpec;
import food.xinyuan.seller.app.utils.Constant;

/**
 * <p>
 * Description：添加商品
 * </p>
 *
 * @author lzd
 * @CreateDate 2018/1/2
 */
public class AddGoods {

    public AddGoods(GoodsInfo info, List<Long> goodsCategoryIdList, List<GoodsProperty> goodsPropertys, List<GoodsSpec> addSpecs) {
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

    private GoodsInfo info;
    private List<Long> goodsCategoryIdList;
    private List<GoodsProperty> goodsPropertys;
    private List<GoodsSpec> addSpecs;

}
