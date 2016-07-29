package jejunu.hackathon.walkingdead.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;

import java.util.ArrayList;
import java.util.List;

import jejunu.hackathon.walkingdead.R;
import jejunu.hackathon.walkingdead.model.Zombie;

public class RunningActivity extends FragmentActivity {

    private List<Zombie> zombies;

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running);

        Intent intent = getIntent();

        zombies = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Zombie zombie = new Zombie();
        }

        handler = new Handler();
    }
}
