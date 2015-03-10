package testing.august.com.haxx.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Benny on 2015-03-09.
 */
public class WeatherDbHelper  extends SQLiteOpenHelper {


    public static final String LOCATIONS_TABLE_NAME = "locations";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_LONGITUDE = "longitude";
    public static final String COLUMN_LATITUDE = "latitude";
    public static final String COLUMN_LOCATION_NAME = "locationname";

    private static final String DATABASE_NAME = "locations.db";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE = "create table " + LOCATIONS_TABLE_NAME
            + " (" + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_LONGITUDE + " double not null, "
            + COLUMN_LATITUDE + " double not null, "
            + COLUMN_LOCATION_NAME + " text not null);";

    public WeatherDbHelper(Context context) { super(context,DATABASE_NAME, null,DATABASE_VERSION);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(WeatherDbHelper.class.getName(), "Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + LOCATIONS_TABLE_NAME);
        onCreate(db);
    }
}
