package food.xinyuan.seller.app.data.bean.response;

import java.util.List;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author hq
 * @CreateDate 2017/12/27
 */
public class SellerInfo {


    /**
     * sellerId : 1
     * sellerName : 15828064744
     * registrationTime : 1505803515000
     * lastLoginTime : 1516002616087
     * initedSecretkey : true
     * shopList : [{"shopId":1189,"shopName":"奎奎甜饼店","address":"四川省成都市金牛区四川省成都市金牛区九里堤街道交大智能小区二期交大智能小区2期"},{"shopId":61,"shopName":"鑫圆共享测试店铺(不对外点餐)","address":"四川省成都市青羊区通威国际中心"}]
     */

    private int sellerId;
    private String sellerName;
    private long registrationTime;
    private long lastLoginTime;
    private boolean initedSecretkey;
    private List<ShopListBean> shopList;

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public long getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(long registrationTime) {
        this.registrationTime = registrationTime;
    }

    public long getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public boolean isInitedSecretkey() {
        return initedSecretkey;
    }

    public void setInitedSecretkey(boolean initedSecretkey) {
        this.initedSecretkey = initedSecretkey;
    }

    public List<ShopListBean> getShopList() {
        return shopList;
    }

    public void setShopList(List<ShopListBean> shopList) {
        this.shopList = shopList;
    }

    public static class ShopListBean {
        /**
         * shopId : 1189
         * shopName : 奎奎甜饼店
         * address : 四川省成都市金牛区四川省成都市金牛区九里堤街道交大智能小区二期交大智能小区2期
         */

        private int shopId;
        private String shopName;
        private String address;

        public int getShopId() {
            return shopId;
        }

        public void setShopId(int shopId) {
            this.shopId = shopId;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
}
