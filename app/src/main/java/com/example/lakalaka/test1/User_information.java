package com.example.lakalaka.test1;

import cn.bmob.v3.BmobObject;

/**
 * Created by lu17852172914 on 2017/9/15.
 */

public class User_information extends BmobObject {
    private String user_name;
    private String user_password;
    private String nickname;
    private String user_sex;
    private String signature;
    private String pay_password;


    public String getName(){return user_name;}
    public void setName(String name) {
        this.user_name = name;
    }
    public String getPassword() {
        return user_password;
    }
    public void setPassword(String password) {
        this.user_password = password;
    }
    public  String getNickname(){return nickname;}
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public  String getUser_sex(){return user_sex;}
    public void setUser_sex(String sex) {
        this.user_sex = sex;
    }
    public  String getSignature(){return signature;}
    public void setSignature(String signature) {
        this.signature = signature;
    }
    public  String getPay_password(){return pay_password;}
    public void setPay_password(String pay_password) {
        this.pay_password = pay_password;
    }



}



