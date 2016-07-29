package jejunu.hackathon.walkingdead;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class RunningModeSettingActivity extends AppCompatActivity implements GoogleMap.OnMarkerDragListener, OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private SupportMapFragment mapFragment;
    private Toolbar toolbar;
    private GoogleMap mMap;
    private LatLng startLatLng, endLatLng;
    private boolean markerStatus = true;
    private MarkerOptions startMarker, endMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running_mode_setting);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("출발지 및 목적지 설정");
        setSupportActionBar(toolbar);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.default_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_done:
                Intent intent = new Intent(RunningModeSettingActivity.this, RunningActivity.class);
                intent.putExtra("start", startLatLng);
                intent.putExtra("end", endLatLng);
                startActivity(intent);
                super.onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng jeju = new LatLng(33.499234, 126.530714);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(jeju, 13));
        mMap.setOnMapClickListener(this);
    }

    @Override
    public void onMapClick(LatLng latLng) {
        mMap.clear();
        if(markerStatus){
            startLatLng = latLng;
        }
        else{
            endLatLng = latLng;
        }
        markerStatus = !markerStatus;

        if(startLatLng != null){
            startMarker = new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.start))
                    .position(startLatLng).draggable(true);
            mMap.addMarker(startMarker);
        }
        if(endLatLng != null){
            endMarker =  new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.end))
                    .position(endLatLng).draggable(true);
            mMap.addMarker(endMarker);
        }
    }


    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        if(marker.equals(startMarker)){
            startLatLng = marker.getPosition();
        }else if(marker.equals(endMarker)){
            endLatLng = marker.getPosition();
        }

    }
}
