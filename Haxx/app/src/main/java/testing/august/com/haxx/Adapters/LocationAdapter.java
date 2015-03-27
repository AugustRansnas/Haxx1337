package testing.august.com.haxx.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import testing.august.com.haxx.pojo.Location;

/**
 * Created by Benny on 2015-03-10.
 */
public class LocationAdapter extends ArrayAdapter<Location> {

    private ArrayList<Location> allLocations;

    public LocationAdapter(Context context, ArrayList allLocations) {
        super(context, android.R.layout.simple_list_item_1, allLocations);
        this.allLocations = allLocations;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View row;
        if (convertView == null) { // Create new row view object
            LayoutInflater inflater = LayoutInflater.from(getContext());
            row = inflater.inflate(android.R.layout.simple_list_item_1, null);
        } else {
            // reuse old row view to save time/battery
            row = convertView;
        }
            /* Add new data to row object */

        TextView locationName = (TextView) row.findViewById(android.R.id.text1);
        locationName.setText(allLocations.get(position).getLocationName());
        return row;
    }
}
