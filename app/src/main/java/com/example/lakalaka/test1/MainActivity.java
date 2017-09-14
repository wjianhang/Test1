package com.example.lakalaka.test1;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import fragment.Fragment_articles;
import fragment.Fragment_dynamic;
import fragment.Fragment_mine;
import fragment.Fragment_project;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener,ViewPager.OnPageChangeListener{

    private ViewPager vpager;

    private List<Fragment> mFragmentList;
    private RadioGroup group;
    private RadioButton radioButton1,radioButton2,radioButton3,radioButton4;

    private MyFragmentPagerAdapter madpter;

    public static final int PAGE_ONE=0;
    public static final int PAGE_TWO=1;
    public static final int PAGE_THREE=2;
    public static final int PAGE_FOUR=3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        StatusBarCompat.compat(this,Color.RED);




        mFragmentList=new ArrayList<Fragment>();
        mFragmentList.add(new Fragment_project());
        mFragmentList.add(new Fragment_articles());
        mFragmentList.add(new Fragment_dynamic());
        mFragmentList.add(new Fragment_mine());


        group= (RadioGroup) findViewById(R.id.radiogroup);
        radioButton1= (RadioButton) findViewById(R.id.first);
        radioButton2= (RadioButton) findViewById(R.id.second);
        radioButton3= (RadioButton) findViewById(R.id.thrid);
        radioButton4= (RadioButton) findViewById(R.id.fourth);
        group= (RadioGroup) findViewById(R.id.radiogroup);
        group.setOnCheckedChangeListener(this);

        madpter=new MyFragmentPagerAdapter(getSupportFragmentManager(),mFragmentList);
        vpager= (ViewPager) findViewById(R.id.viewpager);

        vpager.setAdapter(madpter);              //设置一个PagerAdapter
        vpager.setCurrentItem(0);                //设置当前选中的页面  与上面定义整形变量相对应
        vpager.addOnPageChangeListener(this);    //传入一个ViewPager.SimpleOnPageChangeListener对象
        vpager.setOffscreenPageLimit(4);         //页面数
    }


    @Override
    public void onCheckedChanged(RadioGroup group,  int checkedId) {
        switch (checkedId){
            case  R.id.first:
                vpager.setCurrentItem(PAGE_ONE);
                break;
            case  R.id.second:
                vpager.setCurrentItem(PAGE_TWO);
                break;
            case  R.id.thrid:
                vpager.setCurrentItem(PAGE_THREE);
                break;
            case  R.id.fourth:
                vpager.setCurrentItem(PAGE_FOUR);
                break;
        }


    }



    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }


 @Override
    public void onPageScrollStateChanged(int state) {
        if(state==2){                            //state的过程一共三个  0:开始滑动  1:正在滑动   2:滑动完毕
            switch (vpager.getCurrentItem())
            {
                case PAGE_ONE:
                    radioButton1.setChecked(true);
                    break;
                case PAGE_TWO:
                    radioButton2.setChecked(true);
                    break;
                case PAGE_THREE:
                    radioButton3.setChecked(true);
                    break;
                case PAGE_FOUR:
                    radioButton4.setChecked(true);
                    break;
            }
        }

    }



}
