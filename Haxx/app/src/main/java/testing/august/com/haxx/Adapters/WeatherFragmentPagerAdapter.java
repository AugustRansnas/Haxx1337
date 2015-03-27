package testing.august.com.haxx.Adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.google.android.gms.internal.pu;

import testing.august.com.haxx.Fragments.WeatherFragment;

/**
 * Created by Benny on 2015-03-27.
 */
public class WeatherFragmentPagerAdapter extends FragmentPagerAdapter {


        public WeatherFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            WeatherFragment fragment = new WeatherFragment();
            Bundle args = new Bundle();
            args.putInt("position",i);
            // Our object is just an integer :-P
            //args.putInt(Fragment.ARG_OBJECT, i + 1);
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

