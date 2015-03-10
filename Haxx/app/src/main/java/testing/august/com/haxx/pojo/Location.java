package testing.august.com.haxx.pojo;

import java.util.ArrayList;

/**
 * Created by Benny on 2015-02-16.
 */
public class Location {



    private int id;
    private double latitude;
    private double longitude;

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    private String locationName;
    private String referenceTime;
    private ArrayList<TimeSeries> timeSeries;

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


}
