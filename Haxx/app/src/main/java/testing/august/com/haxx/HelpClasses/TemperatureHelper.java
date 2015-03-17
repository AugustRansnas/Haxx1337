package testing.august.com.haxx.HelpClasses;

import java.util.ArrayList;
import java.util.HashMap;

import testing.august.com.haxx.pojo.Location;
import testing.august.com.haxx.pojo.TimeSeries;

/**
 * Created by Benny on 2015-03-17.
 */
public class TemperatureHelper {


    public static HashMap<String,String> getHighestLowestTemperature(Location location, String time) {

        String date = TimeHelper.getDate(time);


        ArrayList<TimeSeries> tsList = location.getTimeSeries();
        ArrayList<TimeSeries> tsMatchedList = new ArrayList<>();
        HashMap<String,String> highestLowestTemperatures = new HashMap<>();

        for (TimeSeries ts : tsList) {

            if (date.equals(TimeHelper.getDate(ts.getTime()))) {
                tsMatchedList.add(ts);
            }
        }

        double highestTemp = Integer.MIN_VALUE;
        double lowestTemp = Integer.MAX_VALUE;
        if (tsMatchedList.size() != 0) {
            double temp;
            for (TimeSeries ts : tsMatchedList) {
                temp = Double.valueOf(ts.getAirTemperature());
                if (temp > highestTemp) {
                    highestTemp = temp;
                }if(temp < lowestTemp){
                    lowestTemp = temp;
                }
            }
            highestLowestTemperatures.put("highest",String.valueOf(highestTemp));
            highestLowestTemperatures.put("lowest",String.valueOf(lowestTemp));
        }
        return highestLowestTemperatures;
    }

}
