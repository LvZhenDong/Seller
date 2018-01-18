package food.xinyuan.seller.app.utils;

import android.os.Environment;

/**
 * Created by f-x on 2017/12/9  11:02
 * Description
 */

public class Constant {

    public static final String IMAGE = "http://uploads.test.gongxiangdiancan.com";

    public static final int PAGE_SIZE = 10;
    public static final int SHOULD_LOGIN = 001001;//需要登陆.

    //商品状态
    public static final String PUTAWAY = "PUTAWAY"; //上架
    public static final String SOLD_OUT = "SOLD_OUT";   //下架

    //店铺类型
    public static final String SHOP_TYPE_TAKEOUT = "TAKEOUT";//外卖

    //通知状态
    public static final String NOTICE_ORDER_STATUS_CANCEL = "ORDER_CANCELLATION";  //订单取消
    public static final String NOTICE_ORDER_STATUS_CREATE = "ORDER_CREATE";        //新订单
    public static final String NOTICE_ORDER_STATUS_FINISHED = "ORDER_FINISHED";    //订单完成

    //订单状态
    public static final String ORDER_STATUS_NEW = "PAYED";//新订单
    public static final String ORDER_STATUS_RECEIPT = "MERCHANT_CONFIRM_RECEIPT"; //已接单
    public static final String ORDER_STATUS_SHIPPING = "SHIPPING";        //配送中
    public static final String ORDER_STATUS_FINISHED = "TRANSACT_FINISHED";   //已完成
    public static final String ORDER_STATUS_CANCELED = "CANCELLATION";        //已取消

    //活动类型
    public static final String ACTIVITY_TYPE_COMPLIMENTARY = "COMPLIMENTARY";//惠
    public static final String ACTIVITY_TYPE_SALE = "SALE";//折
    public static final String ACTIVITY_TYPE_DELGOLD = "DELGOLD";//减
    public static final String ACTIVITY_TYPE_FIRST = "FIRST";//首
    public static final String ACTIVITY_TYPE_SPECIFIC = "SPECIFIC";//其

    //打印机状态
    public static final String PRINTER_STATUS_ONLINE = "ONLINE";    //在线
    public static final String PRINTER_STATUS_UNBIND = "UNBIND";    //未绑定

    /**
     * APP文件及缓存路径
     */

    public static final String ROOT_DIR;


    static {
        if ("mounted".equals(Environment.getExternalStorageState())) {
            ROOT_DIR = Environment.getExternalStorageDirectory().getAbsolutePath();
        } else {
            ROOT_DIR = Environment.getRootDirectory().getAbsolutePath();
        }
    }

    public static final String SYSTEM_INIT_FILE_NAME = "sysini";
    public static final String APP_DIR = (ROOT_DIR + "/xinyuanRider");
    public static final String CACHE_DIR = (APP_DIR + "/cachePic");
    public static final String LOG_DIR = (APP_DIR + "/log");
    public static final String APK_DIR = (APP_DIR + "/apk");

    public static final int SNACK_NORMAL = 0001;
    public static final int SNACK_WARING = 0002;
    public static final int SNACK_ERROR = 0003;
}
