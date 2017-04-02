package br.edu.ufcg.empsoft.models;

/**
 * Created by stenio on 3/18/2017.
 */

public class Agendamento {
    private int year;
    private int month;
    private int day;
    private String id;

    public Agendamento() {
        setYear(0);
        setMonth(0);
        setDay(0);
    }

    public Agendamento(int year, int month, int day) {
        setYear(year);
        setMonth(month);
        setDay(day);
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return day + "/" + month + "/" + year;
    }
}
