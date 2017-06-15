package com.example.rick.imtpmd.Model;

/**
 * Created by mjboere on 24-5-2017.
 */

public class CourseModel {

    String name;
    String ects;
    String grade;

    public CourseModel(String name, String ects, String grade){
        this.name = name;
        this.ects = ects;
        this.grade = grade;
    }

    public String getName(){
        return name;
    }
    public String getEcts(){  return ects; }
    public String getGrade(){  return grade; }

}
