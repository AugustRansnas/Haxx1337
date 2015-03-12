package testing.august.com.haxx.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Benny on 2015-02-16.
 */
public class Location implements Parcelable {

    private int id;
    private double latitude;
    private double longitude;
    private String locationName;
    private String referenceTime;
    private ArrayList<TimeSeries> timeSeries;

    public Location() {
    }

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

    public String getReferenceTime() {
        return referenceTime;
    }

    public void setReferenceTime(String referenceTime) {
        this.referenceTime = referenceTime;
    }

    public ArrayList<TimeSeries> getTimeSeries() {
        return timeSeries;
    }

    public void setTimeSeries(ArrayList<TimeSeries> timeSeries) {
        this.timeSeries = timeSeries;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeDouble(this.latitude);
        dest.writeDouble(this.longitude);
        dest.writeString(this.locationName);
        dest.writeString(this.referenceTime);
        dest.writeTypedList(timeSeries);
    }

    private Location(Parcel in) {
        this.id = in.readInt();
        this.latitude = in.readDouble();
        this.longitude = in.readDouble();
        this.locationName = in.readString();
        this.referenceTime = in.readString();
        this.timeSeries = in.createTypedArrayList(TimeSeries.CREATOR);
    }

    public static final Parcelable.Creator<Location> CREATOR
            = new Parcelable.Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }
}
