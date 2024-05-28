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

public class RegisterActivity extends AppCompatActivity {
    SharedPreferences preferences = getSharedPreferences("USER_INFO", MODE_PRIVATE);
    SharedPreferences.Editor editor = preferences.edit();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

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

        EditText usernameET = findViewById(R.id.usernameRegisterText);
        EditText passwordET = findViewById(R.id.passwordRegisterText);
        EditText heightET = findViewById(R.id.heightRegisterText);
        EditText weightET = findViewById(R.id.weightRegisterText);
        EditText ageET = findViewById(R.id.ageRegisterText);

        Button registerButton = findViewById(R.id.goToRegisterButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameET.getText().toString();
                String password = passwordET.getText().toString();
                String height = heightET.getText().toString();
                String weight = weightET.getText().toString();
                String age = ageET.getText().toString();

                if (!(username.isEmpty() || password.isEmpty() || height.isEmpty() || weight.isEmpty() || age.isEmpty())) {
                    //Check if username already exists
                    if (preferences.contains(username)) {
                        Toast.makeText(RegisterActivity.this, "Username already exists", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Save the username and password in SharedPreferences
                    editor.putString(username, password);
                    editor.putString(username+"-height", height);
                    editor.putString(username+"-weight", weight);
                    editor.putString(username+"-age", age);
                    editor.commit();
                    Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();

                    Intent myIntent = new Intent(RegisterActivity.this, screen2.class);
                    myIntent.putExtra("USERNAME", username);
                    myIntent.putExtra("PASSWORD", password);
                    myIntent.putExtra("HEIGHT", height);
                    myIntent.putExtra("WEIGHT", weight);
                    myIntent.putExtra("AGE", age);
                    Log.i("DEBUG", "Go to screen2");
                    Toast.makeText(RegisterActivity.this, "Welcome!", Toast.LENGTH_SHORT).show();
                    screen2Launcher.launch(myIntent);
                }
                else {
                    Toast.makeText(RegisterActivity.this, "Please enter data into all data fields", Toast.LENGTH_SHORT).show();
                }
        }});

    }
}