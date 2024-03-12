package com.example.rollcount;

import java.io.Serializable;
import java.util.ArrayList;

public class Histogram implements Serializable {
    /**
     * contains array rolls, a last select for undo, dice properties,
     */
    private int totalCount;
    int rollCount = 0;
    ArrayList<Roll> rolls;
    int lastSelect = 0;
    int Ndice, sides;
    public Histogram(int Ndice, int sides) {
        this.Ndice = Ndice;
        this.sides = sides;
        rolls = new ArrayList<>();
        setArraySize();
        totalCount = 0;
    }
    public void setArraySize(){
        for (int j = 0; j <Ndice; j++) {
            rollCount++;
            if (rollCount < 4) {
                for (int i = (sides * (rollCount - 1)) + 1; i <= sides * rollCount; i++) {
                    rolls.add(new Roll(0, i));
                }
                if (rollCount > 1) {
                    rolls.remove(0);
                }
            }
        }
    }
    public int getTotalCount(){
        return totalCount;
    }
    public int getRollsCount(){
        int output = 0;
        for (Roll i : rolls){
            output += i.count;
        }
        return output;
    }
    public String getStats(){
        int max, min, total = 0, outCount;
        if (rolls.size()>0){
            max = rolls.get(0).count;
            min = rolls.get(0).count;
            for (Roll i : rolls){
                if (i.count > max){
                    max = i.count;
                }
                else if (i.count < min){
                    min = i.count;
                }
                total += i.count;
            }
            if (totalCount == 0){
                outCount = 1;

            }
            else {
                outCount = totalCount;
            }
            return "Max: " + String.valueOf(max) + "\r\n"
                    + "Min: " + String.valueOf(min) + "\r\n"
                    + "Avg: " + String.valueOf(total/rolls.size()) + "\r\n";
        }
        else{
            return "";
        }
    }
    public String[] getListResults(){
         String[] output= new String[rolls.size()];

        for (int i = 0; i <rolls.size(); i++){
            output[i] = String.valueOf(rolls.get(i).result);
        }
        return output;
    }
    public String getHistogram() {
        String output = "";
        for (Roll i : rolls){
            output += String.valueOf(i.result);
            output += "(" + String.valueOf(i.count) + ")";
            for (int j = 0; j < Math.floor((i.count)/10); j++ ){
                output += "#";
            }
            output += "\r\n";

        }
        return output;
    }
    public void addRoll(int result){
        for (Roll i : rolls){
            if(i.result == result){
                i.count++;
                lastSelect = result;
                totalCount++;
                return;
            }
        }
        return;
    }
    public void undoRoll(){
        Roll temp = null;
        for(Roll i : rolls){
            if(i.result == lastSelect){
                temp = i;
                break;
            }
        }
        if (temp != null){
            if(temp.count > 0){
                totalCount --;
                temp.count --;
            }
        }
        lastSelect = 0;
    }
}
