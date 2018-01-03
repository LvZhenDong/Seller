package food.xinyuan.seller.app.data.bean.response;

import java.util.List;

import food.xinyuan.seller.app.utils.XDateUtils;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author lzd
 * @CreateDate 2017/12/28
 */
public class ShopDetail {

    /**
     * shopId : 61
     * agentId : 0
     * sellerId : 1
     * provinceId : 510000
     * provinceName : 四川省
     * cityId : 510100
     * cityName : 成都市
     * areaId : 510105
     * areaName : 青羊区
     * phoneNum : 15828064744
     * shopName : 鑫圆共享测试店铺(不对外点餐)
     * takeOutPhone : 1555254366
     * name : 周奎
     * passdAudit : true
     * address : 通威国际中心
     * busBeginTime : 08:30:00
     * busEndTime : 23:00:00
     * logoUrl : /shopLogo/1.png
     * shopFaceUrl : /shop/20171023152621418.jpg
     * shopInnerUrl : /shop/20171023152621418.jpg
     * introduce : 测试店铺,下单不进行实际配送!!!
     * longitude : 104.067907
     * latitude : 30.550473
     * geohash : wm6jb1ecmuzy
     * shopRate : 2
     * shopMonthlySales : 189
     * preConsumption : 36
     * shelves : true
     * unshelvesReason :
     * operatingState : true
     * shopType : TAKEOUT
     * distributionType : ARES
     * fee : 0
     * minDeliveryPrice : 10
     * distributionScope : 40
     * canDrawInvoice : true
     * topper : 0
     * addTime : 1505803515000
     * collectioned : false
     * carousel : [{"imgId":2,"shopId":61,"imgUrl":"/shop/20171023152650809.jpg"},{"imgId":3,"shopId":61,"imgUrl":"/store/20171023162445504.jpg"},{"imgId":4,"shopId":61,"imgUrl":"/store/20171026180137504.png"}]
     * shopActive : [{"activityId":215,"shopId":61,"activityName":"满12减1,满13减4,满15减5","activityType":"DELGOLD","activityContent":{"typeName":"sharefood.models.activity.activity.entity.DelgoldActivityData","delgolds":[{"full":12,"subtract":1},{"full":13,"subtract":4},{"full":15,"subtract":5}]},"audit":true,"isValid":true,"beginTime":1514170294000},{"activityId":217,"shopId":61,"activityName":"本店新用户立减10元","activityType":"FIRST","activityContent":{"typeName":"sharefood.models.activity.activity.entity.FirstActivityData","money":10},"audit":true,"isValid":true,"beginTime":1514184427000,"endTime":1514736000000},{"activityId":221,"shopId":61,"activityName":"是是是","activityType":"SALE","activityContent":{"typeName":"sharefood.models.activity.activity.entity.SaleActivityData"},"audit":true,"isValid":true,"beginTime":1514254064000,"endTime":1514736000000}]
     */

    private int shopId;
    private int agentId;
    private int sellerId;
    private int provinceId;
    private String provinceName;
    private int cityId;
    private String cityName;
    private int areaId;
    private String areaName;
    private String phoneNum;
    private String shopName;
    private String takeOutPhone;
    private String name;
    private boolean passdAudit;
    private String address;
    private String busBeginTime;
    private String busEndTime;
    private String logoUrl;
    private String shopFaceUrl;
    private String shopInnerUrl;
    private String introduce;
    private double longitude;
    private double latitude;
    private String geohash;
    private int shopRate;
    private int shopMonthlySales;
    private int preConsumption;
    private boolean shelves;
    private String unshelvesReason;
    private boolean operatingState;
    private String shopType;
    private String distributionType;
    private int fee;
    private double minDeliveryPrice;
    private int distributionScope;
    private boolean canDrawInvoice;
    private boolean automaticAcceptOrder;
    private int topper;
    private long addTime;
    private boolean collectioned;
    private List<CarouselBean> carousel;
    private List<ShopActiveBean> shopActive;

    public boolean isAutomaticAcceptOrder() {
        return automaticAcceptOrder;
    }

    public void setAutomaticAcceptOrder(boolean automaticAcceptOrder) {
        this.automaticAcceptOrder = automaticAcceptOrder;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getTakeOutPhone() {
        return takeOutPhone;
    }

    public void setTakeOutPhone(String takeOutPhone) {
        this.takeOutPhone = takeOutPhone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPassdAudit() {
        return passdAudit;
    }

    public void setPassdAudit(boolean passdAudit) {
        this.passdAudit = passdAudit;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBusBeginTime() {
        return XDateUtils.date2String(XDateUtils.string2Date(busBeginTime,"HH:mm"),"HH:mm");
    }

    public void setBusBeginTime(String busBeginTime) {
        this.busBeginTime = busBeginTime;
    }

    public String getBusEndTime() {
        return XDateUtils.date2String(XDateUtils.string2Date(busEndTime,"HH:mm"),"HH:mm");
    }

    public void setBusEndTime(String busEndTime) {
        this.busEndTime = busEndTime;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getShopFaceUrl() {
        return shopFaceUrl;
    }

    public void setShopFaceUrl(String shopFaceUrl) {
        this.shopFaceUrl = shopFaceUrl;
    }

    public String getShopInnerUrl() {
        return shopInnerUrl;
    }

    public void setShopInnerUrl(String shopInnerUrl) {
        this.shopInnerUrl = shopInnerUrl;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
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

    public String getGeohash() {
        return geohash;
    }

    public void setGeohash(String geohash) {
        this.geohash = geohash;
    }

    public int getShopRate() {
        return shopRate;
    }

    public void setShopRate(int shopRate) {
        this.shopRate = shopRate;
    }

    public int getShopMonthlySales() {
        return shopMonthlySales;
    }

    public void setShopMonthlySales(int shopMonthlySales) {
        this.shopMonthlySales = shopMonthlySales;
    }

    public int getPreConsumption() {
        return preConsumption;
    }

    public void setPreConsumption(int preConsumption) {
        this.preConsumption = preConsumption;
    }

    public boolean isShelves() {
        return shelves;
    }

    public void setShelves(boolean shelves) {
        this.shelves = shelves;
    }

    public String getUnshelvesReason() {
        return unshelvesReason;
    }

    public void setUnshelvesReason(String unshelvesReason) {
        this.unshelvesReason = unshelvesReason;
    }

    public boolean isOperatingState() {
        return operatingState;
    }

    public void setOperatingState(boolean operatingState) {
        this.operatingState = operatingState;
    }

    public String getShopType() {
        return shopType;
    }

    public void setShopType(String shopType) {
        this.shopType = shopType;
    }

    public String getDistributionType() {
        return distributionType;
    }

    public void setDistributionType(String distributionType) {
        this.distributionType = distributionType;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public double getMinDeliveryPrice() {
        return minDeliveryPrice;
    }

    public void setMinDeliveryPrice(double minDeliveryPrice) {
        this.minDeliveryPrice = minDeliveryPrice;
    }

    public int getDistributionScope() {
        return distributionScope;
    }

    public void setDistributionScope(int distributionScope) {
        this.distributionScope = distributionScope;
    }

    public boolean isCanDrawInvoice() {
        return canDrawInvoice;
    }

    public void setCanDrawInvoice(boolean canDrawInvoice) {
        this.canDrawInvoice = canDrawInvoice;
    }

    public int getTopper() {
        return topper;
    }

    public void setTopper(int topper) {
        this.topper = topper;
    }

    public long getAddTime() {
        return addTime;
    }

    public void setAddTime(long addTime) {
        this.addTime = addTime;
    }

    public boolean isCollectioned() {
        return collectioned;
    }

    public void setCollectioned(boolean collectioned) {
        this.collectioned = collectioned;
    }

    public List<CarouselBean> getCarousel() {
        return carousel;
    }

    public void setCarousel(List<CarouselBean> carousel) {
        this.carousel = carousel;
    }

    public List<ShopActiveBean> getShopActive() {
        return shopActive;
    }

    public void setShopActive(List<ShopActiveBean> shopActive) {
        this.shopActive = shopActive;
    }

    public static class CarouselBean {
        /**
         * imgId : 2
         * shopId : 61
         * imgUrl : /shop/20171023152650809.jpg
         */

        private int imgId;
        private int shopId;
        private String imgUrl;

        public int getImgId() {
            return imgId;
        }

        public void setImgId(int imgId) {
            this.imgId = imgId;
        }

        public int getShopId() {
            return shopId;
        }

        public void setShopId(int shopId) {
            this.shopId = shopId;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }
    }

    public static class ShopActiveBean {
        /**
         * activityId : 215
         * shopId : 61
         * activityName : 满12减1,满13减4,满15减5
         * activityType : DELGOLD
         * activityContent : {"typeName":"sharefood.models.activity.activity.entity.DelgoldActivityData","delgolds":[{"full":12,"subtract":1},{"full":13,"subtract":4},{"full":15,"subtract":5}]}
         * audit : true
         * isValid : true
         * beginTime : 1514170294000
         * endTime : 1514736000000
         */

        private int activityId;
        private int shopId;
        private String activityName;
        private String activityType;
        private ActivityContentBean activityContent;
        private boolean audit;
        private boolean isValid;
        private long beginTime;
        private long endTime;

        public int getActivityId() {
            return activityId;
        }

        public void setActivityId(int activityId) {
            this.activityId = activityId;
        }

        public int getShopId() {
            return shopId;
        }

        public void setShopId(int shopId) {
            this.shopId = shopId;
        }

        public String getActivityName() {
            return activityName;
        }

        public void setActivityName(String activityName) {
            this.activityName = activityName;
        }

        public String getActivityType() {
            return activityType;
        }

        public void setActivityType(String activityType) {
            this.activityType = activityType;
        }

        public ActivityContentBean getActivityContent() {
            return activityContent;
        }

        public void setActivityContent(ActivityContentBean activityContent) {
            this.activityContent = activityContent;
        }

        public boolean isAudit() {
            return audit;
        }

        public void setAudit(boolean audit) {
            this.audit = audit;
        }

        public boolean isIsValid() {
            return isValid;
        }

        public void setIsValid(boolean isValid) {
            this.isValid = isValid;
        }

        public long getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(long beginTime) {
            this.beginTime = beginTime;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        public static class ActivityContentBean {
            /**
             * typeName : sharefood.models.activity.activity.entity.DelgoldActivityData
             * delgolds : [{"full":12,"subtract":1},{"full":13,"subtract":4},{"full":15,"subtract":5}]
             */

            private String typeName;
            private List<DelgoldsBean> delgolds;

            public String getTypeName() {
                return typeName;
            }

            public void setTypeName(String typeName) {
                this.typeName = typeName;
            }

            public List<DelgoldsBean> getDelgolds() {
                return delgolds;
            }

            public void setDelgolds(List<DelgoldsBean> delgolds) {
                this.delgolds = delgolds;
            }

            public static class DelgoldsBean {
                /**
                 * full : 12
                 * subtract : 1
                 */

                private int full;
                private int subtract;

                public int getFull() {
                    return full;
                }

                public void setFull(int full) {
                    this.full = full;
                }

                public int getSubtract() {
                    return subtract;
                }

                public void setSubtract(int subtract) {
                    this.subtract = subtract;
                }
            }
        }
    }
}
