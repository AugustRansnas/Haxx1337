package testing.august.com.haxx.HelpClasses;

import testing.august.com.haxx.R;
import testing.august.com.haxx.pojo.TimeSeries;

/**
 * Created by Benny on 2015-03-29.
 */
public class ChildIconHelper {



    public static int setCompassIcon(TimeSeries ts){

        int windDirection = Integer.valueOf(ts.getWindDirection());



        if(windDirection > 0 && windDirection <=22.5 || windDirection > 337.5 && windDirection<=360 ){
            return R.drawable.south;
        }else if(windDirection > 22.5 && windDirection <= 67.5){
            return R.drawable.southwest;
        }else if(windDirection > 67.5 && windDirection <= 112.5){
            return R.drawable.west;
        }else if(windDirection > 112.5 && windDirection <= 157.5){
            return R.drawable.northwest;
        }else if(windDirection > 157.5 && windDirection <= 202.5){
            return R.drawable.north;
        }else if(windDirection > 202.5 && windDirection <= 247.5){
            return R.drawable.northeast;
        }else if(windDirection > 247.5 && windDirection <=292.5){
            return R.drawable.east;
        }else if(windDirection > 292.5 && windDirection <= 337.5){
            return R.drawable.southeast;
        }else if(windDirection == 0){
            return R.drawable.nowind;
        }else{
            return R.drawable.ic_launcher;
        }

    }

    public static int setWindDIrection(TimeSeries ts){

        int windDirection = Integer.valueOf(ts.getWindDirection());

        if(windDirection > 0 && windDirection <=22.5 || windDirection > 337.5 && windDirection<=360 ){
            return R.string.north ;
        }else if(windDirection > 22.5 && windDirection <= 67.5){
            return R.string.northeast;
        }else if(windDirection > 67.5 && windDirection <= 112.5){
            return R.string.east;
        }else if(windDirection > 112.5 && windDirection <= 157.5){
            return R.string.southeast;
        }else if(windDirection > 157.5 && windDirection <= 202.5){
            return R.string.south;
        }else if(windDirection > 202.5 && windDirection <= 247.5){
            return R.string.southwest;
        }else if(windDirection > 247.5 && windDirection <=292.5){
            return R.string.west;
        }else if(windDirection > 292.5 && windDirection <= 337.5){
            return R.string.northwest;
        }else if(windDirection == 0){
            return R.string.no_answer;
        }else{
            return R.string.no_answer;
        }

    }
}
