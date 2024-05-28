package com.example.project3;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.tabs.TabLayout;

public class screen2 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_screen2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        MealsFragment mealsFragment = new MealsFragment();
        hydrationFragment hydrationFragment = new hydrationFragment();
//        summaryFragment summaryFragment = new summaryFragment();
//        stepsFragment stepsFragment = new stepsFragment();

        //Get username from sharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("APP_PREFERENCES", MODE_PRIVATE);
        String username = sharedPreferences.getString("USERNAME", "");
        Log.d("USERNAME", username);

        //Create bundle with username and send to fragments
        Bundle bundle = new Bundle();
        bundle.putString("USERNAME", username);
//        String[] meals = (String[]) sharedPreferences.getStringSet(username+"-meals", null).toArray();
//        String[] calories = (String[]) sharedPreferences.getStringSet(username+"-calories", null).toArray();
//        bundle.putStringArray();

        FragmentManager fm = getSupportFragmentManager();
        fm.setFragmentResult("USERNAME", bundle);

        //Incorporate tabs and fragments
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    fm.beginTransaction()
                            .replace(R.id.fragmentContainerView, mealsFragment, null)
                            .setReorderingAllowed(true)
                            .addToBackStack(null)
                            .commit();
                }
                else if (tab.getPosition() == 1) {
                    fm.beginTransaction()
                            .replace(R.id.fragmentContainerView, hydrationFragment, null)
                            .setReorderingAllowed(true)
                            .addToBackStack(null)
                            .commit();
                }
                else if (tab.getPosition() == 2) {
//                    fm.beginTransaction()
//                            .replace(R.id.fragmentContainerView, summaryFragment, null)
//                            .setReorderingAllowed(true)
//                            .addToBackStack(null)
//                            .commit();
                }
                else if (tab.getPosition() == 3) {
//                    fm.beginTransaction()
//                            .replace(R.id.fragmentContainerView, stepsFragment, null)
//                            .setReorderingAllowed(true)
//                            .addToBackStack(null)
//                            .commit();
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}