package testing.august.com.haxx.Activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.ExpandableListView;

import com.astuetz.PagerSlidingTabStrip;

import java.util.ArrayList;

import testing.august.com.haxx.Adapters.WeatherFragmentPagerAdapter;
import testing.august.com.haxx.Fragments.WeatherFragment;
import testing.august.com.haxx.R;
import testing.august.com.haxx.pojo.Location;
import testing.august.com.haxx.pojo.TimeSeries;

/**
 * Created by Benny on 2015-03-10.
 */
public class ShowWeatherActivity extends FragmentActivity  {

    Location loc;
    WeatherFragmentPagerAdapter fpa;
    ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_weather);
        Bundle extras = getIntent().getExtras();



        if (extras != null) {

            loc = extras.getParcelable("location");
            fpa = new WeatherFragmentPagerAdapter(getSupportFragmentManager(),loc);
            vp = (ViewPager) findViewById(R.id.pager);
            vp.setAdapter(fpa);
            PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
            tabs.setViewPager(vp);
        }

    }




}
