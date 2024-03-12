package com.example.rollcount;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

class Roll implements Serializable{
    /*
    contains count of result, and the result value of dice roll
     */
    public int count, result;
    Roll(int count, int result) {
        this.count = count;
        this.result = result;
    }


}
