package com.example.utkarshsingh.todo;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class ViewPagerActivity extends AppCompatActivity {

    ViewPager viewpager;
    ViewAdapter viewadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager2);
        int pos=getIntent().getIntExtra("pos",0);
        viewpager = (ViewPager)findViewById(R.id.viewpager);
        viewadapter = new ViewAdapter(this);
        viewpager.setAdapter(viewadapter);
        viewpager.setCurrentItem(pos);
    }
}
