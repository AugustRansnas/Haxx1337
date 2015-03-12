package testing.august.com.haxx.Activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import testing.august.com.haxx.R;

/**
 * Created by Benny on 2015-03-10.
 */
public class ShowWeatherActivity extends ActionBarActivity {

    private String locationName;
    private double latitude;
    private double longitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_weather);
        Bundle extras = getIntent().getExtras();
        if(extras!= null){

            locationName = extras.getString("locationName");
            latitude = extras.getDouble("latitude");
            longitude = extras.getDouble("longitude");

            System.out.println(locationName + latitude + longitude);

        }



    }
}
