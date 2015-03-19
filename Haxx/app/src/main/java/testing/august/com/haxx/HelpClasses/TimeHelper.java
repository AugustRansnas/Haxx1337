package testing.august.com.haxx.HelpClasses;

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

            int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);


            if (dayOfWeek == 1) {
                day = "Måndag";
            } else if (dayOfWeek == 2) {
                day = "Tisdag";
            } else if (dayOfWeek == 3) {
                day = "Onsdag";
            } else if (dayOfWeek == 4) {
                day = "Torsdag";
            } else if (dayOfWeek == 5) {
                day = "Fredag";
            } else if (dayOfWeek == 6) {
                day = "Lördag";
            } else if (dayOfWeek == 7) {
                day = "Söndag";
            } else {
                System.out.println("NÅGOT ÄR FEL MED DAGEN ********************");
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
