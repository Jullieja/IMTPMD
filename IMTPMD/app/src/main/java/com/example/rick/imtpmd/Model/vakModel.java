package com.example.rick.imtpmd.Model;

/**
 * Created by mjboere on 24-5-2017.
 */

public class vakModel {

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        naam = naam;
    }

    public String getEcts() {
        return String.valueOf(ects);
    }

    public void setEcts(int ects) {
        ects = ects;
    }

    public void setCijfer(double cijfer) {
        this.cijfer = cijfer;
    }

    public int getJaar() {
        return jaar;
    }

    public void setJaar(int jaar) {
        this.jaar = jaar;
    }

    public boolean isBehaald() {
        return behaald;
    }

    public void setBehaald(boolean behaald) {
        this.behaald = behaald;
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

    public String naam;
    public int ects;
    public double cijfer;
    public int jaar;
    public boolean behaald;
    public String soort;
    public String spec;

    public vakModel(String naam, int ects, double cijfer, int jaar, String soort, String spec,boolean behaald) {
        this.naam = naam;
        this.ects = ects;
        this.cijfer = cijfer;
        this.jaar = jaar;
        this.soort = soort;
        this.spec = spec;
        this.behaald=behaald;
    }
    public String getCijfer(){
        return String.valueOf(cijfer);
    }


}
