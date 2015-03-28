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
public class ShowWeatherActivity extends FragmentActivity implements ExpandableListView.OnGroupExpandListener, ViewPager.OnPageChangeListener {

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
            System.out.println(loc.getLocationName() + "******* showweather oncreate");
            System.out.println(loc.getTimeSeries());
            fpa = new WeatherFragmentPagerAdapter(getSupportFragmentManager(),loc);
            vp = (ViewPager) findViewById(R.id.pager);
            vp.setAdapter(fpa);
            PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
            tabs.setViewPager(vp);
            tabs.setOnPageChangeListener(this);
        }

    }

    @Override
    public void onGroupExpand(int groupPosition) {

    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

/*        ArrayList<TimeSeries> ts = loc.getTimeSeries();
        ArrayList<TimeSeries> selection = new ArrayList<>();
        Location tmpLocation = new Location();

        switch (position) {

            case 0:
                //50
                for (int i = 0; ts.size() <= 50; i++) {
                    selection.add(ts.get(i));
                }

                break;
            case 1:
                //4
                for (int i = 50; ts.size() <= 53; i++) {
                    selection.add(ts.get(i));
                }

                break;
            case 2:
                //15
                for (int i = 54; ts.size() <= 68; i++) {
                    selection.add(ts.get(i));
                }

                break;
            case 3:
                //6
                for (int i = 69; ts.size() <= 74; i++) {
                    selection.add(ts.get(i));
                }
                break;
        }
        tmpLocation.setTimeSeries(selection);
        WeatherFragment fragment = (WeatherFragment)fpa.instantiateItem(vp,position);
        fragment.showTimeSeriesInList(tmpLocation);*/

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
