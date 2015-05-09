package testing.august.com.haxx.Adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import testing.august.com.haxx.Fragments.WeatherFragment;
import testing.august.com.haxx.HelpClasses.TimeHelper;
import testing.august.com.haxx.pojo.Location;
import testing.august.com.haxx.pojo.TimeSeries;

/**
 * Created by Benny on 2015-03-27.
 */
public class WeatherFragmentPagerAdapter extends FragmentStatePagerAdapter implements WeatherFragment.OnRefreshContentListener {

    Location loc;
    ArrayList<String> dates;
    ArrayList<TimeSeries> ts;


    public WeatherFragmentPagerAdapter(FragmentManager fm, Location loc) {
        super(fm);
        this.loc = loc;
        setUp();

    }

    public void setUp(){

        ts = loc.getTimeSeries();
        dates = new ArrayList<>();

        for (TimeSeries t : ts) {
            String day = TimeHelper.getDay(t.getTime());
            String date = TimeHelper.getDateWithoutTime(t.getTime());

            String daydate = day + " " + date;

            if (!dates.contains(daydate)) {
                dates.add(daydate);
            }
        }
    }

    @Override
    public Fragment getItem(int i) {

        WeatherFragment fragment = new WeatherFragment();
        Bundle args = new Bundle();
        ArrayList<TimeSeries> selection = new ArrayList<>();
        Location tmpLocation = new Location();
        tmpLocation.setLatitude(loc.getLatitude());
        tmpLocation.setLongitude(loc.getLongitude());
        fragment.setListener(this);

        for(TimeSeries t:ts){
            String day = TimeHelper.getDay(t.getTime());
            String date = TimeHelper.getDateWithoutTime(t.getTime());
            String daydate = day + " " + date;
            if(dates.get(i).equals(daydate)){
                selection.add(t);
            }
        }

        tmpLocation.setTimeSeries(selection);
        tmpLocation.setReferenceTime(loc.getReferenceTime());
        args.putParcelable("location", tmpLocation);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return dates.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return dates.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }


    @Override
    public void onRefreshContent(Location loc) {
        this.loc = loc;
        setUp();
        notifyDataSetChanged();
    }
}

