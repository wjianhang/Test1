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

import ui.login_page;
import ui.login_zhuce;

/**
 * Created by lakalaka on 2017/9/13/0013.
 * 
 */


public class Fragment_mine extends Fragment {


    private Button btn_login;
    private Button test22;
    private Context mcontext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);

        mcontext = this.getContext();

        //mob 注册  第三方

        btn_login = (Button) view.findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(mcontext, login_page.class);
                startActivity(i);

            }
        });
        test22= (Button) view.findViewById(R.id.btn_test2);
        test22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j=new Intent(mcontext, login_zhuce.class);
                startActivity(j);
            }
        });


        return view;
    }
}