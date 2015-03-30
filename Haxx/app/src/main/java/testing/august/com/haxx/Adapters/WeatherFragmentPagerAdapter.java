package testing.august.com.haxx.Adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import testing.august.com.haxx.Fragments.WeatherFragment;
import testing.august.com.haxx.pojo.Location;
import testing.august.com.haxx.pojo.TimeSeries;

/**
 * Created by Benny on 2015-03-27.
 */
public class WeatherFragmentPagerAdapter extends FragmentPagerAdapter {

    Location loc;


    public WeatherFragmentPagerAdapter(FragmentManager fm, Location loc) {
        super(fm);
        this.loc = loc;
        System.out.println(loc.getTimeSeries() + " *** konstruktor PagerAdapter");
    }

    @Override
    public Fragment getItem(int i) {
        int position = i;
        WeatherFragment fragment = new WeatherFragment();
        Bundle args = new Bundle();


        System.out.println(loc.getTimeSeries() + " ********* början av getITEM");
        ArrayList<TimeSeries> ts = loc.getTimeSeries();
        ArrayList<TimeSeries> selection = new ArrayList<>();
        Location tmpLocation = new Location();

        System.out.println(position);

        switch (position) {

            case 0:
                //50
                for (int n = 0; n <= 50; n++) {
                    selection.add(ts.get(n));
                }

                break;
            case 1:
                //4
                for (int n = 50; n <= 53; n++) {
                    selection.add(ts.get(n));
                }

                break;
            case 2:
                //15
                for (int n = 54; n <= 68; n++) {
                    selection.add(ts.get(n));
                }

                break;
            case 3:
                //6
                for (int n = 69; n <= 74; n++) {
                    selection.add(ts.get(n));
                }
                break;
        }
        tmpLocation.setTimeSeries(selection);
        tmpLocation.setReferenceTime(loc.getReferenceTime());
        System.out.println(tmpLocation.getTimeSeries() + "****** tmpLocation.setTimeSeries(selection);");
        args.putParcelable("location", tmpLocation);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "OBJECT " + (position + 1);
    }
}

