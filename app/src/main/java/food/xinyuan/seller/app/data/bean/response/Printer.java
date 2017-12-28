package food.xinyuan.seller.app.data.bean.response;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author lzd
 * @CreateDate 2017/12/28
 */
public class Printer {

    /**
     * printerId : 1
     * shopId : 61
     * deviceName : 易联云K4(4004542401)
     * deviceId : 4004542401
     * deviceSecretKey : p4qi3xwyv72y
     * printerType : CLOUD_YILIANYUN_K4
     * printerPageType : MM58
     * printerStatus : ONLINE
     * copies : 1
     */

    private int printerId;
    private int shopId;
    private String deviceName;
    private String deviceId;
    private String deviceSecretKey;
    private String printerType;
    private String printerPageType;
    private String printerStatus;
    private int copies;

    public int getPrinterId() {
        return printerId;
    }

    public void setPrinterId(int printerId) {
        this.printerId = printerId;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceSecretKey() {
        return deviceSecretKey;
    }

    public void setDeviceSecretKey(String deviceSecretKey) {
        this.deviceSecretKey = deviceSecretKey;
    }

    public String getPrinterType() {
        return printerType;
    }

    public void setPrinterType(String printerType) {
        this.printerType = printerType;
    }

    public String getPrinterPageType() {
        return printerPageType;
    }

    public void setPrinterPageType(String printerPageType) {
        this.printerPageType = printerPageType;
    }

    public String getPrinterStatus() {
        return printerStatus;
    }

    public void setPrinterStatus(String printerStatus) {
        this.printerStatus = printerStatus;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }
}
