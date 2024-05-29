package com.example.project3;

import static android.content.Context.LOCATION_SERVICE;
import static android.content.Context.SENSOR_SERVICE;

import static androidx.core.content.ContextCompat.getSystemService;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class stepsFragment extends Fragment implements SensorEventListener {

  View view;
  SensorManager sensorManager;
  LocationManager locationManager;
  LocationListener locationListener;
  Location previousLocation;
  float totalDistance = 0;

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

        TextView steps = view.findViewById(R.id.stepsCountText);

        sensorManager = (SensorManager) getActivity().getSystemService(SENSOR_SERVICE);
        locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                if (location != null) {
                    Log.i("SENSORS_APP", "onLocationChanged()");
                    totalDistance += location.distanceTo(previousLocation);
                    previousLocation = location;
                    steps.setText(String.valueOf(totalDistance));
                }
                else {
                    previousLocation = location;
                }
            }
        };
        return view;
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