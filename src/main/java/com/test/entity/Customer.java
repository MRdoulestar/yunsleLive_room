package com.test.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by Doublestar on 2017/12/4 18:26).
 */
public class Customer {
    int id=0;
    String name=null;
    String profession=null;
    String province=null;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date uptime=null;
    long funds=0;
    String corporation=null;

    public Customer(){}

    public Customer(String name, String profession, String province, Date uptime, long funds, String corporation) {
        this.name = name;
        this.profession = profession;
        this.province = province;
        this.uptime = uptime;
        this.funds = funds;
        this.corporation = corporation;
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

    public String getProfession() {
        return profession;
    }
    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getProvince() {
        return profession;
    }
    public void setProvince(String province) {
        this.province = province;
    }

    public Date getUptime() {
        return uptime;
    }
    public void setUptime(Date uptime) {
        this.uptime = uptime;
    }

    public long getFunds() {
        return funds;
    }
    public void setFunds(long funds) {
        this.funds = funds;
    }

    public String getCorporation() {
        return corporation;
    }

    public void setCorporation(String corporation) {
        this.corporation = corporation;
    }

    @Override
    //重写equals，便于去重
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof Customer)) return false;
        Customer customer = (Customer)o;
        if(id!=customer.id) return false;
        return name!=null ? name.equals(customer.name) : customer.name ==null;
    }

    @Override
    //重写hashcode
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        return result;
    }
}
