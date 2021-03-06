package testing.august.com.haxx.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import testing.august.com.haxx.HelpClasses.ChildIconHelper;
import testing.august.com.haxx.HelpClasses.GroupIconHelper;
import testing.august.com.haxx.HelpClasses.TemperatureHelper;
import testing.august.com.haxx.HelpClasses.TimeHelper;
import testing.august.com.haxx.R;
import testing.august.com.haxx.pojo.Location;
import testing.august.com.haxx.pojo.TimeSeries;

/**
 * Created by Benny on 2015-03-12.
 */
public class ExpandableListViewAdapter extends AnimatedExpandableListView.AnimatedExpandableListAdapter {

    private Context context;
    private ArrayList<TimeSeries> timeseries;
    private Location location;

    public ExpandableListViewAdapter(Context context, Location location){
        this.context = context;
        this.timeseries = location.getTimeSeries();
        this.location = location;
    }
    @Override
    public int getGroupCount() {
        return this.timeseries.size();
    }


    @Override
    public Object getGroup(int groupPosition) {
        return timeseries.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.timeseries.get(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }


    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View row;
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = infalInflater.inflate(R.layout.expandable_listgroup, null);
        }else {
            // reuse old row view to save time/battery
            row = convertView;
        }

        TimeSeries timeSeries = timeseries.get(groupPosition);

        TextView lblTime = (TextView)row.findViewById(R.id.lblTime);
        TextView lblTempHigh = (TextView)row.findViewById(R.id.lblTempHigh);
        TextView lblTempLow = (TextView)row.findViewById(R.id.lblTempLow);
        TextView lblPrecipitation = (TextView)row.findViewById(R.id.lblPrecipitation);
        ImageView weatherIcon = (ImageView) row.findViewById(R.id.iVWeatherIcon);

        weatherIcon.setImageResource(GroupIconHelper.setWeatherIcon(timeSeries));


        String precipitation = timeSeries.getPrecipitationTotal();

        lblPrecipitation.setText(precipitation);

        String time = TimeHelper.getTime(timeSeries.getTime());
        lblTime.setText(time);

        HashMap<String,String> highestLowestTemp= TemperatureHelper.getHighestLowestTemperature(location,timeSeries.getTime());

        lblTempHigh.setText(highestLowestTemp.get("highest"));
        lblTempLow.setText(highestLowestTemp.get("lowest"));


        return row;
    }

    @Override
    public View getRealChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View row;
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = infalInflater.inflate(R.layout.expandable_listitem, null);
        }else {
            // reuse old row view to save time/battery
            row = convertView;
        }

        TextView temp = (TextView)row.findViewById(R.id.lblTemp);
        TextView windSpeed = (TextView)row.findViewById(R.id.lblWindSpeed);
        ImageView windDirection = (ImageView)row.findViewById(R.id.imgWindDirection);
        TextView windGusts = (TextView)row.findViewById(R.id.lblWindGusts);
        TextView visibility = (TextView)row.findViewById(R.id.lblVisibility);
        //TextView totalAmountOfClouds = (TextView)row.findViewById(R.id.lblTotalAmountOfClouds);
        TextView humidity = (TextView)row.findViewById(R.id.lblHumidity);
        TextView thunder = (TextView)row.findViewById(R.id.lblProbabilityOfThunder);
        TextView snow = (TextView)row.findViewById(R.id.lblSnow);
        TextView windDirectionText = (TextView)row.findViewById(R.id.lblWindDirection);
        //TextView form = (TextView)row.findViewById(R.id.lblForm);

        TimeSeries timeSeries = timeseries.get(groupPosition);

        temp.setText(timeSeries.getAirTemperature() + row.getResources().getString(R.string.degrees));

        windSpeed.setText(timeSeries.getWindSpeed());
        windDirection.setImageResource(ChildIconHelper.setCompassIcon(timeSeries));
        windGusts.setText(timeSeries.getWindGusts());
        visibility.setText(timeSeries.getVisibility());
        // totalAmountOfClouds.setText(timeSeries.getTotalAmountOfCloud());
       humidity.setText(timeSeries.getRelativeHumidity());
       thunder.setText(timeSeries.getProbabilityForThunder());
        snow.setText(timeSeries.getPrecipitationSnow());
        // form.setText(timeSeries.getPrecipitationForm());
        windDirectionText.setText(row.getResources().getString(ChildIconHelper.setWindDIrection(timeSeries)));

        return row;
    }

    @Override
    public int getRealChildrenCount(int groupPosition) {
        return 1;
    }



    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }



}
