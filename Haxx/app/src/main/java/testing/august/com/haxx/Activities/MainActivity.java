package testing.august.com.haxx.Activities;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;

import testing.august.com.haxx.Adapters.LocationAdapter;
import testing.august.com.haxx.Database.WeatherDataSource;
import testing.august.com.haxx.HelpClasses.CoordinateBoundsHelper;
import testing.august.com.haxx.HelpClasses.DownloadWeather;
import testing.august.com.haxx.HelpClasses.HaxxGeoCoder;
import testing.august.com.haxx.HelpClasses.JSONparser;
import testing.august.com.haxx.R;
import testing.august.com.haxx.pojo.Location;


public class MainActivity extends ActionBarActivity implements HaxxGeoCoder.GeoCoderCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        OnMapReadyCallback, View.OnClickListener, AdapterView.OnItemClickListener, GoogleMap.OnCameraChangeListener {

    private GoogleApiClient mGoogleApiClient;

    // Request code to use when launching the resolution activity
    private static final int REQUEST_RESOLVE_ERROR = 1001;
    // Unique tag for the error dialog fragment
    private static final String DIALOG_ERROR = "dialog_error";
    // Bool to track whether the app is already resolving an error
    private boolean mResolvingError = false;
    private static final String STATE_RESOLVING_ERROR = "resolving_error";

    private ActionMode mActionMode;

    private EditText searchBox;
    private Button search;
    private MapFragment mapFragment;

    private ActionBarDrawerToggle drawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private DrawerLayout drawerLayout;
    private ListView drawerLayoutListView;
    private WeatherDataSource dataSource;
    private double clickedLongitude;
    private double clickedLatitude;
    private String locationName;
    private ArrayList<Location> locationList;
    private LocationAdapter locationAdapter;
    private static final int MAP_ANIMATION_TIME = 3000;
    private long mapAnimationStartTime;
    private boolean isWeatherDownloadReady = false;
    private ProgressBar bar;


    //Bara för test
    private static Toast toast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpComponents(savedInstanceState);
        loadLocationsFromDb();
    }


    private void setUpComponents(Bundle savedInstanceState) {

        locationList = new ArrayList<Location>();
        dataSource = new WeatherDataSource(this);
        mTitle = mDrawerTitle = getTitle();

        searchBox = (EditText) findViewById(R.id.etSearchBox);
        search = (Button) findViewById(R.id.btnSearch);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayoutListView = (ListView) findViewById(R.id.left_drawer);
        search.setOnClickListener(this);
        locationAdapter = new LocationAdapter(this, locationList);
        drawerLayoutListView.setAdapter(locationAdapter);
        drawerLayoutListView.setOnItemClickListener(this);
        bar = (ProgressBar) this.findViewById(R.id.progressBar);

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close) {
            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

        };
        // Set the drawer toggle as the DrawerListener
        drawerLayout.setDrawerListener(drawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        mResolvingError = savedInstanceState != null
                && savedInstanceState.getBoolean(STATE_RESOLVING_ERROR, false);
    }

    private void loadLocationsFromDb() {


        try {
            dataSource.open();
            locationList = dataSource.getAllLocations();
            dataSource.close();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        refreshLocationAdapter();

    }

    private void refreshLocationAdapter() {

        locationAdapter.clear();
        locationAdapter.addAll(locationList);
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE_RESOLVING_ERROR, mResolvingError);
    }

    protected void getWeatherFromCoordinates(double longitude, double latitude) {


        DecimalFormat df = new DecimalFormat("####0.00");
        System.out.println("Value: " + df.format(longitude).replace(",", "."));
        try {
            //url = nedladdningslänk
            URL url = new URL("http://opendata-download-metfcst.smhi.se/api/category/pmp1.5g/version/1/geopoint/lat/" + df.format(latitude).replace(",", ".") + "/lon/" + df.format(longitude).replace(",", ".") + "/data.json");
            new DownloadWeatherDataTask().execute(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnSearch:
                String ourAddress = searchBox.getText().toString();
                HaxxGeoCoder geoCoder = new HaxxGeoCoder();
                geoCoder.setCallback(this);
                geoCoder.getLatLongFromAddress(ourAddress);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        isWeatherDownloadReady = false;
        drawerLayoutListView.setItemChecked(position, true);
        drawerLayout.closeDrawer(drawerLayoutListView);
        setTitle("Item " + String.valueOf(position));

        Location l = (Location) drawerLayoutListView.getItemAtPosition(position);
        this.locationName = l.getLocationName();
        LatLng coordinates = new LatLng(l.getLatitude(), l.getLongitude());

        this.getWeatherFromCoordinates(l.getLongitude(), l.getLatitude());

        GoogleMap map = mapFragment.getMap();
        float zoom = map.getCameraPosition().zoom;

        CameraPosition cameraPosition = new CameraPosition.Builder().target(coordinates).zoom(zoom).build();
        mapAnimationStartTime = Calendar.getInstance().getTimeInMillis();
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), MAP_ANIMATION_TIME, null);

        new CountDownTimer(MAP_ANIMATION_TIME, 1000) {

            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                if (!isWeatherDownloadReady) {

                    bar.setVisibility(View.VISIBLE);
                }
            }
        }.start();
    }

    @Override
    public void geoCoderCallback(double[] latlnglist, String address) {
        clickedLongitude = latlnglist[0];
        clickedLatitude = latlnglist[1];
        if (CoordinateBoundsHelper.isCoordinatesWithinBounds(clickedLongitude, clickedLatitude)) {
            this.locationName = address;
            GoogleMap map = mapFragment.getMap();
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng(clickedLatitude, clickedLongitude), 16));

            // You can customize the marker image using images bundled with
            // your app, or dynamically generated bitmaps.
            map.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.mapmarker))
                    .position(new LatLng(clickedLatitude, clickedLongitude)));

            map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    LatLng latlng = marker.getPosition();

                    if (mActionMode != null) {
                        return false;
                    }

                    // Start the CAB using the ActionMode.Callback defined above
                    mActionMode = startSupportActionMode(mActionModeCallback);
                    return false;
                }
            });
        } else {
            Toast.makeText(this, getResources().getString(R.string.coordinates_out_of_bounds_error_msg), Toast.LENGTH_SHORT).show();
        }
    }

    /* A fragment to display an error dialog */
    public static class ErrorDialogFragment extends DialogFragment {
        public ErrorDialogFragment() {
        }

        @Override
        @NonNull
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Get the error code and retrieve the appropriate dialog
            int errorCode = this.getArguments().getInt(DIALOG_ERROR);
            return GooglePlayServicesUtil.getErrorDialog(errorCode,
                    this.getActivity(), REQUEST_RESOLVE_ERROR);
        }

        @Override
        public void onDismiss(DialogInterface dialog) {
            ((MainActivity) getActivity()).onDialogDismissed();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_RESOLVE_ERROR) {
            mResolvingError = false;
            if (resultCode == RESULT_OK) {
                // Make sure the app is not already connected or attempting to connect
                if (!mGoogleApiClient.isConnecting() &&
                        !mGoogleApiClient.isConnected()) {
                    mGoogleApiClient.connect();
                }
            }
        }
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = drawerLayout.isDrawerOpen(drawerLayoutListView);
        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConnected(Bundle connectionHint) {
        // Connected to Google Play services!
        // The good stuff goes here.

        System.out.println("on connected!");
    }

    @Override
    public void onConnectionSuspended(int cause) {
        // The connection has been interrupted.
        // Disable any UI components that depend on Google APIs
        // until onConnected() is called.
        System.out.println("on connected suspended!" + cause);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!mResolvingError) {  // more about this later
            mGoogleApiClient.connect();
        }
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        if (mResolvingError) {
            // Already attempting to resolve an error.
            return;
        } else if (result.hasResolution()) {
            try {
                mResolvingError = true;
                result.startResolutionForResult(this, REQUEST_RESOLVE_ERROR);
            } catch (IntentSender.SendIntentException e) {
                // There was an error with the resolution intent. Try again.
                mGoogleApiClient.connect();
            }
        } else {
            // Show dialog using GooglePlayServicesUtil.getErrorDialog()
            showErrorDialog(result.getErrorCode());
            mResolvingError = true;
        }
    }

    // The rest of this code is all about building the error dialog

    /* Creates a dialog for an error message */
    private void showErrorDialog(int errorCode) {
        // Create a fragment for the error dialog
        ErrorDialogFragment dialogFragment = new ErrorDialogFragment();
        // Pass the error that should be displayed
        Bundle args = new Bundle();
        args.putInt(DIALOG_ERROR, errorCode);
        dialogFragment.setArguments(args);
        dialogFragment.show(getSupportFragmentManager(), "errordialog");
    }

    /* Called from ErrorDialogFragment when the dialog is dismissed. */
    public void onDialogDismissed() {
        mResolvingError = false;
    }

    @Override
    public void onMapReady(GoogleMap map) {
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(41.889, -87.622), 16));

        // You can customize the marker image using images bundled with
        // your app, or dynamically generated bitmaps.
        map.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.mapmarker))
                .position(new LatLng(41.889, -87.622)));

        map.setOnCameraChangeListener(this);
        map.getUiSettings().setZoomControlsEnabled(true);
    }

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {
        LatLng position = cameraPosition.target;
        boolean isWithinBounds = CoordinateBoundsHelper.isCoordinatesWithinBounds(position.longitude, position.latitude);
        if(toast != null){
            toast.cancel();
        }
        toast = Toast.makeText(this, "Is within bounds: " + isWithinBounds, Toast.LENGTH_SHORT);
        toast.show();
    }

    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {

        // Called when the action mode is created; startActionMode() was called
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            // Inflate a menu resource providing context menu items
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.context_menu, menu);
            return true;
        }

        // Called each time the action mode is shown. Always called after onCreateActionMode, but
        // may be called multiple times if the mode is invalidated.
        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false; // Return false if nothing is done
        }

        // Called when the user selects a contextual menu item
        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.showWeather:
                    Intent i = new Intent(getApplicationContext(), ShowWeatherActivity.class);

                    i.putExtra("locationName", locationName);
                    i.putExtra("longitude", clickedLongitude);
                    i.putExtra("latitude", clickedLatitude);
                    startActivity(i);
                    mode.finish(); // Action picked, so close the CAB
                    return true;
                case R.id.addToFavorites:
                    long result = -1;
                    try {
                        dataSource.open();
                        result = dataSource.createLocation(clickedLatitude, clickedLongitude, locationName);
                        dataSource.close();
                    } catch (SQLiteException e) {
                        e.printStackTrace();
                    }
                    if (result == -1) {
                        System.out.println("create location ERROR");
                    } else {
                        System.out.println("create location SUCCESS");
                        Location l = new Location();
                        l.setId((int) result);
                        l.setLocationName(locationName);
                        l.setLatitude(clickedLatitude);
                        l.setLongitude(clickedLongitude);

                        locationList.add(l);
                        refreshLocationAdapter();

                    }

                    mode.finish(); // Action picked, so close the CAB
                    return true;
                default:
                    return false;
            }
        }

        // Called when the user exits the action mode
        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mActionMode = null;
        }
    };

    private class DownloadWeatherDataTask extends AsyncTask<URL, Integer, Location> {

        //Tunga arbetet här
        @Override
        protected Location doInBackground(URL... urls) {

            Location location = null;

            //Starta JSON nedladding + parse
            for (URL url : urls) {
                System.out.println(url);
                JSONObject json = DownloadWeather.downloadWeather(url);

                location = JSONparser.parseJSONobject(json);


            }

            //Används för att uppdatera progress
            publishProgress();
            return location;
        }

        //Uppdatera progress
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        //Efter tråden är färdig
        @Override
        protected void onPostExecute(Location location) {
            super.onPostExecute(location);

            bar.setVisibility(View.GONE);

            if (location != null) {
                isWeatherDownloadReady = true;
                long timeDifference = MAP_ANIMATION_TIME + mapAnimationStartTime - Calendar.getInstance().getTimeInMillis();
                location.setLocationName(locationName);
                final Location myLocation = location;

                if (timeDifference <= 0) {

                    System.out.println("starta intent" + timeDifference);

                    final Context myContext = getApplicationContext();
                    Intent i = new Intent(myContext, ShowWeatherActivity.class);

                    i.putExtra("location", myLocation);
                    startActivity(i);

                } else {
                    System.out.println("starta inte intent änmnu" + timeDifference);

                    new CountDownTimer(timeDifference + 500, 1000) {

                        public void onTick(long millisUntilFinished) {
                        }

                        public void onFinish() {
                            final Context myContext = getApplicationContext();
                            Intent i = new Intent(myContext, ShowWeatherActivity.class);

                            i.putExtra("location", myLocation);
                            startActivity(i);
                        }
                    }.start();

                }
            } else {
                System.out.println("Location är null");
            }

        }
    }
}
