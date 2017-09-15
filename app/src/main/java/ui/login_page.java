package ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.example.lakalaka.test1.R;

/**
 * Created by lakalaka on 2017/9/15/0015.
 *
 *
 */

public class login_page extends Activity implements View.OnClickListener{
    private Button btn_login;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
    }

    @Override
    public void onClick(View v) {

    }
}
