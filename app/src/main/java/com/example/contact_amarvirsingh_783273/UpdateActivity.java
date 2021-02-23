package com.example.contact_amarvirsingh_783273;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity {

    EditText first , last, address, number, email;
    Button update;
    HelperClass helper;
    ContactModelClass modelClass;
    ArrayList<ContactModelClass> list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        list = new ArrayList<>();
        helper = new HelperClass(this);
        first = findViewById(R.id.etUpdateFirstName);
        last = findViewById(R.id.etupdateLastName);
        address = findViewById(R.id.etUpdateAddress);
        number = findViewById(R.id.etUpdatePhoneNumber);
        email = findViewById(R.id.etUpdateEmailAddress);
        update = findViewById(R.id.updateBtn);





       int id =  getIntent().getIntExtra("id",1);
        Log.i("TAG", "onCreate: "+ id);

       // modelClass = new ContactModelClass();

        list = helper.getOneContact(id);




        first.setText(list.get(0).getFirstName());
        last.setText(list.get(0).getLastName());
        address.setText(list.get(0).getAddress());
        email.setText(list.get(0).getEmailId());
        number.setText(list.get(0).getPhoneNumber());


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long result = helper.updateData(id,first.getText().toString(),last.getText().toString(),email.getText().toString(),number.getText().toString(),address.getText().toString());
                if(result == -1)
                {
                    Toast.makeText(UpdateActivity.this, "Can Not Update Data", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(UpdateActivity.this, "Data Updated Successfullu", Toast.LENGTH_SHORT).show();
                    Intent intetn = new Intent(UpdateActivity.this, MainActivity.class);
                    startActivity(intetn);
                    finish();
                }
            }
        });







    }
}