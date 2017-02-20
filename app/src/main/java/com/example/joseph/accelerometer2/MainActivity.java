package com.example.joseph.accelerometer2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.widget.TextView;
import android.hardware.SensorEventListener;
//import android.app.Activity;
//import android.content.Context;
//import android.os.Bundle;
//import com.example.joseph.accelerometer2.R;


public class MainActivity extends AppCompatActivity {
    SensorManager sensorManager;
    TextView textView;
    Sensor accelerometer;
    private SensorEventListener sensorEventListener = new SensorEventListener() {

        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }

        public void onSensorChanged(SensorEvent event) {
            Sensor sensor = event.sensor;
            if (sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];
                update(x, y, z, sensor.getType());
            }
            else if (sensor.getType() == Sensor.TYPE_GYROSCOPE){
                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];
                update(x, y, z, sensor.getType());
            }
            else if (sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){
                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];
                update(x, y, z, sensor.getType());
            }
            else{
                textView.setText("No sensor inputs detected.");
            }
        }
    };

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textView = new TextView(this);
        setContentView(textView);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
//        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
//        magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    }

    public void update(float x, float y, float z, int sensor) {
        if(sensor == Sensor.TYPE_ACCELEROMETER) {
            textView.setText("x_acc: " + x + "\ny_acc: " + y + "\nz_acc: " + z);
        }
        else if(sensor == Sensor.TYPE_GYROSCOPE) {
            textView.setText("x_gyr: " + x + "\ny_gyr: " + y + "\nz_gyr: " + z);
        }
        else if(sensor == Sensor.TYPE_MAGNETIC_FIELD) {
            textView.setText("x_mag: " + x + "\ny_mag: " + y + "\nz_mag: " + z);
        }
        else{
            textView.setText("No sensor inputs detected.");
        }
    }

    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(sensorEventListener, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(sensorEventListener);
    }
}