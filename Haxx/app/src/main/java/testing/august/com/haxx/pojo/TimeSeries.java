package testing.august.com.haxx.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Benny on 2015-02-16.
 */
public class TimeSeries implements Parcelable {

    private String time;
    private String airTemperature;
    private String visibility;
    private String windDirection;
    private String windSpeed;
    private String relativeHumidity;
    private String probabilityForThunder;
    private String totalAmountOfCloud;
    private String amountOfCloudLow;
    private String amountOfCloudMedium;
    private String amountOfCloudHigh;
    private String windGusts;
    private String precipitationTotal;
    private String precipitationSnow;
    private String precipitationForm;

    public TimeSeries() {
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAirTemperature() {
        return airTemperature;
    }

    public void setAirTemperature(String airTemperature) {
        this.airTemperature = airTemperature;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getRelativeHumidity() {
        return relativeHumidity;
    }

    public void setRelativeHumidity(String relativeHumidity) {
        this.relativeHumidity = relativeHumidity;
    }

    public String getProbabilityForThunder() {
        return probabilityForThunder;
    }

    public void setProbabilityForThunder(String probabilityForThunder) {
        this.probabilityForThunder = probabilityForThunder;
    }

    public String getTotalAmountOfCloud() {
        return totalAmountOfCloud;
    }

    public void setTotalAmountOfCloud(String totalAmountOfCloud) {
        this.totalAmountOfCloud = totalAmountOfCloud;
    }

    public String getAmountOfCloudLow() {
        return amountOfCloudLow;
    }

    public void setAmountOfCloudLow(String amountOfCloudLow) {
        this.amountOfCloudLow = amountOfCloudLow;
    }

    public String getAmountOfCloudMedium() {
        return amountOfCloudMedium;
    }

    public void setAmountOfCloudMedium(String amountOfCloudMedium) {
        this.amountOfCloudMedium = amountOfCloudMedium;
    }

    public String getAmountOfCloudHigh() {
        return amountOfCloudHigh;
    }

    public void setAmountOfCloudHigh(String amountOfCloudHigh) {
        this.amountOfCloudHigh = amountOfCloudHigh;
    }

    public String getWindGusts() {
        return windGusts;
    }

    public void setWindGusts(String windGusts) {
        this.windGusts = windGusts;
    }

    public String getPrecipitationTotal() {
        return precipitationTotal;
    }

    public void setPrecipitationTotal(String precipitationTotal) {
        this.precipitationTotal = precipitationTotal;
    }

    public String getPrecipitationSnow() {
        return precipitationSnow;
    }

    public void setPrecipitationSnow(String precipitationSnow) {
        this.precipitationSnow = precipitationSnow;
    }

    public String getPrecipitationForm() {
        return precipitationForm;
    }

    public void setPrecipitationForm(String precipitationForm) {
        this.precipitationForm = precipitationForm;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.time);
        dest.writeString(this.airTemperature);
        dest.writeString(this.visibility);
        dest.writeString(this.windDirection);
        dest.writeString(this.windSpeed);
        dest.writeString(this.relativeHumidity);
        dest.writeString(this.probabilityForThunder);
        dest.writeString(this.totalAmountOfCloud);
        dest.writeString(this.amountOfCloudLow);
        dest.writeString(this.amountOfCloudMedium);
        dest.writeString(this.amountOfCloudHigh);
        dest.writeString(this.windGusts);
        dest.writeString(this.precipitationTotal);
        dest.writeString(this.precipitationSnow);
        dest.writeString(this.precipitationForm);
    }

    private TimeSeries(Parcel in) {
        this.time = in.readString();
        this.airTemperature = in.readString();
        this.visibility = in.readString();
        this.windDirection = in.readString();
        this.windSpeed = in.readString();
        this.relativeHumidity = in.readString();
        this.probabilityForThunder = in.readString();
        this.totalAmountOfCloud = in.readString();
        this.amountOfCloudLow = in.readString();
        this.amountOfCloudMedium = in.readString();
        this.amountOfCloudHigh = in.readString();
        this.windGusts = in.readString();
        this.precipitationTotal = in.readString();
        this.precipitationSnow = in.readString();
        this.precipitationForm = in.readString();
    }

    public static final Parcelable.Creator<TimeSeries> CREATOR
            = new Parcelable.Creator<TimeSeries>() {
        @Override
        public TimeSeries createFromParcel(Parcel in) {
            return new TimeSeries(in);
        }

        @Override
        public TimeSeries[] newArray(int size) {
            return new TimeSeries[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

}
