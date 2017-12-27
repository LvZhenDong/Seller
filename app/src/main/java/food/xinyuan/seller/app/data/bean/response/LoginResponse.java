package food.xinyuan.seller.app.data.bean.response;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author lzd
 * @CreateDate 2017/12/27
 */
public class LoginResponse {

    /**
     * jwt : string
     * seller : {"initedSecretkey":false,"lastLoginTime":"2017-12-27T06:33:32.909Z","registrationTime":"2017-12-27T06:33:32.909Z","sellerId":0,"sellerName":"string","shopId":0}
     * shopName : string
     */

    private String jwt;
    private SellerInfo seller;
    private String shopName;

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public SellerInfo getSeller() {
        return seller;
    }

    public void setSeller(SellerInfo seller) {
        this.seller = seller;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }


}
