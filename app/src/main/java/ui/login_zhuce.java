package ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lakalaka.test1.R;
import com.example.lakalaka.test1.User_information;

import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by lakalaka on 2017/9/16/0016.
 *
 */

public class login_zhuce extends Activity{
    private ProgressDialog mPd;
    private TimerTask tt;
    private Timer tm;
    private String phone;
    public String country="86";
    private  int CODE_REPEAT = 1;
    private int TIME = 60;
    private Button btn_send,btn_end;
    private LinearLayout line1,line2,line3,line3_2;
    private int detect=1;
    private EditText edit_send,edit_valida,edit_pswd,edit_pswd_2;

    private TextView text_1,text_2,text_3,text_sphone;
    private Context mcontext;

    Handler hd = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == CODE_REPEAT) {
                btn_send.setEnabled(true);
                tm.cancel();//取消任务
                tt.cancel();//取消任务
                TIME = 60;//时间重置
                btn_send.setText("重新获取");
            }else {
                btn_send.setText(TIME + "重新获取");
            }
        }
    };

    EventHandler eh=new EventHandler(){
        @Override
        public void afterEvent(int event, int result, Object data) {
            if (result == SMSSDK.RESULT_COMPLETE) {
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    toast("验证成功");
                }else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){       //获取验证码成功
                    toast("获取验证码成功");
                }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){//如果你调用了获取国家区号类表会在这里回调
                    //返回支持发送验证码的国家列表
                }

            }
            else{//错误等在这里（包括验证失败）
                //错误码请参照http://wiki.mob.com/android-api-错误码参考/这里我就不再继续写了
                ((Throwable)data).printStackTrace();
                String str = data.toString();
                toast(str);
            }
        }
    };
    //吐司的一个小方法
    private void toast(final String str) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText( mcontext, str, Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhuce1);
        Bmob.initialize(this,"82a542cbad55f514667a516a51da7045");
        btn_send= (Button) findViewById(R.id.btn_send);
        btn_end= (Button) findViewById(R.id.btn_end);
        edit_send= (EditText) findViewById(R.id.edt_send);
        edit_pswd= (EditText) findViewById(R.id.edit_pswd);
        edit_valida= (EditText) findViewById(R.id.edit_valida);
        edit_pswd_2= (EditText) findViewById(R.id.edit_pswd_2);
        mcontext=this;
        SMSSDK.registerEventHandler(eh); //注册短信回调（记得销毁，避免泄露内存）
       // btn_end.setEnabled(true);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone = edit_send.getText().toString().trim().replaceAll("/s","");
                if (!TextUtils.isEmpty(phone)) {
                    //定义需要匹配的正则表达式的规则
                    String REGEX_MOBILE_SIMPLE =  "[1][3578]\\d{9}";
                    //把正则表达式的规则编译成模板
                    Pattern pattern = Pattern.compile(REGEX_MOBILE_SIMPLE);
                    //把需要匹配的字符给模板匹配，获得匹配器
                    Matcher matcher = pattern.matcher(phone);
                    // 通过匹配器查找是否有该字符，不可重复调用重复调用matcher.find()
                    if (matcher.find()) {//匹配手机号是否存在
                        alterWarning();
                    } else {
                        toast("手机号格式错误");
                    }
                } else {
                    toast("请先输入手机号");
                }


            }
        });
        btn_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获得用户输入的验证码
                String code = edit_valida.getText().toString().replaceAll("/s","");
                if (!TextUtils.isEmpty(code)) {//判断验证码是否为空
                    //验证
                    SMSSDK.submitVerificationCode( country,  phone,  code);
                    String user_password=edit_pswd.getText().toString();
                    String pawd2=edit_pswd_2.getText().toString();

                    if(user_password.equals("")||pawd2.equals("")){
                        toast("请输入完整");
                    }else{
                        if(user_password==pawd2){
                            User_information p2 = new User_information();
                            p2.setName(phone);
                            p2.setPassword(user_password);
                            p2.save(new SaveListener<String>() {
                                @Override
                                public void done(String s, BmobException e) {
                                    if(e==null){
                                        toast("注册成功");
                                    }else{
                                        toast("失败");
                                    }
                                }
                            });
                        }
                    }

                }else{//如果用户输入的内容为空，提醒用户
                    toast("请输入验证码后再提交");
                }

                if(detect==1) {


                }

        }});

    }
    private void alterWarning() {
        // 2. 通过sdk发送短信验证
        AlertDialog.Builder builder = new AlertDialog.Builder(mcontext);  //先得到构造器
        builder.setTitle("提示"); //设置标题
        builder.setMessage("我们将要发送到" + phone + "验证"); //设置内容
        builder.setIcon(R.mipmap.ic_launcher);//设置图标，图片id即可
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            //设置确定按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); //关闭dialog
                // 2. 通过sdk发送短信验证（请求获取短信验证码，在监听（eh）中返回）
                SMSSDK.getVerificationCode(country, phone);
                //做倒计时操作
                Toast.makeText(mcontext, "已发送" + which, Toast.LENGTH_SHORT).show();
//                btn_send.setEnabled(false);
                tm = new Timer();
                tt = new TimerTask() {
                    @Override
                    public void run() {
                        hd.sendEmptyMessage(TIME--);
                    }
                };
                tm.schedule(tt,0,1000);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() { //设置取消按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(mcontext, "已取消" + which, Toast.LENGTH_SHORT).show();
            }
        });
        //参数都设置完成了，创建并显示出来
        builder.create().show();
    }

}



