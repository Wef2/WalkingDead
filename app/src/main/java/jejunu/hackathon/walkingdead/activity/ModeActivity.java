package jejunu.hackathon.walkingdead.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import jejunu.hackathon.walkingdead.R;

public class ModeActivity extends AppCompatActivity {

    ImageView walkingButton, runningButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode);

        walkingButton = (ImageView) findViewById(R.id.walking_button);
        runningButton = (ImageView) findViewById(R.id.running_button);

        walkingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(ModeActivity.this, WalkingActivity.class));
            }
        });

        runningButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(ModeActivity.this, RunningModeSettingActivity.class));
            }
        });
    }
}
