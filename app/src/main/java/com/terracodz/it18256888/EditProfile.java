package com.terracodz.it18256888;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.List;

import Database.DBHandler;

public class EditProfile extends AppCompatActivity {

    EditText user_name, dob, password;
    RadioButton male, female;
    Button edit, delete, search;
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        user_name = (EditText) findViewById(R.id.etUserNameep);
        dob = (EditText) findViewById(R.id.etDobep);
        password = (EditText) findViewById(R.id.etPasswordep);
        male = (RadioButton) findViewById(R.id.maleep);
        female = (RadioButton) findViewById(R.id.femaleep);
        edit = (Button) findViewById(R.id.btnEditep);
        delete = (Button) findViewById(R.id.btnDeleteep);
        search = (Button) findViewById(R.id.btnSearchep);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHandler dbHandler = new DBHandler(getApplicationContext());
                List user = dbHandler.readAllInfo(user_name.getText().toString());

                    if (user.isEmpty()){
                        Toast.makeText(EditProfile.this, "No user found", Toast.LENGTH_SHORT).show();
                        user_name.setText(null);
                        dob.setText(null);
                        password.setText(null);
                        male.setChecked(true);
                    }else{
                        Toast.makeText(EditProfile.this, "User Found", Toast.LENGTH_SHORT).show();
                        user_name.setText(user.get(0).toString()); //array list ekakin enne
                        dob.setText(user.get(1).toString()); //arraylist ekakin enne
                        password.setText(user.get(2).toString());
                        if (user.get(3).toString().equals("Male")){
                            male.setChecked(true);
                        }else{
                            female.setChecked(true);
                        }
                    }
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHandler dbHandler = new DBHandler(getApplicationContext());
                if (male.isChecked()){
                    gender = "Male";
                }else{
                    gender = "Female";
                }
                boolean status = dbHandler.updateInfo(user_name.getText().toString(), dob.getText().toString(), password.getText().toString(),gender );
                if (status){
                    Toast.makeText(EditProfile.this, "Update successful", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(EditProfile.this, "Update Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHandler dbHandler = new DBHandler(getApplicationContext());
                dbHandler.deleteInfo(user_name.getText().toString());
                Toast.makeText(EditProfile.this, "User Deleted", Toast.LENGTH_SHORT).show();
                user_name.setText(null);
                dob.setText(null);
                password.setText(null);
                male.setChecked(true);
                female.setChecked(true);
            }
        });




    }
}
