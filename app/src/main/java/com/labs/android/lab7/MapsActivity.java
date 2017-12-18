package com.labs.android.lab7;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.labs.android.lab7.lab7.Point;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private List<MarkerOptions> getMarksCollection() {
        Context context = getApplicationContext();
        Point[] temp = null;
        try {
            InputStream inputStream = context.openFileInput("points.data");

            if ( inputStream != null ) {
                ObjectInputStream ois = new ObjectInputStream(inputStream);
                temp = (Point[]) ois.readObject();
                ois.close();
                Toast.makeText(getApplicationContext(), "Markers were read from file...", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            temp = new Point[] {
                new Point(52.432282, 30.684318, "Магазин Red"),
                new Point(52.430753, 30.991155, "Магазин Гомель Центральный"),
                new Point(52.455317, 30.945813, "Магазин Гомель Северный"),
                new Point(52.429850, 30.901923, "ЦентрПродуктовый"),
                new Point(52.400583, 30.757754, "Все для дома"),
                new Point(52.368092, 30.582408, "Продуктовый"),
                new Point(52.379336, 30.502695, "Строительный")
            };
            try {
                FileOutputStream fos = context.openFileOutput("points.data", Context.MODE_PRIVATE);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(temp);
                Toast.makeText(getApplicationContext(), "Creating new markers file...", Toast.LENGTH_SHORT).show();
                oos.close();
            } catch (FileNotFoundException ex) {
                e.printStackTrace();
            } catch (IOException ex) {
                e.printStackTrace();
            }
        }

        MarkerOptions marker1 = new MarkerOptions().position(new LatLng(temp[0].getLatitude(), temp[0].getLongitude())).title(temp[0].getName());
        MarkerOptions marker2 = new MarkerOptions().position(new LatLng(temp[1].getLatitude(), temp[1].getLongitude())).title(temp[1].getName());
        MarkerOptions marker3 = new MarkerOptions().position(new LatLng(temp[2].getLatitude(), temp[2].getLongitude())).title(temp[2].getName());
        MarkerOptions marker4 = new MarkerOptions().position(new LatLng(temp[3].getLatitude(), temp[3].getLongitude())).title(temp[3].getName());
        MarkerOptions marker5 = new MarkerOptions().position(new LatLng(temp[4].getLatitude(), temp[4].getLongitude())).title(temp[4].getName());
        MarkerOptions marker6 = new MarkerOptions().position(new LatLng(temp[5].getLatitude(), temp[5].getLongitude())).title(temp[5].getName());
        MarkerOptions marker7 = new MarkerOptions().position(new LatLng(temp[6].getLatitude(), temp[6].getLongitude())).title(temp[6].getName());
        List<MarkerOptions> listOfMarkers = new ArrayList<>();
        listOfMarkers.addAll(Arrays.asList(marker1, marker2, marker2, marker3, marker4, marker5, marker6, marker7));
        return listOfMarkers;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng myPosition = new LatLng(0, 0);
        mMap = googleMap;
        List<MarkerOptions> listOfMarkers = getMarksCollection();
        for (MarkerOptions marker : listOfMarkers) {
            mMap.addMarker(marker);
        }
        try {
            mMap.setMyLocationEnabled(true);
        } catch (Exception exc) {}
        LocationManager locationManager;
        Log.d("Find Location", "in find_location");
        String location_context = Context.LOCATION_SERVICE;
        Context con = MapsActivity.this;
        locationManager = (LocationManager) con.getSystemService(location_context);
        List<String> providers = locationManager.getProviders(true);
        for (String provider3 : providers) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            locationManager.requestLocationUpdates(provider3, 1000, 0,
                    new LocationListener() {
                        public void onLocationChanged(Location location) {}
                        public void onProviderDisabled(String provider) {}
                        public void onProviderEnabled(String provider) {}
                        public void onStatusChanged(String provider, int status,Bundle extras) {}
                    });
            Location location3 = locationManager.getLastKnownLocation(provider3);
            if (location3 != null) {
                Toast.makeText(getApplicationContext(), String.valueOf(location3.getLatitude()), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), String.valueOf(location3.getLongitude()), Toast.LENGTH_SHORT).show();
                myPosition = new LatLng(location3.getLatitude(), location3.getLongitude());
                mMap.addMarker(new MarkerOptions().position(myPosition).title("you are here").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
                break;
            } else {
                Toast.makeText(getApplicationContext(), new String("location is empty, try again"), Toast.LENGTH_SHORT).show();
            }
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLng(myPosition));

        double mindist=10000;
        int jmin=0;

        Location myLocation = new Location("myloc");
        myLocation.setLatitude(myPosition.latitude);
        myLocation.setLongitude(myPosition.longitude);
        MarkerOptions minMarker = null;

        for (int j = 0; j < listOfMarkers.size(); j++) {
            Location locSt = new Location("provider");
            locSt.setLatitude(listOfMarkers.get(j).getPosition().latitude);
            locSt.setLongitude(listOfMarkers.get(j).getPosition().longitude);
            double dist = myLocation.distanceTo(locSt)/1000;
            Log.i("TAG", listOfMarkers.get(j) + ": расcтояние = " + dist);
            if (dist < mindist){
                mindist=dist;
                jmin=j;
                minMarker = listOfMarkers.get(j);
            }
        }
        LatLng nearestPos = new LatLng(minMarker.getPosition().latitude, minMarker.getPosition().longitude);
        String strInfo = String.format(":  расcтояние= %1$.1f км", mindist);
        mMap.addMarker(minMarker.position(nearestPos).title(minMarker.getTitle() + strInfo).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
    }


}