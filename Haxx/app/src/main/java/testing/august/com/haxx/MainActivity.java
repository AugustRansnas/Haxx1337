package testing.august.com.haxx;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import testing.august.com.haxx.R;
import testing.august.com.haxx.pojo.Location;
import testing.august.com.haxx.pojo.TimeSeries;


public class MainActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Starta Task
        try{
            //url = nedladdningslänk
            URL url = new URL("http://opendata-download-metfcst.smhi.se/api/category/pmp1.5g/version/1/geopoint/lat/58.59/lon/16.18/data.json");
            new DownloadWeatherDataTask().execute(url);
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class DownloadWeatherDataTask extends AsyncTask<URL, Integer, Long> {

        //Tunga arbetet här
        @Override
        protected Long doInBackground(URL... urls) {

            //Starta JSON nedladding + parse



            for(URL url : urls){
                JSONObject json = DownloadWeather.downloadWeather(url);
                Location location = JSONparser.parseJSONobject(json);

                    System.out.println("START!!!!!!!!!!!");
                    System.out.println(location.getLatitude());
                    System.out.println(location.getLongitude());
                    System.out.println(location.getReferenceTime());

                    ArrayList<TimeSeries> a = location.getTimeSeries();

                    for(TimeSeries t :a){
                        System.out.println("TIMESERIES*****************************************");
                        System.out.println(t.getAirTemperature());
                        System.out.println(t.getAmountOfCloudHigh());
                        System.out.println(t.getAmountOfCloudLow());
                        System.out.println(t.getTime());
                        System.out.println(t.getPrecipitationSnow());

                    }

                    System.out.println("END!!!!!!!!!!!");

            }


            //Används för att uppdatera progress
            publishProgress();
            return null;
        }

        //Uppdatera progress
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        //Efter tråden är färdig
        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);
        }
    }
}
