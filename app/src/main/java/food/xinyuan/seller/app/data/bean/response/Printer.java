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

    //PRINTER_TYPE表示APP上显示的内容，PRINTER_TYPE_FLAG表示和服务器之间传输时的字段
    public final static String PRINTER_TYPE1 = "易联云K4";
    public final static String PRINTER_TYPE1_FLAG = "CLOUD_YILIANYUN_K4";
    public final static String PRINTER_TYPE2 = "USB";
    public final static String PRINTER_TYPE2_FLAG = "USB";

    //PRINTER_PAGE_TYPE表示APP上显示的内容，PRINTER_PAGE_TYPE_FLAG表示和服务器之间传输时的字段
    public final static String PRINTER_PAGE_TYPE1 = "58mm";
    public final static String PRINTER_PAGE_TYPE1_FLAG = "MM58";
    public final static String PRINTER_PAGE_TYPE2 = "80mm";
    public final static String PRINTER_PAGE_TYPE2_FLAG = "MM80";

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

    public Printer(String name, String deviceId, String deviceSecretKey, String printerType,
                   String printerPageType, String deviceRemark, int copies) {
        this.deviceName = name;
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

    //对printerType作次转换，因为APP上显示的内容和传输到服务器的值不一样
    public String getPrinterType() {
        String type = "";
        if (TextUtils.equals(PRINTER_TYPE1_FLAG, printerType)) {
            type = PRINTER_TYPE1;
        } else if (TextUtils.equals(PRINTER_TYPE2_FLAG, printerType)) {
            type = PRINTER_TYPE2;
        }
        return type;
    }

    public void setPrinterType(String printerType) {
        if (TextUtils.equals(PRINTER_TYPE1, printerType)) {
            this.printerType = PRINTER_TYPE1_FLAG;
        } else if (TextUtils.equals(PRINTER_TYPE2, printerType)) {
            this.printerType = PRINTER_TYPE2_FLAG;
        }
    }

    public String getPrinterPageType() {
        String pageType = "";
        if (TextUtils.equals(PRINTER_PAGE_TYPE2_FLAG, printerPageType)) {
            pageType = PRINTER_PAGE_TYPE2;
        } else if (TextUtils.equals(PRINTER_PAGE_TYPE1_FLAG, printerPageType)) {
            pageType = PRINTER_PAGE_TYPE1;
        }
        return pageType;
    }

    public void setPrinterPageType(String pageType) {
        if (TextUtils.equals(PRINTER_PAGE_TYPE2, pageType)) {
            this.printerPageType = PRINTER_PAGE_TYPE2_FLAG;
        } else if (TextUtils.equals(PRINTER_PAGE_TYPE1, pageType)) {
            this.printerPageType = PRINTER_PAGE_TYPE1_FLAG;
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
