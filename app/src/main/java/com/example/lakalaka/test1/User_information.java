package com.example.lakalaka.test1;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.QueryListener;

import static cn.smssdk.utils.a.e;

/**
 * Created by lu17852172914 on 2017/9/15.
 */

public class User_information extends BmobObject {
    private String name;
    private String password;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}



