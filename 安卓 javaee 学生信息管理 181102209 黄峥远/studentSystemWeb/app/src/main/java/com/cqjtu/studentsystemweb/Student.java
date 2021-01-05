package com.cqjtu.studentsystemweb;

import java.io.Serializable;

public class Student  implements Serializable {
    private String name;
    private String id;
    private String major;
    private String age;
    private String sex;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Student(String id, String name, String sex, String age, String major) {
        this.id=id;
        this.name=name;
        this.sex=sex;
        this.age=age;
        this.major=major;
    }
    public Student(){

    }
    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName(){
        return name;
    }
    public String getMajor(){
        return major;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setMajor(String major){
        this.major=major;
    }
}

