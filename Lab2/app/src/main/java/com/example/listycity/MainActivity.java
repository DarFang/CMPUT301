package com.example.listycity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements AddCityFragment.OnFragmentInteractionListener {
    ListView cityList;
    ArrayAdapter<City> cityAdapter;
    ArrayList<City> dataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);

        String []cities ={"Edmonton", "Vancouver", "Toronto", "Hamilton"};
        String []provinces = {"AB","BC","ON","ON"};
        dataList = new ArrayList<>();
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AddCityFragment temp = AddCityFragment.newInstance(dataList.get(i));
                temp.show(getSupportFragmentManager(), "Edit City");
            }
        });
        for(int i = 0; i < cities.length; i++) {
            dataList.add((new City(cities[i], provinces[i])));

        }

        cityAdapter = new CustomList(this, dataList);


        cityList.setAdapter(cityAdapter);
        final FloatingActionButton addCityButton = findViewById(R.id.addCityButton);
        addCityButton.setOnClickListener((v) -> new AddCityFragment().show(getSupportFragmentManager(), "ADD CITY"));

    }
    public void onOkPressed(City newCity) {
        if(newCity != null) {
            cityAdapter.add(newCity);
        }
    }


}