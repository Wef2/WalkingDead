package jejunu.hackathon.walkingdead;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class RunningActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LatLng startLatLng, endLatLng;
    private List<Zombie> zombies;

    private Handler handler;
    private MyThread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running);

        Intent intent = getIntent();
        startLatLng = (LatLng)intent.getParcelableExtra("start");
        endLatLng = (LatLng)intent.getParcelableExtra("end");

        zombies = new ArrayList<>();
        for(int i = 0; i < 10; i ++){
            Zombie zombie = new Zombie();
            double randomLatitude = (double) ((int)(Math.random() * 100) + 1)/ 10000;
            randomLatitude = randomLatitude + startLatLng.latitude;
            double randomLongitude = (double) ((int)(Math.random() * 100) + 1)/ 10000;
            randomLongitude = randomLongitude + startLatLng.longitude;
            LatLng position = new LatLng(randomLatitude, randomLongitude);
            zombie.setPosition(position);
            zombies.add(zombie);
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        handler = new Handler();
        thread = new MyThread();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    public void setDefaultMarkers(){
        MarkerOptions startMarker = new MarkerOptions()
                .position(startLatLng).draggable(true);
        mMap.addMarker(startMarker);
        MarkerOptions endMarker = new MarkerOptions()
                .position(endLatLng).draggable(true);
        mMap.addMarker(endMarker);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng jeju = new LatLng(33.499234, 126.530714);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(jeju, 15));

        setDefaultMarkers();

        for(Zombie zombie : zombies){
            MarkerOptions markerOptions = new MarkerOptions().position(zombie.getPosition())
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.skull));
            System.out.println(zombie.getPosition());
            mMap.addMarker(markerOptions);
        }

        thread.start();
    }

    class MyThread extends Thread {

        @Override
        public void run() {
            super.run();
            while (true) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        mMap.clear();

                        setDefaultMarkers();

                        List<Zombie> newZombies = new ArrayList<>();
                        for(Zombie zombie : zombies){
                            Zombie newZombie = new Zombie();
                            double newLatitude = (startLatLng.latitude + zombie.getPosition().latitude) / 2;
                            double newLongitude = (startLatLng.longitude + zombie.getPosition().longitude) / 2;
                            LatLng newPosition = new LatLng(newLatitude, newLongitude);
                            newZombie.setPosition(newPosition);
                            newZombies.add(newZombie);
                        }

                        zombies.clear();
                        zombies.addAll(newZombies);

                        for(Zombie zombie : zombies){
                            MarkerOptions markerOptions = new MarkerOptions().position(zombie.getPosition())
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.skull));
                            System.out.println(zombie.getPosition());
                            mMap.addMarker(markerOptions);
                        }

                    }
                });
            }
        }
    }
}
