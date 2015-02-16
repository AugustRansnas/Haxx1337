package testing.august.com.haxx.pojo;

import java.util.ArrayList;

/**
 * Created by Benny on 2015-02-16.
 */
public class Location {

    private String latitude;
    private String longitude;
    private String referenceTime;
    private ArrayList<TimeSeries> timeSeries;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
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


}
