package testing.august.com.haxx.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import testing.august.com.haxx.pojo.Location;

/**
 * Created by Benny on 2015-03-09.
 */
public class WeatherDataSource{

    // Database fields
    private SQLiteDatabase database;
    private WeatherDbHelper dbHelper;


    public WeatherDataSource(Context context) {
        dbHelper = new WeatherDbHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long createLocation(double latitude, double longitude,String locationName){

        ContentValues values = new ContentValues();
        values.put(WeatherDbHelper.COLUMN_LONGITUDE,longitude);
        values.put(WeatherDbHelper.COLUMN_LATITUDE,latitude);
        values.put(WeatherDbHelper.COLUMN_LOCATION_NAME,locationName);
        return database.insert(WeatherDbHelper.LOCATIONS_TABLE_NAME, null, values);

    }

    public long deleteLocation(Location location){

       int id = location.getId();
       return database.delete(WeatherDbHelper.LOCATIONS_TABLE_NAME, WeatherDbHelper.COLUMN_ID
                + " = " + id, null);
    }

    public ArrayList<Location> getAllLocations(){

        ArrayList<Location> allLocations = new ArrayList<Location>();

        Cursor cursor = database.query(WeatherDbHelper.LOCATIONS_TABLE_NAME,
                null, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Location location = new Location();
            location.setId(cursor.getInt(0));
            location.setLongitude(cursor.getDouble(1));
            location.setLatitude(cursor.getDouble(2));
            location.setLocationName(cursor.getString(3));
            allLocations.add(location);
            cursor.moveToNext();
        }
        // Make sure to close the cursor
        cursor.close();
        return allLocations;

    }
}
