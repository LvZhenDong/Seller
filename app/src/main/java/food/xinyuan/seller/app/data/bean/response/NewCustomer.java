package food.xinyuan.seller.app.data.bean.response;

/**
 * <p>
 * Description：每天的新客户
 * </p>
 *
 * @author lzd
 * @CreateDate 2018/1/10
 */
public class NewCustomer {

    /**
     * finishDay : 1515513600000
     * newCustomerCount : 1
     */

    private long finishDay;
    private int newCustomerCount;

    public long getFinishDay() {
        return finishDay;
    }

    public void setFinishDay(long finishDay) {
        this.finishDay = finishDay;
    }

    public int getNewCustomerCount() {
        return newCustomerCount;
    }

    public void setNewCustomerCount(int newCustomerCount) {
        this.newCustomerCount = newCustomerCount;
    }
}
