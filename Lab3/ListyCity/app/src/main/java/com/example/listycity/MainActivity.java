package com.example.listycity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    ListView cityList;
    String currentCity;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList= findViewById(R.id.city_list);
        String[] cities = {"Edmonton", "Vancouver", "Moscow", "Sydney","Berlin","Vienna","Tokyo","Bejing","Osaka","New Delhi"};
        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList); //places information to be formatted and mapped into data

        cityList.setAdapter(cityAdapter);

        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long arg3)
            {
                currentCity = (String)adapter.getItemAtPosition(position);
            }
        });

    }
    public void addCity(View view){
        EditText editText = (EditText) findViewById(R.id.editTextAddCity);
        String message = editText.getText().toString();
        for (String i : dataList){
            if(i.equals(message)) {
                return;
            }
        }
        if (message.equals("")) {
            return;
        }
        dataList.add(message);
        cityAdapter.notifyDataSetChanged();

    }
    public void deleteCity(View view){
        dataList.remove(currentCity);
        cityAdapter.notifyDataSetChanged();
        currentCity = "";

    }
}