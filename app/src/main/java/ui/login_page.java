package ui;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lakalaka.test1.Person;
import com.example.lakalaka.test1.R;
import com.example.lakalaka.test1.User_information;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SQLQueryListener;

import static cn.smssdk.utils.a.e;
import static com.example.lakalaka.test1.R.id.ceshi1;
import static com.example.lakalaka.test1.R.id.user_name;

/**
 * Created by lakalaka on 2017/9/15/0015.
 *
 *
 */

public class login_page extends Activity{
    private EditText username;
    private EditText password;
    private TextView ceshi;
    private TextView ceshi1;
    private Button btn_sign;
    private TextView txt_zhuce;

    /**
     *
     */
    private void getInformation(final String username,String password){
        String sql="select user_name from User_information where user_name=? and user_password=?";
        BmobQuery<User_information> bmobQuery=new BmobQuery<User_information>();
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
        },username,password);

        /*bmobQuery.addWhereEqualTo("user_name",username);
        bmobQuery.addWhereEqualTo("user_password",password);*/



    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        username= (EditText) findViewById(user_name);
        password= (EditText) findViewById(R.id.user_password);
        ceshi= (TextView) findViewById(R.id.ceshi);
        ceshi1= (TextView) findViewById(R.id.ceshi1);
        btn_sign= (Button) findViewById(R.id.sign);
        Bmob.initialize(this,"82a542cbad55f514667a516a51da7045");
        btn_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().trim().equals("")||password.getText().toString().trim().equals("")){
                    Toast.makeText(login_page.this,"用户名或者密码不能为空",Toast.LENGTH_SHORT).show();
                }
                else {

                    final String userName=username.getText().toString().trim();
                    final String userPassword=password.getText().toString().trim();
                    getInformation(userName,userPassword);

//                    ceshi.setText(userName);
//                    ceshi1.setText(userPassword);

                    /*BmobQuery<User_information> bmobQuery = new BmobQuery<User_information>();
                    bmobQuery.getObject(userName, new >QueryListener<User_information>() {
                        @Override
                        public void done(User_information object,BmobException e) {
                            if(e==null){

                            }else{
                                //用户名不正确
                            }
                        }
                    });*/



                }

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
