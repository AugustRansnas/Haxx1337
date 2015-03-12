package testing.august.com.haxx.Activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import testing.august.com.haxx.Adapters.ExpandableListViewAdapter;
import testing.august.com.haxx.R;
import testing.august.com.haxx.pojo.Location;
import testing.august.com.haxx.pojo.TimeSeries;

/**
 * Created by Benny on 2015-03-10.
 */
public class ShowWeatherActivity extends ActionBarActivity implements ExpandableListView.OnGroupExpandListener {

    private String locationName;
    private double latitude;
    private double longitude;
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_weather);
        Bundle extras = getIntent().getExtras();

        Location loc = null;

        if (extras != null) {

            loc = extras.getParcelable("location");
            System.out.println(loc.getLocationName());

        }

        expListView = (ExpandableListView) findViewById(R.id.expandList);
        if(loc != null){
            listAdapter = new ExpandableListViewAdapter(this, loc);
            expListView.setAdapter(listAdapter);
        }

        expListView.setOnGroupExpandListener(this);
    }

    @Override
    public void onGroupExpand(int groupPosition) {

    }
}
