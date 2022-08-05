package com.example.codeblue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.RadioButton;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity {

    private TextInputLayout name, age, phonenumber;
    private Button updatebtn;
    private RadioButton rdmalebtn, rdfemalebtn;
    private FirebaseDatabase startnode;
    private DatabaseReference reference;
    private FirebaseAuth auth;
    private FirebaseUser user;
    private String userid,Gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name = findViewById(R.id.nametextField);
        age = findViewById(R.id.agetextField);
        phonenumber = findViewById(R.id.phonetextField);
        updatebtn = findViewById(R.id.updateBtn);
        rdmalebtn = findViewById(R.id.malerdbutton);
        rdfemalebtn = findViewById(R.id.femalerdbutton);

       rdmalebtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               rdfemalebtn.setChecked(false);
               Gender = "Male";
           }
       });

        rdfemalebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rdmalebtn.setChecked(false);
                Gender = "Female";
            }
        });

        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                FirebaseDatabase.getInstance().setPersistenceEnabled(true);
                String Name = name.getEditText().getText().toString();
                String Age = age.getEditText().getText().toString();
                String Phonenumber = phonenumber.getEditText().getText().toString();

                if (TextUtils.isEmpty(Name)) {
                    Toast.makeText(getApplicationContext(), "Enter Your Name!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(Age)) {
                    Toast.makeText(getApplicationContext(), "Enter Your Age!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(Phonenumber)) {
                    Toast.makeText(getApplicationContext(), "Enter Your Phone Number!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (Phonenumber.length() < 10) {
                    Toast.makeText(getApplicationContext(), "Enter a Valid Mobile Number!", Toast.LENGTH_SHORT).show();
                    return;
                }

                startnode = FirebaseDatabase.getInstance();
                reference = startnode.getReference("Users");

                user = auth.getInstance().getCurrentUser();
                userid = user.getUid();

                UserProfileCreationHelper obj = new UserProfileCreationHelper(Name, Age, Phonenumber, Gender);
                reference.child(userid).setValue(obj);

                finish();
                Toast.makeText(ProfileActivity.this, "Profile Successfully Updated!" ,Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
            }
        });

    }
}