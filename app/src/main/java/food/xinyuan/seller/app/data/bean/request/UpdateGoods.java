package food.xinyuan.seller.app.data.bean.request;

import java.util.List;

import food.xinyuan.seller.app.data.bean.response.GoodsInfo;
import food.xinyuan.seller.app.data.bean.response.GoodsProperty;
import food.xinyuan.seller.app.data.bean.response.GoodsSpec;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author lzd
 * @CreateDate 2018/1/18
 */
public class UpdateGoods {

    public UpdateGoods(GoodsInfo info, List<Long> goodsCategoryIdList, List<GoodsProperty> goodsPropertys,
                       List<GoodsSpec> addSpecs, List<Long> deleteSpecIds, List<GoodsSpec> updateSpecs) {
        this.info = info;
        this.goodsCategoryIdList = goodsCategoryIdList;
        this.goodsPropertys = goodsPropertys;
        this.addSpecs = addSpecs;
        this.deleteSpecIds = deleteSpecIds;
        this.updateSpecs = updateSpecs;
    }

    /**
     * addSpecs : [{"boxesMoney":0,"boxesNumber":0,"goodsId":0,"goodsSpecificationId":0,"goodsSpecificationName":"string","goodsSpecificationPrice":0,"infiniteInventory":false,"stock":0}]
     * deleteSpecIds : [0]
     * goodsCategoryIdList : [0]
     * goodsId : 0
     * goodsPropertys : [{"goodsId":0,"goodsPropertyId":0,"goodsPropertyName":"string","goodsPropertyValueList":[{"value":"string"}]}]
     * info : {"goodsContent":"string","goodsImgUrl":"string","goodsName":"string","goodsStatus":"PUTAWAY","goodsTag":"string"}
     * updateSpecs : [{"boxesMoney":0,"boxesNumber":0,"goodsId":0,"goodsSpecificationId":0,"goodsSpecificationName":"string","goodsSpecificationPrice":0,"infiniteInventory":false,"stock":0}]
     */

    private GoodsInfo info;
    private java.util.List<Long> goodsCategoryIdList;
    private java.util.List<GoodsProperty> goodsPropertys;
    private java.util.List<GoodsSpec> addSpecs;

    private java.util.List<Long> deleteSpecIds;
    private java.util.List<GoodsSpec> updateSpecs;
}
