package com.example.project3;

import static android.content.Context.SENSOR_SERVICE;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class stepsFragment extends Fragment implements SensorEventListener {

  View view;
  SensorManager sensorManager;
  Sensor stepSensor;
  Sensor gps;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_steps, container, false);

        sensorManager = (SensorManager) getActivity().getSystemService(SENSOR_SERVICE);
        gps = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER), SensorManager.SENSOR_DELAY_NORMAL);
    }


    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this, stepSensor);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        TextView stepsCountText = view.findViewById(R.id.stepsCountText);
        stepsCountText.setText(String.valueOf(event.values[0]));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}