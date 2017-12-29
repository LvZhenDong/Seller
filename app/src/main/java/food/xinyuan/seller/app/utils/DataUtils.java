package food.xinyuan.seller.app.utils;

import android.content.Context;
import android.os.Build;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.util.SparseLongArray;

import com.jess.arms.base.App;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Pattern;

import food.xinyuan.seller.app.data.bean.response.LoginResponse;

/**
 * Created by f-x on 2017/12/12  09:36
 * Description
 */

public class DataUtils {
    /**
     * 檢查Token
     *
     * @param context
     * @return
     */
    public static boolean checkToken(Context context) {
        if (getToken(context) != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取Token
     *
     * @param context
     * @return
     */
    public static String getToken(Context context) {
        return (String) ((App) context.getApplicationContext()).getAppComponent().extras().get("token");
    }

    /**
     * 设置Token
     *
     * @param context
     * @param token
     */
    public static void setToken(Context context, String token) {
        ((App) context.getApplicationContext()).getAppComponent().extras().put("token", token);
    }
//
//    /**
//     * 获取定位
//     *
//     * @param context
//     * @return
//     */
//    public static GeoBean getRiderLocation(Context context) {
//        return (GeoBean) ((App) context.getApplicationContext()).getAppComponent().extras().get("LOCATION");
//    }
//
//    /**
//     * 设置定位
//     *
//     * @param context
//     * @param geoBean
//     */
//    public static void setRiderLocation(Context context, GeoBean geoBean) {
//        ((App) context.getApplicationContext()).getAppComponent().extras().put("LOCATION", geoBean);
//    }
//
    /**
     * 获取User
     *
     * @param context
     * @return
     */
    public static LoginResponse getUser(Context context) {
        return (LoginResponse) ((App) context.getApplicationContext()).getAppComponent().extras().get("user");
    }

    /**
     * t
     * 设置User
     *
     * @param context
     * @param user
     */
    public static void setUser(Context context, LoginResponse user) {
        ((App) context.getApplicationContext()).getAppComponent().extras().put("user", user);
    }


    /**
     * 验证手机号码（支持国际格式，+86135xxxx...（中国内地），+00852137xxxx...（中国香港））
     *
     * @param mobile 移动、联通、电信运营商的号码段
     *               <p>移动的号段：134(0-8)、135、136、137、138、139、147、150、151、152、157、158、159、187、188</p>
     *               <p>联通的号段：130、131、132、155、156、185、186</p>
     *               <p>电信的号段：133、153、180、189</p>
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkMobile(String mobile) {
        String regex = "(\\+\\d+)?1[34578]\\d{9}$";
        return Pattern.matches(regex, mobile);
    }

    /**
     * 判断对象是否为null或长度数量为0
     *
     * @param obj 对象
     * @return {@code true}: 为空<br>{@code false}: 不为空
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj instanceof String && obj.toString().length() == 0) {
            return true;
        }
        if (obj.getClass().isArray() && Array.getLength(obj) == 0) {
            return true;
        }
        if (obj instanceof Collection && ((Collection) obj).isEmpty()) {
            return true;
        }
        if (obj instanceof Map && ((Map) obj).isEmpty()) {
            return true;
        }
        if (obj instanceof SparseArray && ((SparseArray) obj).size() == 0) {
            return true;
        }
        if (obj instanceof SparseBooleanArray && ((SparseBooleanArray) obj).size() == 0) {
            return true;
        }
        if (obj instanceof SparseIntArray && ((SparseIntArray) obj).size() == 0) {
            return true;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            if (obj instanceof SparseLongArray && ((SparseLongArray) obj).size() == 0) {
                return true;
            }
        }
        return false;
    }
//
//    public static String getOrderStatusTitle(String orderStatus) {
//        String orderStatusTitle = "";
//        switch (orderStatus) {
//            case ConstantUtil.WAIT_PICKUP:
//                orderStatusTitle = "待到店";
//                break;
//            case ConstantUtil.PICKUPING:
//                orderStatusTitle = "待取餐";
//                break;
//            case ConstantUtil.SHIPPING:
//                orderStatusTitle = "待送达";
//                break;
//        }
//        return orderStatusTitle;
//
//    }
//
//    /**
//     * 比较订单
//     * @param oldList
//     * @param newList
//     * @return
//     */
//    public static List<OrderInfoBean> compareDataNew(List<OrderInfoBean> oldList, List<OrderInfoBean> newList) {
//        List<OrderInfoBean> newOrderList = new ArrayList<>();
//        newOrderList.addAll(newList);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            oldList.stream().forEach(p -> {
//                if (newOrderList.contains(p)) {
//                    newOrderList.remove(p);
//                }
//            });
//        } else {
//            for (int i = 0; i < newOrderList.size(); i++) {
//                for (int j = 0; j < oldList.size(); j++) {
//                    if (newOrderList.get(i).getOrderId() == oldList.get(j).getOrderId()) {
//                        newOrderList.remove(newOrderList.get(i));
//                        continue;
//                    }
//                }
//            }
//        }
//        return newOrderList;
//    }

}
