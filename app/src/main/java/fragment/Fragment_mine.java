package fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.lakalaka.test1.R;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;
import ui.login_page;

/**
 * Created by lakalaka on 2017/9/13/0013.
 * 
 */

public class Fragment_mine extends Fragment {

    private Button btn_register;
    private Button btn_login;

    private Context mcontext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);

        mcontext = this.getContext();

        //mob 注册  第三方
        btn_register = (Button) view.findViewById(R.id.btn_Register);
        btn_login = (Button) view.findViewById(R.id.btn_login);
        btn_register.setOnClickListener(new View.OnClickListener() {
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

                registerPage.show(mcontext);
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(mcontext, login_page.class);
                startActivity(i);

            }
        });

        return view;
    }
}
