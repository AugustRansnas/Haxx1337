package testing.august.com.haxx;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.net.MalformedURLException;
import java.net.URL;

import testing.august.com.haxx.R;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Starta Task
        try{
            //url = nedladdningslänk
            URL url = new URL("http://example.com/");
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
                System.out.println(url);
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
