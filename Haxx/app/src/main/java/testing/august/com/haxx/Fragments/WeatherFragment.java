package testing.august.com.haxx.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import testing.august.com.haxx.R;

/**
 * Created by Benny on 2015-03-27.
 */
public class WeatherFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        TextView tv = (TextView)view.findViewById(R.id.tvRandom);
        tv.setText(String.valueOf(getArguments().getInt("position")));
        return view;


    }
}
