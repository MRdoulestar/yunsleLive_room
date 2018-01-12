package com.test.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by Doublestar on 2017/12/4 18:27).
 */
public class Linkman {
    int id;
    String name;
    String sex;
    String tel;
    String partment;
    String place;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date birthday;
    String hobby;
    int cid;

    public Linkman(){}

    public Linkman(String sex, String name, String tel, String partment, String place, Date birthday, String hobby) {
        this.sex = sex;
        this.name = name;
        this.tel = tel;
        this.partment = partment;
        this.place = place;
        this.birthday = birthday;
        this.hobby = hobby;
    }

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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPartment() {
        return partment;
    }

    public void setPartment(String partment) {
        this.partment = partment;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getCid() {
        return cid;
    }
}
