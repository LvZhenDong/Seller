package food.xinyuan.seller.app.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by f-x on 2017/12/14  09:47
 * Description
 */

public class MapInfoBean implements Parcelable {
    private Double Latitude;
    private Double longitude;
    private String AddressName;

    public MapInfoBean() {
    }

    public Double getLatitude() {
        return Latitude;
    }

    public void setLatitude(Double latitude) {
        Latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getAddressName() {
        return AddressName;
    }

    public void setAddressName(String addressName) {
        AddressName = addressName;
    }

    public static Creator<MapInfoBean> getCREATOR() {
        return CREATOR;
    }

    protected MapInfoBean(Parcel in) {
        if (in.readByte() == 0) {
            Latitude = null;
        } else {
            Latitude = in.readDouble();
        }
        if (in.readByte() == 0) {
            longitude = null;
        } else {
            longitude = in.readDouble();
        }
        AddressName = in.readString();
    }

    public static final Creator<MapInfoBean> CREATOR = new Creator<MapInfoBean>() {
        @Override
        public MapInfoBean createFromParcel(Parcel in) {
            return new MapInfoBean(in);
        }

        @Override
        public MapInfoBean[] newArray(int size) {
            return new MapInfoBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (Latitude == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(Latitude);
        }
        if (longitude == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(longitude);
        }
        dest.writeString(AddressName);
    }

    @Override
    public String toString() {
        return "MapInfoBean{" +
                "Latitude=" + Latitude +
                ", longitude=" + longitude +
                ", AddressName='" + AddressName + '\'' +
                '}';
    }
}
