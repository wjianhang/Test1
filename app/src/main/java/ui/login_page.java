package ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lakalaka.test1.R;
import com.example.lakalaka.test1.StatusBarCompat;
import com.example.lakalaka.test1.User_information;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;



/**
 * Created by lakalaka on 2017/9/15/0015.
 *
 *
 */

public class login_page extends Activity{
    private EditText username;
    private EditText password;
    private Button btn_sign;
    private TextView txt_zhuce;
    private Context mcontext;

    /**
     *
     */
    private void toast(final String str) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(login_page.this, str, Toast.LENGTH_SHORT).show();
            }
        });
    }

    //private void getInformation(final String username, String password){
        //String sql="select user_name from User_information where user_name=? and user_password=?";
        /*BmobQuery<User_information> bmobQuery=new BmobQuery<User_information>();
        bmobQuery.doSQLQuery(sql,new SQLQueryListener<User_information>() {
            @Override
            public void done(BmobQueryResult<User_information> bmobQueryResult, BmobException e) {
                    if (e==null){

                        Toast.makeText(login_page.this,"登陆成功",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(login_page.this,"登陆失败"+e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
            }
        },username,password);*/






    //}

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        StatusBarCompat.compat(this, 0xFFFF6347);  //沉浸状态栏

        mcontext=this;
        username= (EditText) findViewById(R.id.edit_user_name);
        password= (EditText) findViewById(R.id.edit_user_password);
        btn_sign= (Button) findViewById(R.id.sign);



        Bmob.initialize(this,"82a542cbad55f514667a516a51da7045");
        btn_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 final String usernm=username.getText().toString();
                 final String userpwd=password.getText().toString();
                if(usernm.equals("")||userpwd.equals("")){
                    Toast.makeText(mcontext, "帐号或密码不能为空", Toast.LENGTH_LONG).show();
                    return;
                }
                BmobQuery<User_information> query=new BmobQuery<User_information>();
                query.addWhereEqualTo("user_name", usernm);
                query.addWhereEqualTo("user_password", userpwd);
                query.findObjects(new FindListener<User_information>() {
                    @Override
                    public void done(List<User_information> list, BmobException e) {
                        if(e==null){
                            String gname=list.get(0).getName().toString();
                            String gpassword=list.get(0).getPassword().toString();
                            if(gname.equals(usernm)&&gpassword.equals(userpwd))
                            {
                                toast("登陆成功");
                            }
                        }
                        else{
                            Toast.makeText(mcontext, "帐号或密码有误", Toast.LENGTH_LONG).show();
                        }

                    }

                    /*@Override
                    public void done(List<user> arg0, BmobException e) {
                        // TODO Auto-generated method stub
                        if(e==null){
                            String gname=arg0.get(0).getName().toString();
                            String gpassword=arg0.get(0).getPassword().toString();
                            String name=mname.getText().toString();
                            String password=mpassword.getText().toString();
                            Toast.makeText(SecondActivity.this, gname, Toast.LENGTH_LONG).show();
                            if(gname.equals(name)&&gpassword.equals(password))
                            {
                                Intent seccess = new Intent();
                                seccess.setClass(SecondActivity.this, ThridActivity.class);
                                startActivity(seccess);
                            }

                        }
                        else{
                            Toast.makeText(SecondActivity.this, "帐号或密码有误", Toast.LENGTH_LONG).show();
                        }

                    }*/
                });


            }



        });
        txt_zhuce= (TextView) findViewById(R.id.zhuce);
        txt_zhuce.setClickable(true);
        txt_zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(login_page.this,login_zhuce.class);
                startActivity(i);
            }


        });

       /* btn_login= (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
        }





}
