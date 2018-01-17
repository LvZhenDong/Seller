package food.xinyuan.seller.app.data.bean.response;

import android.text.TextUtils;

import java.util.List;

import food.xinyuan.seller.app.utils.Constant;
import food.xinyuan.seller.app.utils.XDateUtils;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author lzd
 * @CreateDate 2018/1/8
 */
public class Order {

    /**
     * orderId : 5017
     * orderNum : 20180108152933343
     * orderName : 满汉全席(大碗)
     * userId : 9
     * shopId : 61
     * shopName : 鑫圆共享测试店铺(不对外点餐)
     * phoneNum : 15828064744
     * addTime : 1515396574000
     * payTimeoutTime : 1515397474000
     * payTime : 1515396576000
     * cancelTime : 1515397501000
     * orderStatus : CANCELLATION
     * originalPrice : 22.0
     * originalGoodsPrice : 17.0
     * orderGoodsPrice : 15.0
     * favourablePrice : 1.0
     * orderPrice : 21.0
     * payment : BALANCE
     * paymentSN : 20180108152933343
     * orderReserve : {"orderId":5017}
     * orderTakeout : {"orderId":5017,"shippingFee":5,"freightDiscount":1,"originalFreight":5,"baseFreight":5,"timePremium":0,"distancePremium":0,"mealFee":1,"deliveryTime":1515399540000,"distributionType":"ARES"}
     * orderCancel : {"orderId":5017,"orderCancelReason":"OTHER","cancelContent":"等待接单超时取消订单","cancelType":"RECEIVING_TIMEOUT"}
     * orderContent :
     * isFinishPay : true
     * isAppraise : false
     * orderType : TAKEOUT
     * orderGoods : []
     * orderContact : {"orderId":5017,"contactId":219,"contactName":"余俊","contactPhone":"13558911947","address":"通威国际中心() ","houseNumber":"","longitude":104.06772316,"latitude":30.55056401,"gender":"MALE","simpleAddress":"通威国际中心","detailAddress":""}
     * orderActivitys : []
     * orderGoodsCount : 1
     * isRefund : true
     * orderInvoiceInfo : {"orderId":5017}
     */

    private long orderId;
    private String orderNum;
    private String orderName;
    private int userId;
    private int shopId;
    private String shopName;
    private String phoneNum;
    private long addTime;
    private long payTimeoutTime;
    private long payTime;
    private long cancelTime;
    private String orderStatus;
    private double originalPrice;
    private double originalGoodsPrice;
    private double orderGoodsPrice;
    private double favourablePrice;
    private double orderPrice;
    private String payment;
    private String paymentSN;
    private OrderReserveBean orderReserve;
    private OrderTakeoutBean orderTakeout;
    private OrderCancelBean orderCancel;
    private String orderContent;
    private boolean isFinishPay;
    private boolean isAppraise;
    private String orderType;
    private OrderContactBean orderContact;
    private int orderGoodsCount;
    private boolean isRefund;
    private OrderInvoiceInfoBean orderInvoiceInfo;
    private List<OrderGoods> orderGoods;
    private List<OrderActivitys> orderActivitys;

    public String getShortOrderNum() {
        return "#" + orderNum.substring(orderNum.length() - 4, orderNum.length());
    }

    public String getAddTimeStr() {
        return XDateUtils.millis2String(addTime);
    }

    public String getOrderTypeStr() {
        return TextUtils.equals("TAKEOUT", orderType) ? "外卖订单" : "预约订单";
    }

    public String getOrderStatusStr() {
        String result = "";
        if (TextUtils.equals(Constant.ORDER_STATUS_RECEIPT, orderStatus)) {
            result = "已接单";
        } else if (TextUtils.equals(Constant.ORDER_STATUS_SHIPPING, orderStatus)) {
            result = "配送中";
        } else if (TextUtils.equals(Constant.ORDER_STATUS_FINISHED, orderStatus)) {
            result = "已完成";
        } else if (TextUtils.equals(Constant.ORDER_STATUS_CANCELED, orderStatus)) {
            result = "已取消";
        } else if (TextUtils.equals(Constant.ORDER_STATUS_NEW, orderStatus)) {
            result = "新订单";
        }

        return result;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public long getAddTime() {
        return addTime;
    }

    public void setAddTime(long addTime) {
        this.addTime = addTime;
    }

    public long getPayTimeoutTime() {
        return payTimeoutTime;
    }

    public void setPayTimeoutTime(long payTimeoutTime) {
        this.payTimeoutTime = payTimeoutTime;
    }

    public long getPayTime() {
        return payTime;
    }

    public void setPayTime(long payTime) {
        this.payTime = payTime;
    }

    public long getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(long cancelTime) {
        this.cancelTime = cancelTime;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public double getOriginalGoodsPrice() {
        return originalGoodsPrice;
    }

    public void setOriginalGoodsPrice(double originalGoodsPrice) {
        this.originalGoodsPrice = originalGoodsPrice;
    }

    public double getOrderGoodsPrice() {
        return orderGoodsPrice;
    }

    public void setOrderGoodsPrice(double orderGoodsPrice) {
        this.orderGoodsPrice = orderGoodsPrice;
    }

    public double getFavourablePrice() {
        return favourablePrice;
    }

    public void setFavourablePrice(double favourablePrice) {
        this.favourablePrice = favourablePrice;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getPaymentSN() {
        return paymentSN;
    }

    public void setPaymentSN(String paymentSN) {
        this.paymentSN = paymentSN;
    }

    public OrderReserveBean getOrderReserve() {
        return orderReserve;
    }

    public void setOrderReserve(OrderReserveBean orderReserve) {
        this.orderReserve = orderReserve;
    }

    public OrderTakeoutBean getOrderTakeout() {
        return orderTakeout;
    }

    public void setOrderTakeout(OrderTakeoutBean orderTakeout) {
        this.orderTakeout = orderTakeout;
    }

    public OrderCancelBean getOrderCancel() {
        return orderCancel;
    }

    public void setOrderCancel(OrderCancelBean orderCancel) {
        this.orderCancel = orderCancel;
    }

    public String getOrderContent() {
        return TextUtils.isEmpty(orderContent) ? "无" : orderContent;
    }

    public void setOrderContent(String orderContent) {
        this.orderContent = orderContent;
    }

    public boolean isIsFinishPay() {
        return isFinishPay;
    }

    public void setIsFinishPay(boolean isFinishPay) {
        this.isFinishPay = isFinishPay;
    }

    public boolean isIsAppraise() {
        return isAppraise;
    }

    public void setIsAppraise(boolean isAppraise) {
        this.isAppraise = isAppraise;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public OrderContactBean getOrderContact() {
        return orderContact;
    }

    public void setOrderContact(OrderContactBean orderContact) {
        this.orderContact = orderContact;
    }

    public int getOrderGoodsCount() {
        return orderGoodsCount;
    }

    public void setOrderGoodsCount(int orderGoodsCount) {
        this.orderGoodsCount = orderGoodsCount;
    }

    public boolean isIsRefund() {
        return isRefund;
    }

    public void setIsRefund(boolean isRefund) {
        this.isRefund = isRefund;
    }

    public OrderInvoiceInfoBean getOrderInvoiceInfo() {
        return orderInvoiceInfo;
    }

    public void setOrderInvoiceInfo(OrderInvoiceInfoBean orderInvoiceInfo) {
        this.orderInvoiceInfo = orderInvoiceInfo;
    }

    public List<OrderGoods> getOrderGoods() {
        return orderGoods;
    }

    public void setOrderGoods(List<OrderGoods> orderGoods) {
        this.orderGoods = orderGoods;
    }

    public List<OrderActivitys> getOrderActivitys() {
        return orderActivitys;
    }

    public void setOrderActivitys(List<OrderActivitys> orderActivitys) {
        this.orderActivitys = orderActivitys;
    }

    public static class OrderReserveBean {
        /**
         * orderId : 5017
         */

        private int orderId;

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }
    }

    public static class OrderTakeoutBean {
        /**
         * orderId : 5017
         * shippingFee : 5.0
         * freightDiscount : 1.0
         * originalFreight : 5.0
         * baseFreight : 5.0
         * timePremium : 0.0
         * distancePremium : 0.0
         * mealFee : 1.0
         * deliveryTime : 1515399540000
         * distributionType : ARES
         */

        private int orderId;
        private double shippingFee;
        private double freightDiscount;
        private double originalFreight;
        private double baseFreight;
        private double timePremium;
        private double distancePremium;
        private double mealFee;
        private long deliveryTime;
        private String distributionType;
        private String carrierDriverName;
        private String carrierDriverphone;

        public String getCarrierDriverName() {
            return carrierDriverName;
        }

        public void setCarrierDriverName(String carrierDriverName) {
            this.carrierDriverName = carrierDriverName;
        }

        public String getCarrierDriverphone() {
            return carrierDriverphone;
        }

        public void setCarrierDriverphone(String carrierDriverphone) {
            this.carrierDriverphone = carrierDriverphone;
        }

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public double getShippingFee() {
            return shippingFee;
        }

        public void setShippingFee(double shippingFee) {
            this.shippingFee = shippingFee;
        }

        public double getFreightDiscount() {
            return freightDiscount;
        }

        public void setFreightDiscount(double freightDiscount) {
            this.freightDiscount = freightDiscount;
        }

        public double getOriginalFreight() {
            return originalFreight;
        }

        public void setOriginalFreight(double originalFreight) {
            this.originalFreight = originalFreight;
        }

        public double getBaseFreight() {
            return baseFreight;
        }

        public void setBaseFreight(double baseFreight) {
            this.baseFreight = baseFreight;
        }

        public double getTimePremium() {
            return timePremium;
        }

        public void setTimePremium(double timePremium) {
            this.timePremium = timePremium;
        }

        public double getDistancePremium() {
            return distancePremium;
        }

        public void setDistancePremium(double distancePremium) {
            this.distancePremium = distancePremium;
        }

        public double getMealFee() {
            return mealFee;
        }

        public void setMealFee(double mealFee) {
            this.mealFee = mealFee;
        }

        public long getDeliveryTime() {
            return deliveryTime;
        }

        public void setDeliveryTime(long deliveryTime) {
            this.deliveryTime = deliveryTime;
        }

        public String getDistributionType() {
            return distributionType;
        }

        public void setDistributionType(String distributionType) {
            this.distributionType = distributionType;
        }
    }

    public static class OrderCancelBean {
        /**
         * orderId : 5017
         * orderCancelReason : OTHER
         * cancelContent : 等待接单超时取消订单
         * cancelType : RECEIVING_TIMEOUT
         */

        private int orderId;
        private String orderCancelReason;
        private String cancelContent;
        private String cancelType;

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public String getOrderCancelReason() {
            return orderCancelReason;
        }

        public void setOrderCancelReason(String orderCancelReason) {
            this.orderCancelReason = orderCancelReason;
        }

        public String getCancelContent() {
            return cancelContent;
        }

        public void setCancelContent(String cancelContent) {
            this.cancelContent = cancelContent;
        }

        public String getCancelType() {
            return cancelType;
        }

        public String getCancelTypeStr() {
            String result = "";
            if (TextUtils.equals("RECEIVING_TIMEOUT", cancelType)) {
                result = "接单超时";
            } else if (TextUtils.equals("DELIVERY_REJECT", cancelType)) {
                result = "拒绝配送";
            }

            return result;
        }

        public void setCancelType(String cancelType) {
            this.cancelType = cancelType;
        }
    }

    public static class OrderContactBean {
        /**
         * orderId : 5017
         * contactId : 219
         * contactName : 余俊
         * contactPhone : 13558911947
         * address : 通威国际中心()
         * houseNumber :
         * longitude : 104.06772316
         * latitude : 30.55056401
         * gender : MALE
         * simpleAddress : 通威国际中心
         * detailAddress :
         */

        private int orderId;
        private int contactId;
        private String contactName;
        private String contactPhone;
        private String address;
        private String houseNumber;
        private double longitude;
        private double latitude;
        private String gender;
        private String simpleAddress;
        private String detailAddress;

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public int getContactId() {
            return contactId;
        }

        public void setContactId(int contactId) {
            this.contactId = contactId;
        }

        public String getContactName() {
            return contactName;
        }

        public String getContactNameStr() {
            return contactName + (!TextUtils.equals("MALE", gender) ? "女士" : "先生");
        }

        public void setContactName(String contactName) {
            this.contactName = contactName;
        }

        public String getContactPhone() {
            return contactPhone;
        }

        public void setContactPhone(String contactPhone) {
            this.contactPhone = contactPhone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getHouseNumber() {
            return houseNumber;
        }

        public void setHouseNumber(String houseNumber) {
            this.houseNumber = houseNumber;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getSimpleAddress() {
            return simpleAddress;
        }

        public void setSimpleAddress(String simpleAddress) {
            this.simpleAddress = simpleAddress;
        }

        public String getDetailAddress() {
            return detailAddress;
        }

        public void setDetailAddress(String detailAddress) {
            this.detailAddress = detailAddress;
        }
    }

    public static class OrderInvoiceInfoBean {
        /**
         * orderId : 5017
         */

        private int orderId;

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }
    }
}
