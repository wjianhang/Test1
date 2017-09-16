package ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lakalaka.test1.R;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

/**
 * Created by lakalaka on 2017/9/15/0015.
 *
 *
 */

public class login_page extends Activity{
    private Button btn_login;
    private TextView txt_zhuce;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        txt_zhuce= (TextView) findViewById(R.id.zhuce);
        txt_zhuce.setClickable(true);
        txt_zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterPage registerPage = new RegisterPage();
                registerPage.setRegisterCallback(new EventHandler() {
                    public void afterEvent(int event, int result, Object data) {
                        // 解析结果
                        if (result == SMSSDK.RESULT_COMPLETE) {
                            //提交验证码成功
                            if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {

                            }
                            //提交验证码成功，此时已经验证成功了
                            else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                            }
                        }
                    }
                });

                registerPage.show(login_page.this);
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
