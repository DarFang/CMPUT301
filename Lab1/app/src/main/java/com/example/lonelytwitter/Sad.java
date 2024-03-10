package com.example.lonelytwitter;

import java.util.Date;

public class Sad extends currentMood{
    private Date date;
    private String mood;

    public Sad(Date date) {
        super(date);
        this.mood = "Sad";
    }

    public Sad() {
        this.mood = "Sad";
    }
    public String getMood() {
        return mood;
    }

}
