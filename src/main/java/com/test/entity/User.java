package com.test.entity;

/**
 * Created by Doublestar on 2017/11/24 11:15).
 */

/**
 * 实体类：属性对应数据库表的字段
 */
public class User {

    private int id;
    private String name;
    private String password;
    private String passwd_hash;

    public User(){}
    public User(String name, String password){
        this.name = name;
        this.password = password;
    }
    public User(String name, String password, String passwd_hash){
        this.name = name;
        this.password = password;
        this.passwd_hash = passwd_hash;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String passwd) {
        this.password = passwd;
    }

    public String getPasswd_hash() {
        return passwd_hash;
    }

    public void setPasswd_hash(String passwd_hash) {
        this.passwd_hash = passwd_hash;
    }
    @Override
    public String toString() {
        return "User{" +
                "name=" + name +
                ", password='" + password + '\'' +
                ", passwd_hash='" + passwd_hash + '\'' +
                '}';
    }
}
