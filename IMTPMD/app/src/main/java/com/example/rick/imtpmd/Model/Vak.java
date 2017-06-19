package com.example.rick.imtpmd.Model;

/**
 * Created by juliarijsbergen on 19-06-17.
 */

public class Vak {

    private String name;
    private String ects;
    private String year;
    private String spec;
    private String grade;
    private String user_id;


    public Vak(String name, String ects, String year, String spec) {
        this.name = name;
        this.ects = ects;
        this.year = year;
        this.spec = spec;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEcts() {
        return ects;
    }

    public void setEcts(String ects) {
        this.ects = ects;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getGrade() {
        return grade;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_id() {
        return user_id;
    }
}
