package com.example.utkarshsingh.todo;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Utkarsh Singh on 09-11-2016.
 */
public class ViewAdapter extends PagerAdapter{
    Context cont;
    public LayoutInflater layoutInflater;
    public View view;
    TextView t1;
    TextView t2;
    public ViewAdapter(Context context)
    {
        this.cont=context;
    }

    @Override
    public int getCount() {
        return MainActivity.list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object)
    {
        return view==(RelativeLayout)object;
    }



    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
       container.removeView((RelativeLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater)cont.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=layoutInflater.inflate(R.layout.fragact,container,false);
        t1=(TextView)view.findViewById(R.id.t1);
        t2=(TextView)view.findViewById(R.id.t2);
        t1.setText(MainActivity.list.get(position).getTitle());
        t2.setText(MainActivity.list.get(position).getDetail());
        container.addView(view);
        return view;
    }
}
