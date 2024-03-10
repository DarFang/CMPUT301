package com.example.lonelytwitter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImportantTweet importantTweet = new ImportantTweet("");
        importantTweet.setMessage("Hello") ;
        importantTweet.getMessage();
        importantTweet.setDate(new Date());
        NormalTweet normalTweet = new NormalTweet("Hi");



        ArrayList<Tweet>tweetList = new ArrayList<Tweet>();
        tweetList.add(importantTweet);
        tweetList.add(normalTweet);


        //test case for mood
        Happy mood1 = new Happy();
        Happy mood2 = new Happy(new Date());
        Sad mood3 = new Sad();
        Sad mood4 = new Sad(new Date());
        mood1.getMood();
        mood1.setDate(new Date());
        mood2.getMood();
        mood2.setDate(new Date());
        mood3.getMood();
        mood3.setDate(new Date());
        mood4.getMood();
        mood4.setDate(new Date());
        mood4.getMood();
        mood4.getDate();

    }
}