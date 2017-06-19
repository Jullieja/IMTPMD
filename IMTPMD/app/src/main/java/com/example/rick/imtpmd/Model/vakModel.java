package com.example.rick.imtpmd.Model;

/**
 * Created by mjboere on 24-5-2017.
 */

public class vakModel {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEcts() {
        return String.valueOf(ects);
    }

    public void setEcts(int ects) {
        this.ects = ects;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public String getSoort() {
        return soort;
    }

    public void setSoort(String soort) {
        this.soort = soort;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String name;
    public int ects;
    public double grade;
    public int year;
    public boolean passed;
    public String soort;
    public String spec;

    public vakModel(String name, int ects, double grade, int year, String soort, String spec, boolean passed) {
        this.name = name;
        this.ects = ects;
        this.grade = grade;
        this.year = year;
        this.soort = soort;
        this.spec = spec;
        this.passed = passed;
    }
    public String getGrade(){
        return String.valueOf(grade);
    }


}
