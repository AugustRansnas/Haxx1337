package testing.august.com.haxx.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;

import testing.august.com.haxx.Adapters.ExpandableListViewAdapter;
import testing.august.com.haxx.R;
import testing.august.com.haxx.pojo.Location;
import testing.august.com.haxx.pojo.TimeSeries;

/**
 * Created by Benny on 2015-03-27.
 */
public class WeatherFragment extends Fragment implements ExpandableListView.OnGroupExpandListener{

    ExpandableListViewAdapter listAdapter;
    ExpandableListView expListView;
    int previousGroup = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_weather, container, false);

        expListView = (ExpandableListView)view.findViewById(R.id.expandList);

        Location loc = getArguments().getParcelable("location");

       if(loc != null){
            listAdapter = new ExpandableListViewAdapter(getActivity(),loc);
            expListView.setAdapter(listAdapter);
        }

        expListView.setOnGroupExpandListener(this);

        return view;
    }

    @Override
    public void onGroupExpand(int groupPosition) {

        if(groupPosition != previousGroup)
            expListView.collapseGroup(previousGroup);
        previousGroup = groupPosition;

    }
}
