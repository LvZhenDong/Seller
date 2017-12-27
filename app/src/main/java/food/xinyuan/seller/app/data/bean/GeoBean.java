package food.xinyuan.seller.app.data.bean;

import java.io.Serializable;

/**
 * Created by f-x on 2017/12/15  16:19
 * Description
 */

public class GeoBean implements Serializable {
    private Double latitude;
    private Double longitude;

    public GeoBean(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
}
