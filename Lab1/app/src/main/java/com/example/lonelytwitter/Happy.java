package com.example.lonelytwitter;

import java.util.Date;

public class Happy extends currentMood{
    private Date date;
    private String mood;

    public Happy(Date date) {
        super(date);
        this.mood = "Happy";
    }

    public Happy() {
        this.mood = "Happy";
    }

    public String getMood() {
        return mood;
    }
}
