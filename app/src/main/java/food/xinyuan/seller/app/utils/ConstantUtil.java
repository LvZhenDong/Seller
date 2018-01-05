package food.xinyuan.seller.app.utils;

import android.os.Environment;

/**
 * Created by f-x on 2017/12/9  11:02
 * Description
 */

public class ConstantUtil {

    public static final String IMAGE = "http://uploads.test.gongxiangdiancan.com";

    /**
     * 订单状态
     */
    public static final String WAIT_ALLOCATE = "WAIT_ALLOCATE";                  //待分配
    public static final String WAIT_PICKUP = "WAIT_PICKUP";                      //待取餐
    public static final String PICKUPING = "PICKUPING";                          //取货中
    public static final String SHIPPING = "SHIPPING";                            //送货中
    public static final String DELIVERED = "DELIVERED";                          //已送达
    public static final String TRANSACT_FINISHED = "TRANSACT_FINISHED";         //配送结束
    public static final String CANCELLATION = "CANCELLATION";                    //配送取消


    public static final String FINISHORDER = "FINISHORDER";                   //已完成
    public static final String EXCEPTION = "EXCEPTION";              //异常订单

    public static final int PAGE_SIZE = 10;
    public static final int SHOULD_LOGIN = 001001;//需要登陆.
    //骑手状态
    public static final String START_WORK = "START_WORK";                   //上线
    public static final String STOP_WORK = "STOP_WORK";              //下线
    public static final String REST = "REST";              //

    //商品状态
    public static final String PUTAWAY = "PUTAWAY"; //上架
    public static final String SOLD_OUT = "SOLD_OUT";   //下架

    //通知状态
    public static final String ORDER_STATUS_CANCEL = "ORDER_CANCELLATION";
    public static final String ORDER_STATUS_CREATE = "ORDER_CREATE";
    public static final String ORDER_STATUS_FINISHED = "ORDER_FINISHED";

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
