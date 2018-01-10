package food.xinyuan.seller.app.data.bean.response;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author lzd
 * @CreateDate 2018/1/10
 */
public class GeoInfo {

    /**
     * buyer : {"latitude":0,"longitude":0}
     * carrier : {"latitude":0,"longitude":0}
     * shop : {"latitude":0,"longitude":0}
     */

    private BuyerBean buyer;
    private CarrierBean carrier;
    private ShopBean shop;

    public BuyerBean getBuyer() {
        return buyer;
    }

    public void setBuyer(BuyerBean buyer) {
        this.buyer = buyer;
    }

    public CarrierBean getCarrier() {
        return carrier;
    }

    public void setCarrier(CarrierBean carrier) {
        this.carrier = carrier;
    }

    public ShopBean getShop() {
        return shop;
    }

    public void setShop(ShopBean shop) {
        this.shop = shop;
    }

    public static class BuyerBean {
        /**
         * latitude : 0
         * longitude : 0
         */

        private double latitude;
        private double longitude;

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }
    }

    public static class CarrierBean {
        /**
         * latitude : 0
         * longitude : 0
         */

        private double latitude;
        private double longitude;

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }
    }

    public static class ShopBean {
        /**
         * latitude : 0
         * longitude : 0
         */

        private double latitude;
        private double longitude;

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }
    }
}
