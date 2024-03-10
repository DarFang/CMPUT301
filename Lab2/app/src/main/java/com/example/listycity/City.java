package com.example.listycity;

import java.io.Serializable;

public class City implements Serializable {
    private String city;
    private String province;
    City(String city, String province){
        this.city = city;
        this.province = province;
    }

    public String getCityName() {
        return city;
    }

    public String getProvinceName() {
        return province;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public void setProvince(String province) {
        this.province = province;
    }

}
