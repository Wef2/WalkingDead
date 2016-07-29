package jejunu.hackathon.walkingdead;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class WalkingActivity extends AppCompatActivity implements SensorEventListener{

    public static int stepCount = 0;
    public static int coin = 0;

    private SensorManager sensorManager;
    private Sensor sensor;

    private TextView stepCountText, coinText;
    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walking);

        stepCountText = (TextView) findViewById(R.id.step_count_text);
        coinText = (TextView) findViewById(R.id.coin_text);

        backButton = (ImageView) findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        stepCount = stepCount + 1;
        stepCountText.setText(String.valueOf(stepCount));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
