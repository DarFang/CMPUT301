package com.example.rollcount;

import android.content.*;
import java.util.UUID;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class GameSession implements Serializable {
    /*
    model of game session, contains properties, and histogram, has a load game session and save game session when exiting

    Citation:
    Saving files in a folder and loading them:
    Author: Andrei Suvorkov from https://stackoverflow.com/questions/5694385/getting-the-filenames-of-all-files-in-a-folder
     */
    private String date;
    private String name;
    private int Ndice;
    private int sides;
    private String fileName;
    private Histogram histogram;
    GameSession(Context ctx, String fileName) {
        this.fileName = fileName;
        try {
            FileInputStream fin = new FileInputStream(new File(fileName));
            ObjectInputStream ois = new ObjectInputStream(fin);
            GameSession session = (GameSession) ois.readObject();
            this.date = session.date;
            this.name = session.name;
            this.Ndice = session.Ndice;
            this.sides = session.sides;
            this.histogram = session.histogram;
            ois.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public GameSession(Context context, String date, String name, int Ndice, int sides) {
        this.date = date;
        this.name = name;
        this.Ndice = Ndice;
        this.sides = sides;
        String unique = UUID.randomUUID().toString();
        fileName = context.getFilesDir().getAbsolutePath() + "/userData/" + unique + ".userData";
        this.histogram = new Histogram(Ndice, sides);

    }
    public int getCount(){
        return this.histogram.getTotalCount();
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDate() {
        return  date;
    }

    public String getName() {
        return name;
    }
    public String getStats(){
        return this.histogram.getStats();
    }
    public void addDice(){
        if (this.Ndice < 3 ) {
            this.Ndice++;
        }
    }

    public String getDice() {
        return String.valueOf(this.Ndice) + "d" + String.valueOf(this.sides);
    }

    public String getHistogram() {
        return this.histogram.getHistogram();
    }
    public String[] getListResults(){
        return this.histogram.getListResults();
    }
    public void delete() {
        File temp = new File(fileName);
        temp.delete();
    }
    public void storeData() {
        try{
            histogram.lastSelect = 0;
            FileOutputStream fout = new FileOutputStream(new File(fileName), false);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(this);
            oos.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void undo(){
        this.histogram.undoRoll();
    }
    public void addRoll(int i){
        this.histogram.addRoll(i);
    }
}
