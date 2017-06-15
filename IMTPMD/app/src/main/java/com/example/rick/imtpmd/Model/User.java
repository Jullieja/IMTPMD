package com.example.rick.imtpmd.Model;

/**
 * Created by rick on 15-6-2017.
 */

public class User {


    public User(int id, String name, String passwd, String spec) {
        this.id = id;
        this.name = name;
        this.passwd = passwd;
        this.spec = spec;
    }

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    private String name;
    private String passwd;
    private String spec;


}
