package com.example.codeblue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    private Button resetbtn;
    private EditText emailid;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        resetbtn = (Button) findViewById(R.id.ResetButton);
        emailid = (EditText) findViewById(R.id.email);
        auth = FirebaseAuth.getInstance();

        resetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailid.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplication(), "Enter your registered email id!", Toast.LENGTH_SHORT).show();
                    return;
                }
                auth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(ForgotPassword.this, "Reset Link has been sent to your registered Email ID!", Toast.LENGTH_SHORT).show();
                                    finish();
                                    startActivity(new Intent(ForgotPassword.this, LoginActivity.class));
                                } else {
                                    Toast.makeText(ForgotPassword.this, "Enter a valid Email ID!", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
            }
        });
    }
    }

