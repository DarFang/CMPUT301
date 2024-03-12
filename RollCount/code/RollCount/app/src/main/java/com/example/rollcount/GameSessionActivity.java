package com.example.rollcount;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;

public class GameSessionActivity extends AppCompatActivity implements SessionFragment.OnFragmentInteractionListener  {
    /*
    Gui of the single session, contains buttons for listeners, undo, save, delete, histogram view, stats view, (missing add roll)
     */
    private GameSession session;
    String lastSelect ;
    ArrayList<String> dataList;
    ArrayAdapter<String> sessionAdapter;
    private Button undoButton, histogramButton, deleteButton,saveButton, statsButton, editButton;
    private TextView totalcount;
    ListView resultList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_session);
        resultList= findViewById(R.id.result_list);

        Intent intent = getIntent();
        session = (GameSession) intent.getSerializableExtra("GameSession");
        String title = session.getName();
        String date = session.getDate();
        String dicetype = session.getDice();
        String [] resultnumbers = session.getListResults();
        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(resultnumbers));

        sessionAdapter = new ArrayAdapter<>(this, R.layout.result_content, dataList);

        resultList.setAdapter(sessionAdapter);

        resultList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long arg3)
            {
                lastSelect = (String)adapter.getItemAtPosition(position);
                int roll = Integer. parseInt(lastSelect);
                session.addRoll(roll);
                totalcount.setText("Roll count: " + String.valueOf(session.getCount()));

            }
        });





        title += " " +date + " " + dicetype;
        this.setTitle(title);
        undoButton = findViewById(R.id.undoButton);
        histogramButton = findViewById(R.id.histogramButton);
        deleteButton = findViewById(R.id.deleteButton);
        statsButton = findViewById(R.id.Stats);
        totalcount = findViewById(R.id.totalCount);
        saveButton = findViewById(R.id.saveButton);
        editButton = findViewById(R.id.edit);
        totalcount.setText("Roll count: " + String.valueOf(session.getCount()));
        undoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                session.undo();
                totalcount.setText("Roll count: " + String.valueOf(session.getCount()));

            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                session.storeData();

            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SessionFragment temp = SessionFragment.newInstance(session);
                temp.show(getSupportFragmentManager(), "session");


            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                session.delete();
                setResult(2);
                finish();
            }
        });

        histogramButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                histogramFragment temp = histogramFragment.newInstance(session);
                temp.show(getSupportFragmentManager(), "Histogram");

            }
        });
        statsButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StatsFragment temp = StatsFragment.newInstance(session);
                temp.show(getSupportFragmentManager(), "Histogram");
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("GSessionRes", session);
        setResult(1, intent);
        super.onBackPressed();
    }
    public void onOkPressed(String s) {
        this.setTitle(s);
    }

    @Override
    public void onOkPressed(GameSession newGameSession) {
        return;
    }
}
