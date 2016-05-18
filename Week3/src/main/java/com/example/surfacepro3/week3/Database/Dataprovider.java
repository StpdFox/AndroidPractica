package com.example.surfacepro3.week3.Database;

/**
 * Created by Surface Pro 3 on 14-5-2016.
 */
public class Dataprovider {
    private String name;
    private String day;
    private String month;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Dataprovider(String name, String day, String month){
        this.name = name;
        this.day = day;
        this.month = month;
    }
}
