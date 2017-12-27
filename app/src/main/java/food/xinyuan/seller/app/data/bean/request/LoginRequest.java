package food.xinyuan.seller.app.data.bean.request;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author lzd
 * @CreateDate 2017/12/27
 */
public class LoginRequest {

    /**
     * cid : string
     * code : string
     * ios : false
     * sellerName : string
     */

    private String cid;
    private String code;
    private boolean ios;
    private String sellerName;

    public LoginRequest(String sellerName, String code) {
        this.sellerName = sellerName;
        this.code = code;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isIos() {
        return ios;
    }

    public void setIos(boolean ios) {
        this.ios = ios;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }
}
