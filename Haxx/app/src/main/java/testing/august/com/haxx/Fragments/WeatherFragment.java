package testing.august.com.haxx.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Calendar;

import testing.august.com.haxx.Activities.ShowWeatherActivity;
import testing.august.com.haxx.Adapters.AnimatedExpandableListView;
import testing.august.com.haxx.Adapters.ExpandableListViewAdapter;
import testing.august.com.haxx.HelpClasses.DownloadWeather;
import testing.august.com.haxx.HelpClasses.JSONparser;
import testing.august.com.haxx.R;
import testing.august.com.haxx.pojo.Location;


/**
 * Created by Benny on 2015-03-27.
 */
public class WeatherFragment extends Fragment   {

    ExpandableListViewAdapter listAdapter;
    AnimatedExpandableListView expListView;
    View view;
    int previousGroup = -1;
    Context context;
    Location loc;
    SwipeRefreshLayout mSwipeRefreshLayout;
    OnRefreshContentListener listener;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_weather, container, false);

         mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.activity_main_swipe_refresh_layout);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                                                     @Override
                                                     public void onRefresh() {

                                                         System.out.println("HALLÅ JAG REFRSHAERW");
                                                         refreshContent();
                                                     }
                                                 });

        context = getActivity();

        expListView = (AnimatedExpandableListView) view.findViewById(R.id.expandList);

        loc = getArguments().getParcelable("location");

        if (loc != null) {
            listAdapter = new ExpandableListViewAdapter(getActivity(), loc);
            expListView.setAdapter(listAdapter);
        }

        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                // We call collapseGroupWithAnimation(int) and
                // expandGroupWithAnimation(int) to animate group
                // expansion/collapse.
                if (expListView.isGroupExpanded(groupPosition)) {
                    expListView.collapseGroupWithAnimation(groupPosition);
                    previousGroup=-1;
                } else {
                    expListView.expandGroupWithAnimation(groupPosition);
                    if(previousGroup!=-1){
                        expListView.collapseGroupWithAnimation(previousGroup);
                    }
                    previousGroup=groupPosition;
                }
                expListView.setSelection(groupPosition);
                return true;
            }

        });

        return view;
    }

    public void setListener(OnRefreshContentListener listener){
        this.listener = listener;
    }

    private class RefreshWeather extends AsyncTask<URL, Integer, Location> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Location doInBackground(URL... urls) {

            Location location = null;


                JSONObject json = DownloadWeather.downloadWeather(urls[0]);
                location = JSONparser.parseJSONobject(json);

            return location;
        }

        @Override
        protected void onPostExecute(Location location) {
            super.onPostExecute(location);

            if (location != null) {

                mSwipeRefreshLayout.setRefreshing(false);
                System.out.println(location.getTimeSeries());
                listener.onRefreshContent(location);

            }

        }
    }

    private void refreshContent(){

        DecimalFormat df = new DecimalFormat("####0.00");
        try {
            URL url = new URL("http://opendata-download-metfcst.smhi.se/api/category/pmp1.5g/version/1/geopoint/lat/" + df.format(loc.getLatitude()).replace(",", ".") + "/lon/" + df.format(loc.getLongitude()).replace(",", ".") + "/data.json");
            new RefreshWeather().execute(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public interface OnRefreshContentListener{

        void onRefreshContent(Location loc);
    }
}
