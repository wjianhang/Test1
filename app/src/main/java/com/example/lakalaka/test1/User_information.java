package com.example.lakalaka.test1;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.QueryListener;

import static cn.smssdk.utils.a.e;

/**
 * Created by lu17852172914 on 2017/9/15.
 */

public class User_information extends BmobObject {
    private String user_name;
    private String user_password;


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



}



