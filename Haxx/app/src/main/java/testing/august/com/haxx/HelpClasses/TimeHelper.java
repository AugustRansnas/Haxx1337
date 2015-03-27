package testing.august.com.haxx.HelpClasses;

import com.google.android.gms.internal.ge;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Benny on 2015-03-17.
 */
public class TimeHelper {


    public static String getDay(String date) {
        String day = null;
        Date myDate = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());
        try {
            myDate = format.parse(date);

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (myDate != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(myDate);

            Date todaysDate = new Date();
            Calendar t = Calendar.getInstance();
            t.setTime(todaysDate);

            boolean sameDay = c.get(Calendar.YEAR) == t.get(Calendar.YEAR) &&
                    c.get(Calendar.DAY_OF_YEAR) == t.get(Calendar.DAY_OF_YEAR);

            t.add(Calendar.DAY_OF_YEAR, 1);
            boolean dayAfter = c.get(Calendar.YEAR) == t.get(Calendar.YEAR) &&
                    c.get(Calendar.DAY_OF_YEAR) == t.get(Calendar.DAY_OF_YEAR);

            SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
            String dayOfTheWeek = sdf.format(myDate);


            if(sameDay) {
                day = "Idag";
            }else if(dayAfter){
                day= "Imorgon";
            }else{
                switch(dayOfTheWeek){

                case "Monday":
                    day="Måndag";
                    break;
                case "Tuesday":
                    day = "Tisdag";
                    break;
                case "Wednesday":
                    day = "Onsdag";
                    break;
                case "Thursday":
                    day = "Torsdag";
                    break;
                case "Friday":
                    day = "Fredag";
                    break;
                case "Saturday":
                    day = "Lördag";
                    break;
                case "Sunday":
                    day = "Söndag";
                    break;

                }
            }
        }

        return day;

    }

    // returnera datum, sträng

    public static String getDate(String date) {
        String formatedDate = date.substring(0, 10);
        String formatTime = date.substring(11,16);
        return formatedDate + " " + formatTime;
    }

}
