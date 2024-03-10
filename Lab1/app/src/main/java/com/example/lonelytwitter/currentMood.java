package com.example.lonelytwitter;

import java.util.Date;

public abstract class currentMood {
    private Date date;

    public currentMood(Date date) {
        this.date = date;
    }

    public currentMood() {
        this.date = new Date();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
