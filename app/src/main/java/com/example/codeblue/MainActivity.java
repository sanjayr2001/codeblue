package com.example.codeblue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private Button registerbtn;
    private EditText emailid, password;
    private TextView logintext;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(MainActivity.this, AddNewMedicineActivity.class));
        registerbtn = (Button) findViewById(R.id.RegButton);
        emailid = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        logintext = (TextView) findViewById(R.id.LoginBottom);
        auth = FirebaseAuth.getInstance();

        logintext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
            }
        );



        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailid.getText().toString().trim();
                String passwordf = password.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(passwordf)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (passwordf.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }
                auth.createUserWithEmailAndPassword(email, passwordf).
                        addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    Toast.makeText(MainActivity.this, "Retry Again!" ,Toast.LENGTH_SHORT).show();
                                } else {
                                    finish();
                                    Toast.makeText(MainActivity.this, "Registration Successful!" ,Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                                }
                            }
                        });

            }
        });
    }
}