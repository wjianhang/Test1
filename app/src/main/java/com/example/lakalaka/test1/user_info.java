package com.example.lakalaka.test1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by lakalaka on 2017/9/19/0019.
 */

public class user_info extends Activity implements View.OnClickListener {
    private Context mcontext;
    private TextView text_user,text_nname,text_sex,text_autograph;


    private void toast(final String str) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText( mcontext, str, Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info);
        text_user= (TextView) findViewById(R.id.text_user);
        text_nname= (TextView) findViewById(R.id.text_nname);
        text_sex= (TextView) findViewById(R.id.text_sex);
        text_autograph= (TextView) findViewById(R.id.text_autograph);
        Bmob.initialize(this,"82a542cbad55f514667a516a51da7045");

        BmobQuery<User_information> user=new BmobQuery<User_information>();
        user.findObjects(new FindListener<User_information>() {
            @Override
            public void done(List<User_information> list, BmobException e) {
                if(e==null){
                }
            }
        });

        mcontext=this;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.icon_infoHead:
                break;
            case R.id.line_user:
               toast("用户名不可以再次设置哦~");
                break;
            case R.id.line_nname:
                AlertDialog.Builder builder = new AlertDialog.Builder(mcontext);
                builder.setTitle("标题");
                builder.setView(new EditText(mcontext));
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();


                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
                break;
            case R.id.line_sex:
                break;
            case R.id.line_autograph:
                break;
            case R.id.line_weibo:
                break;

        }

    }
}
