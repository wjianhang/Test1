package ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.example.lakalaka.test1.R;

/**
 * Created by lakalaka on 2017/9/16/0016.
 */

public class login_zhuce2 extends Activity {
    private Button btn_next;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhuce2);
        btn_next= (Button) findViewById(R.id.next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(login_zhuce2.this,login_zhuce3.class);
                startActivity(i);
            }
        });

    }
}
