package food.xinyuan.seller.app.data.bean.response;

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
     * initedSecretkey : false
     * lastLoginTime : 2017-12-27T06:33:32.909Z
     * registrationTime : 2017-12-27T06:33:32.909Z
     * sellerId : 0
     * sellerName : string
     * shopId : 0
     */

    private boolean initedSecretkey;
    private String lastLoginTime;
    private String registrationTime;
    private int sellerId;
    private String sellerName;
    private int shopId;

    public boolean isInitedSecretkey() {
        return initedSecretkey;
    }

    public void setInitedSecretkey(boolean initedSecretkey) {
        this.initedSecretkey = initedSecretkey;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(String registrationTime) {
        this.registrationTime = registrationTime;
    }

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

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }
}
