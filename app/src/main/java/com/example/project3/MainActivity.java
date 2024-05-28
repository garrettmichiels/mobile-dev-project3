package com.example.project3;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Arrays;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {
    EditText usernameET;
    EditText passwordET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("APP_PREFERENCES", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        ActivityResultLauncher<Intent> screen2Launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK){
                            Intent data = result.getData();
                        }
                    }
                });

        //CREATE TEST USERS
        editor.putString("gm", "test");
        editor.putInt("gm-weight", 77);
        editor.putInt("gm-height", 177);
        editor.putInt("gm-age", 22);
        editor.putStringSet("gm-meals", new HashSet<>(Arrays.asList("Meal1", "Meal2", "Meal3")));
        editor.putStringSet("gm-calories", new HashSet<>(Arrays.asList("120", "250", "25")));
        editor.putStringSet("gm-hydration", new HashSet<>(Arrays.asList("12:15AM", "12:30PM")));
        editor.putStringSet("gm-ml", new HashSet<>(Arrays.asList("120", "250")));
        editor.commit();

        usernameET = findViewById(R.id.usernameInput);
        passwordET = findViewById(R.id.passwordInput);
        // SIGN IN BUTTON
        Log.i("DEBUG", "Create Sign In Button");
        Button signin = findViewById(R.id.signinButton);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameET.getText().toString();
                String password = passwordET.getText().toString();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter a Username and Password", Toast.LENGTH_SHORT).show();
                }
                else {
                    String result = prefs.getString(username, "Invalid Username or Password");
                    if (result.equals(password)) {
                        Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();

                        Intent myIntent = new Intent(MainActivity.this, screen2.class);
                        myIntent.putExtra("USERNAME", username);
                        myIntent.putExtra("PASSWORD", password);
                        Log.i("DEBUG", "Go to SCREEN2");
                        screen2Launcher.launch(myIntent);
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // REGISTER BUTTON
        Log.i("DEBUG", "Create register Button");
        Button register = findViewById(R.id.goToRegisterButton);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, RegisterActivity.class);
                screen2Launcher.launch(myIntent);
            }
        });
    }
}