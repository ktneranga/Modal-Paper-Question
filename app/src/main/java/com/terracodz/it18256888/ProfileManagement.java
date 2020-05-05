package com.terracodz.it18256888;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import Database.DBHandler;

public class ProfileManagement extends AppCompatActivity {

    EditText user_name, dob, password;
    RadioButton male, female;
    Button add, update;
    String gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_managemnt);

        user_name = (EditText) findViewById(R.id.etUserNamepm);
        dob = (EditText) findViewById(R.id.etDobpm);
        password = (EditText) findViewById(R.id.etPasswordpm);
        male = (RadioButton) findViewById(R.id.rmalepm);
        female = (RadioButton) findViewById(R.id.rfemalepm);
        add = (Button) findViewById(R.id.btnAddpm);
        update = (Button) findViewById(R.id.btnUpdatepm);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileManagement.this, EditProfile.class));
            }
        });

        //implement the add button
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHandler dbHandler = new DBHandler(getApplicationContext());

                String str_userName = user_name.getText().toString();
                String str_dob = dob.getText().toString();
                String str_password = password.getText().toString();

                if (male.isChecked()){
                    gender = "Male";
                }else{
                    gender = "Female";
                }

               long row_id = dbHandler.addInfo(str_userName, str_dob, str_password, gender);

                Toast.makeText(ProfileManagement.this, "User added, User ID : "+row_id, Toast.LENGTH_SHORT).show();

            }
        });

    }
}
