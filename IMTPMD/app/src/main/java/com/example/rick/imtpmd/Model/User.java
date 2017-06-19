package com.example.rick.imtpmd.Model;

/**
 * Created by rick on 15-6-2017.
 */

public class User {

    private String naam;
    private String passwd;
    private String spec;
    private int id;
    public User(int id, String name, String passwd, String spec) {
        this.id = id;
        this.naam = name;
        this.passwd = passwd;
        this.spec = spec;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return naam;
    }

    public void setName(String name) {
        this.naam = name;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }




}
