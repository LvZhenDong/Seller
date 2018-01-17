package food.xinyuan.seller.app.data.bean.response;

import android.text.TextUtils;

import food.xinyuan.seller.app.utils.Constant;

/**
 * <p>
 * Description：店铺统计数据
 * </p>
 *
 * @author lzd
 * @CreateDate 2017/12/27
 */
public class ShopStatistics {


    /**
     * shopId : 61
     * todayTurnover : 0
     * todayOrderQuantity : 0
     * availableBalance : 0.0
     * yesterdayTurnover : 0
     * yesterdayOrderQuantity : 0
     * amountWithdrawal : 0.0
     * printerStatus : ONLINE
     * operatingState : true
     */

    private int shopId;
    private double todayTurnover;
    private int todayOrderQuantity;
    private double availableBalance;
    private double yesterdayTurnover;
    private int yesterdayOrderQuantity;
    private double amountWithdrawal;
    private String printerStatus;
    private boolean operatingState;

    public String getPrinterStatusStr(){
        if(TextUtils.equals(printerStatus, Constant.PRINTER_STATUS_ONLINE)){
            return "在线";
        }else if(TextUtils.equals(printerStatus, Constant.PRINTER_STATUS_UNBIND)){
            return "未绑定";
        }else {
            //TODO 除了ONLINE UNBIND还有其他字段吗？
            return "离线";
        }
    }

    public boolean isPrinterOnLine(){
        return TextUtils.equals(printerStatus, Constant.PRINTER_STATUS_ONLINE);
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public double getTodayTurnover() {
        return todayTurnover;
    }

    public void setTodayTurnover(double todayTurnover) {
        this.todayTurnover = todayTurnover;
    }

    public int getTodayOrderQuantity() {
        return todayOrderQuantity;
    }

    public void setTodayOrderQuantity(int todayOrderQuantity) {
        this.todayOrderQuantity = todayOrderQuantity;
    }

    public double getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(double availableBalance) {
        this.availableBalance = availableBalance;
    }

    public double getYesterdayTurnover() {
        return yesterdayTurnover;
    }

    public void setYesterdayTurnover(double yesterdayTurnover) {
        this.yesterdayTurnover = yesterdayTurnover;
    }

    public int getYesterdayOrderQuantity() {
        return yesterdayOrderQuantity;
    }

    public void setYesterdayOrderQuantity(int yesterdayOrderQuantity) {
        this.yesterdayOrderQuantity = yesterdayOrderQuantity;
    }

    public double getAmountWithdrawal() {
        return amountWithdrawal;
    }

    public void setAmountWithdrawal(double amountWithdrawal) {
        this.amountWithdrawal = amountWithdrawal;
    }

    public String getPrinterStatus() {
        return printerStatus;
    }

    public void setPrinterStatus(String printerStatus) {
        this.printerStatus = printerStatus;
    }

    public boolean isOperatingState() {
        return operatingState;
    }

    public void setOperatingState(boolean operatingState) {
        this.operatingState = operatingState;
    }
}
