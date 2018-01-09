package food.xinyuan.seller.app.data.bean.request;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author lzd
 * @CreateDate 2018/1/9
 */
public class CancelOrder {

    public CancelOrder(String cancelContent, long orderId) {
        this.cancelContent = cancelContent;
        this.orderId = orderId;
    }

    /**
     * cancelContent : you
     * orderId : 4982
     */


    private String cancelContent;
    private long orderId;

    public String getCancelContent() {
        return cancelContent;
    }

    public void setCancelContent(String cancelContent) {
        this.cancelContent = cancelContent;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }
}
