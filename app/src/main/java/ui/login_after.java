package ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.lakalaka.test1.R;
import com.example.lakalaka.test1.User_information;
import com.example.lakalaka.test1.user_info;

/**
 * Created by lu17852172914 on 2017/9/20.
 */

public class login_after extends Activity{
    private ImageButton user_icon;
    private TextView user_name;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_after);
        user_icon= (ImageButton) findViewById(R.id.user_icon);
        user_name= (TextView) findViewById(R.id.user_name);
        user_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(login_after.this,user_info.class);
                startActivity(i);
            }
        });

    }
}
