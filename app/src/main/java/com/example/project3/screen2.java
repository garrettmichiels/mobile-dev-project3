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

import java.util.ArrayList;
import java.util.Set;

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

        //Get username from intent
        String username = getIntent().getStringExtra("USERNAME");
        Log.i("USERNAME", username);

        //Create bundle with username and send to fragments
        MealsFragment mealsFragment = new MealsFragment();
        Bundle mealBundle = getMealsBundle(username);
        mealsFragment.setArguments(mealBundle);

        hydrationFragment hydrationFragment = new hydrationFragment();
        Bundle hydrationBundle = getHydrationBundle(username);
        hydrationFragment.setArguments(hydrationBundle);

        summaryFragment summaryFragment = new summaryFragment();
        Bundle summaryBundle = getSummaryBundle(username);
        summaryFragment.setArguments(summaryBundle);

        stepsFragment stepsFragment = new stepsFragment();

        FragmentManager fm = getSupportFragmentManager();

        //Incorporate tabs and fragments
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    Bundle mealBundle = getMealsBundle(username);
                    mealsFragment.setArguments(mealBundle);
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
                    fm.beginTransaction()
                            .replace(R.id.fragmentContainerView, summaryFragment, null)
                            .setReorderingAllowed(true)
                            .addToBackStack(null)
                            .commit();
                }
                else if (tab.getPosition() == 3) {
                    fm.beginTransaction()
                            .replace(R.id.fragmentContainerView, stepsFragment, null)
                            .setReorderingAllowed(true)
                            .addToBackStack(null)
                            .commit();
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


    public Bundle getMealsBundle(String username) {
        SharedPreferences sharedPreferences = getSharedPreferences("APP_PREFERENCES", MODE_PRIVATE);
        Bundle mealBundle = new Bundle();
        mealBundle.putString("USERNAME", username);
        Set<String> meals = sharedPreferences.getStringSet(username+"-meals", null);
        Set<String> calories = sharedPreferences.getStringSet(username+"-calories", null);

        Log.i("MEALS", meals.toString());
        Log.i("CALORIES", calories.toString());

        ArrayList<String> mealsArray = new ArrayList<>();
        ArrayList<String> caloriesArray = new ArrayList<>();
        for (String meal : meals) {
            mealsArray.add(meal);
        }
        for (String calorie : calories) {
            caloriesArray.add(calorie);
        }

        mealBundle.putStringArrayList("meals", mealsArray);
        mealBundle.putStringArrayList("calories", caloriesArray);
        return mealBundle;
    }

    public Bundle getHydrationBundle(String username) {
        SharedPreferences sharedPreferences = getSharedPreferences("APP_PREFERENCES", MODE_PRIVATE);
        Bundle hydrationBundle = new Bundle();
        hydrationBundle.putString("USERNAME", username);
        Set<String> hydration = sharedPreferences.getStringSet(username+"-hydration", null);
        Set<String> ml = sharedPreferences.getStringSet(username+"-ml", null);

        Log.i("HYDRATION", hydration.toString());
        Log.i("ML", ml.toString());

        ArrayList<String> hydrationArray = new ArrayList<>();
        ArrayList<String> mlArray = new ArrayList<>();
        for (String h : hydration) {
            hydrationArray.add(h);
        }
        for (String m : ml) {
            mlArray.add(m);
        }

        hydrationBundle.putStringArrayList("hydration", hydrationArray);
        hydrationBundle.putStringArrayList("ml", mlArray);
        return hydrationBundle;
    }

    public Bundle getSummaryBundle(String username) {
        SharedPreferences sharedPreferences = getSharedPreferences("APP_PREFERENCES", MODE_PRIVATE);
        Bundle summaryBundle = new Bundle();
        summaryBundle.putString("USERNAME", username);
        int weight = sharedPreferences.getInt(username+"-weight", 0);
        int height = sharedPreferences.getInt(username+"-height", 0);
        int age = sharedPreferences.getInt(username+"-age", 0);
        Log.i("WEIGHT", String.valueOf(weight));
        Log.i("HEIGHT", String.valueOf(height));
        Log.i("AGE", String.valueOf(age));

        summaryBundle.putInt("weight", weight);
        summaryBundle.putInt("height", height);
        summaryBundle.putInt("age", age);
        return summaryBundle;
    }
}