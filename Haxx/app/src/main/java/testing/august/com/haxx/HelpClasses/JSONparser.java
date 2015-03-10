package testing.august.com.haxx.HelpClasses;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import testing.august.com.haxx.pojo.Location;
import testing.august.com.haxx.pojo.TimeSeries;

/**
 * Created by Benny on 2015-02-16.
 */
public  class JSONparser {


    public static Location parseJSONobject(JSONObject json) {
        Location l = new Location();


        try {
            l.setLatitude(Double.valueOf(json.getString("lat")));
            l.setLongitude(Double.valueOf(json.getString("lon")));
            l.setReferenceTime(json.getString("referenceTime"));
            l.setTimeSeries(setWeatherData(json));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return l;
    }


    private static ArrayList<TimeSeries> setWeatherData(JSONObject json){

        ArrayList<TimeSeries> ta = new ArrayList<TimeSeries>();

        try {
            JSONArray ja = json.getJSONArray("timeseries");

            for(int i = 0; i < ja.length(); i++){

                TimeSeries ts = new TimeSeries();
                ts.setTime(ja.getJSONObject(i).getString("validTime"));
                ts.setAirTemperature(ja.getJSONObject(i).getString("t"));
                ts.setVisibility(ja.getJSONObject(i).getString("vis"));
                ts.setWindDirection(ja.getJSONObject(i).getString("wd"));
                ts.setWindSpeed(ja.getJSONObject(i).getString("ws"));
                ts.setRelativeHumidity(ja.getJSONObject(i).getString("r"));
                ts.setProbabilityForThunder(ja.getJSONObject(i).getString("tstm"));
                ts.setTotalAmountOfCloud(ja.getJSONObject(i).getString("tcc"));
                ts.setAmountOfCloudLow(ja.getJSONObject(i).getString("lcc"));
                ts.setAmountOfCloudMedium(ja.getJSONObject(i).getString("mcc"));
                ts.setAmountOfCloudHigh(ja.getJSONObject(i).getString("hcc"));
                ts.setWindGusts(ja.getJSONObject(i).getString("gust"));
                ts.setPrecipitationTotal(ja.getJSONObject(i).getString("pit"));
                ts.setPrecipitationSnow(ja.getJSONObject(i).getString("pis"));
                ts.setPrecipitationForm(ja.getJSONObject(i).getString("pcat"));

                ta.add(ts);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return ta;
    }

}
