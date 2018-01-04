package food.xinyuan.seller.app.data.bean.response;

import android.text.TextUtils;

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
    private String deviceRemark;
    private int copies;

    public Printer(String name,String deviceId, String deviceSecretKey, String printerType,
                   String printerPageType, String deviceRemark, int copies) {
        this.deviceName=name;
        this.deviceId = deviceId;
        this.deviceSecretKey = deviceSecretKey;
        setPrinterType(printerType);
        setPrinterPageType(printerPageType);
        this.deviceRemark = deviceRemark;
        this.copies = copies;
    }

    public String getDeviceRemark() {
        return deviceRemark;
    }

    public void setDeviceRemark(String deviceRemark) {
        this.deviceRemark = deviceRemark;
    }

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
        String type="";
        if (TextUtils.equals("CLOUD_YILIANYUN_K4", printerType)) {
            type = "易联云K4";
        } else if (TextUtils.equals("USB", printerType)) {
            type = "USB";
        }
        return type;
    }

    public void setPrinterType(String printerType) {
        if (TextUtils.equals("易联云K4", printerType)) {
            this.printerType = "CLOUD_YILIANYUN_K4";
            } else if (TextUtils.equals("USB", printerType)) {
            this.printerType = "USB";
        }
    }

    public String getPrinterPageType() {
        String pageType="";
        if (TextUtils.equals("MM80", printerPageType)) {
            pageType = "80mm";
        } else if (TextUtils.equals("MM58", printerPageType)) {
            pageType = "58mm";
        }
        return pageType;
    }

    public void setPrinterPageType(String pageType) {
        if (TextUtils.equals("80mm", pageType)) {
            this.printerPageType = "MM80";
        } else if (TextUtils.equals("58mm", pageType)) {
            this.printerPageType = "MM58";
        }

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
