package ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lakalaka.test1.R;

/**
 * Created by lakalaka on 2017/9/16/0016.
 */

public class login_zhuce extends Activity {
    private Button btn_send;
    private EditText editText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhuce1);
        editText= (EditText) findViewById(R.id.edt_send);
        editText.setInputType(InputType.TYPE_CLASS_PHONE);
        btn_send= (Button) findViewById(R.id.btn_send);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(login_zhuce.this,login_zhuce2.class);
                startActivity(i);



            }
        });


    }
}
