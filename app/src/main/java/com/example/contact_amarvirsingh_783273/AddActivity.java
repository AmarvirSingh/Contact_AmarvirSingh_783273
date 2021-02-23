package com.example.contact_amarvirsingh_783273;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    EditText firstName, lastName, Address, phoneNumber, eMailId;
    Button saveBtn;

    
    HelperClass helper;
    


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        firstName = findViewById(R.id.etFirstName);
        lastName = findViewById(R.id.etLastName);
        Address = findViewById(R.id.etAddress);
        phoneNumber = findViewById(R.id.etPhoneNumber);
        eMailId = findViewById(R.id.etEmailAddress);
        saveBtn = findViewById(R.id.saveBtn);
        
        helper = new HelperClass(this);
        

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String first = firstName.getText().toString();
                String last = lastName.getText().toString();
                String phone = phoneNumber.getText().toString();
                String email = eMailId.getText().toString();
                String address = Address.getText().toString();
                
                
                
            long result = helper.insertContact(first, last, address,phone,email);
            if (result != -1){
                Toast.makeText(AddActivity.this, "Data added Successfully ", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddActivity.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
            else {
                Toast.makeText(AddActivity.this, "not added ", Toast.LENGTH_SHORT).show();
            }
                
            }
        });






    }
}