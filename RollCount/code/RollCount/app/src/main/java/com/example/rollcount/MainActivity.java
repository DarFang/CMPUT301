package com.example.rollcount;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SessionFragment.OnFragmentInteractionListener  {
    /*
    contains lists of game sessions, uses customList to display
     */
    ListView sessionlist;
    ArrayAdapter<GameSession> sessionAdapter;
    ArrayList<GameSession> dataList;
    ActivityResultLauncher<Intent> ac = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == 1) {
                        Intent intent = result.getData();
                        GameSession session = (GameSession) intent.getSerializableExtra("GSessionRes");
                        dataList.set(index, session);
                    }
                    else if(result.getResultCode() == 2) {
                        dataList.remove(index);
                    }
                    sessionAdapter.notifyDataSetChanged();
                }
            }
    );;


    private Context ctx;
    private int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sessionlist = findViewById(R.id.sessionList);
        String [] session = {};
        dataList = new ArrayList<>();
        ctx = this;
        String path = ctx.getFilesDir().getAbsolutePath() +"/userData";
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        Log.i("MainActivity", "getPath: " + path);
        if(listOfFiles != null) {
            for (int i = 0; i < listOfFiles.length; i++) {
                GameSession temp = new GameSession(ctx, listOfFiles[i].getAbsolutePath());
                dataList.add(temp);
            }
        }
        else {
            new File(ctx.getFilesDir(), "/userData/").mkdir();
        }

        sessionlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            // click on item
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ctx, GameSessionActivity.class);
                intent.putExtra("GameSession", dataList.get(i));
                index = i;
                ac.launch(intent);
            }
        });
        sessionAdapter = new CustomList(this, dataList);
        sessionlist.setAdapter(sessionAdapter);
        final FloatingActionButton addCityButton = findViewById(R.id.addSessionButton);
        addCityButton.setOnClickListener((v) -> new SessionFragment().show(getSupportFragmentManager(), "Add session"));
    }
    public void onOkPressed(GameSession newGameSession) {
        // fragment button
        if(newGameSession != null) {
            sessionAdapter.add(newGameSession);
        }
    }
}