package jejunu.hackathon.walkingdead;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by replay on 2016. 7. 12..
 */
public class Zombie {

    private LatLng position;

    public void setPosition(LatLng position) {
        this.position = position;
    }

    public LatLng getPosition() {
        return position;
    }
}
