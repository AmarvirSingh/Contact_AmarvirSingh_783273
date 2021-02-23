package com.example.contact_amarvirsingh_783273;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    FloatingActionButton addBtn;
    TextView total;
    ContactAdapter adapter;
    HelperClass helper;
    RecyclerView rv ;
    ArrayList<ContactModelClass> contactList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new HelperClass(this);

        addBtn = findViewById(R.id.addBtn);
        rv = findViewById(R.id.rv);
        total = findViewById(R.id.totalTextView);
        contactList =  helper.getAllContacts();

        if (contactList.size() > 0){
            adapter = new ContactAdapter(this,contactList,helper);
            total.setText(String.valueOf(contactList.size()));
            rv.setLayoutManager(new LinearLayoutManager(this));
            rv.setAdapter(adapter);
        }
        else {
            Toast.makeText(this, "No data Available ", Toast.LENGTH_SHORT).show();
        }
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_activity_menu,menu);

        MenuItem item = menu.findItem(R.id.search_id);

        SearchView searchView = (SearchView) item.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adapter.getFilter().filter(newText);

                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}