package testing.august.com.haxx;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import testing.august.com.haxx.pojo.Location;
import testing.august.com.haxx.pojo.TimeSeries;

/**
 * Created by Benny on 2015-02-19.
 */
public class HaxxGeoCoder {

    private GeoCoderCallback callback;
    private String address;

    public interface GeoCoderCallback{
        public void geoCoderCallback(double[] latlnglist,String address);
    }

    public void setCallback(GeoCoderCallback callback) {
        this.callback = callback;
    }

    public void getLatLongFromAddress(String address) {
        String uri = "http://maps.google.com/maps/api/geocode/json?address=" +
                address + "&sensor=false";
        this.address = address;

        new getAddressTask().execute(uri);


    }

    private class getAddressTask extends AsyncTask<String, Integer, double[]> {

        //Tunga arbetet här
        @Override
        protected double[] doInBackground(String... urls) {

            double[] latlnglist = new double[2];

            //Starta JSON nedladding + parse
            for(String uri : urls){

                HttpGet httpGet = new HttpGet(uri);
                HttpClient client = new DefaultHttpClient();
                HttpResponse response;
                StringBuilder stringBuilder = new StringBuilder();

                try {
                    response = client.execute(httpGet);
                    HttpEntity entity = response.getEntity();
                    InputStream stream = entity.getContent();
                    int b;
                    while ((b = stream.read()) != -1) {
                        stringBuilder.append((char) b);
                    }
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject = new JSONObject(stringBuilder.toString());

                    double lng = ((JSONArray)jsonObject.get("results")).getJSONObject(0)
                            .getJSONObject("geometry").getJSONObject("location")
                            .getDouble("lng");

                    double lat = ((JSONArray)jsonObject.get("results")).getJSONObject(0)
                            .getJSONObject("geometry").getJSONObject("location")
                            .getDouble("lat");
                    latlnglist[0] = lng;
                    latlnglist[1] = lat;
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            //Används för att uppdatera progress
            publishProgress();
            return latlnglist;
        }

        //Uppdatera progress
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        //Efter tråden är färdig
        @Override
        protected void onPostExecute(double[] latlnglist) {
            super.onPostExecute(latlnglist);
            callback.geoCoderCallback(latlnglist,address);
        }
    }
}
