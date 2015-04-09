package testing.august.com.haxx.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import testing.august.com.haxx.Adapters.AnimatedExpandableListView;
import testing.august.com.haxx.Adapters.ExpandableListViewAdapter;
import testing.august.com.haxx.R;
import testing.august.com.haxx.pojo.Location;


/**
 * Created by Benny on 2015-03-27.
 */
public class WeatherFragment extends Fragment  {

    ExpandableListViewAdapter listAdapter;
    AnimatedExpandableListView expListView;
    View view;
    int previousGroup = -1;
    Context context;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_weather, container, false);

        context = getActivity();

        expListView = (AnimatedExpandableListView) view.findViewById(R.id.expandList);

        Location loc = getArguments().getParcelable("location");

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


}
