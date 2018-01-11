package food.xinyuan.seller.app.data.bean.response;

import com.google.gson.annotations.SerializedName;

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
    @SerializedName(value = "finishDay", alternate = {"finishDayTime"})
    private long finishDay;
    @SerializedName(value = "newCustomerCount", alternate = {"orderQuantity","goodsSaleCount"})
    private int intData;
    @SerializedName("turnoverCount")
    private float floatData;

    public float getFloatData() {
        return floatData;
    }

    public long getFinishDay() {
        return finishDay;
    }

    public int getIntData() {
        return intData;
    }
}
