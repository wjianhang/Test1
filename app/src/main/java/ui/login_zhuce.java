package ui;

import android.app.Activity;
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

import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by lakalaka on 2017/9/16/0016.
 *
 */

public class login_zhuce extends Activity implements View.OnClickListener{
    private TimerTask tt;
    private Timer tm;
    private Button btn_send;
    private Button btn_next;
    private Button btn_end;
    private Button btn_again;

    private LinearLayout line1;
    private LinearLayout line2;
    private LinearLayout line3;
    private LinearLayout line3_2;

    private EditText edit_send;
    private EditText edit_valida;
    private EditText edit_pswd;
    private EditText edit_pswd_2;

    private TextView text_1;
    private TextView text_2;
    private TextView text_3;

    private Context mcontext;
    private int TIME = 10;//倒计时60s这里应该多设置些因为mob后台需要60s,我们前端会有差异的建议设置90，100或者120
    public String country="86";//这是中国区号，如果需要其他国家列表，可以使用getSupportedCountries();获得国家区号
    private String phone;
    private static final int CODE_REPEAT = 1; //重新发送
    Handler hd = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == CODE_REPEAT) {
                btn_again.setEnabled(true);
                btn_next.setEnabled(true);
                tm.cancel();//取消任务
                tt.cancel();//取消任务
                TIME = 10;//时间重置
                btn_again.setText("重新获取");
            }else {
                btn_again.setText(TIME + "重新获取");
            }
        }
    };
    //回调
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
            }else{//错误等在这里（包括验证失败）
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
        text_1= (TextView) findViewById(R.id.text_1);
        text_2= (TextView) findViewById(R.id.text_2);
        text_3= (TextView) findViewById(R.id.text_3);
        btn_send= (Button) findViewById(R.id.btn_send);
        btn_next= (Button) findViewById(R.id.btn_next);
        btn_again= (Button) findViewById(R.id.btn_again);
        btn_end= (Button) findViewById(R.id.btn_end);
        line1= (LinearLayout) findViewById(R.id.line_first);
        line2= (LinearLayout) findViewById(R.id.line_second);
        line3= (LinearLayout) findViewById(R.id.line_third);
        line3_2= (LinearLayout) findViewById(R.id.line_third_2);
        edit_send= (EditText) findViewById(R.id.edt_send);
        edit_pswd= (EditText) findViewById(R.id.edit_pswd);
        edit_valida= (EditText) findViewById(R.id.edit_valida);
        edit_pswd_2= (EditText) findViewById(R.id.edit_pswd_2);
        showLine( View.VISIBLE,View.GONE, View.GONE);
         textColor(R.color.tomato,R.color.gray,R.color.gray);




        SMSSDK.registerEventHandler(eh); //注册短信回调（记得销毁，避免泄露内存）


        mcontext=this;
        initView();
    }
    private void initView() {
        btn_send.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_send:
                phone = edit_send.getText().toString().trim().replaceAll("/s","");
                if (!TextUtils.isEmpty(phone)) {
                    //定义需要匹配的正则表达式的规则
                    String REGEX_MOBILE_SIMPLE = "[1][3578]\\d{9}";
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
                break;
        }


    }
    private void alterWarning() {
        // 2. 通过sdk发送短信验证
        AlertDialog.Builder builder = new AlertDialog.Builder(this);  //先得到构造器
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
                Toast.makeText(mcontext, "已发送", Toast.LENGTH_SHORT).show();
                btn_again.setEnabled(false);
                btn_next.setEnabled(true);
                tm = new Timer();
                tt = new TimerTask() {
                    @Override
                    public void run() {
                        hd.sendEmptyMessage(TIME--);
                    }
                };
                tm.schedule(tt,0,1000);
                showLine(View.GONE,View.VISIBLE,View.GONE);
                textColor(R.color.gray,R.color.tomato,R.color.gray);
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


    /**
     * 设置控件布局显隐
     * @param first
     * @param second
     * @param third
     */
    private void showLine(int first,int second,int third){

        line1.setVisibility(first);
        edit_send.setVisibility(first);
        btn_send.setVisibility(first);
        line2.setVisibility(second);
        edit_valida.setVisibility(second);
        btn_next.setVisibility(second);
        btn_again.setVisibility(second);
        line3.setVisibility(third);
        line3_2.setVisibility(third);
        edit_pswd.setVisibility(third);
        edit_pswd_2.setVisibility(third);
        btn_end.setVisibility(third);
    }
    /**
     * 文字颜色改变
     * @param color1
     * @param color2
     * @param color3
     */
    private void textColor(int color1,int color2,int color3){

        text_1.setTextColor(getResources().getColor(color1));
        text_2.setTextColor(getResources().getColor(color2));
        text_3.setTextColor(getResources().getColor(color3));
    }


}

