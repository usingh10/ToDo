package com.example.utkarshsingh.todo;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewParent;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Adapter adapter;
    public static int f = 0;
    RecyclerView.LayoutManager layoutmanager;
    public static ArrayList<Row> list= new ArrayList<Row>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Row row1 = new Row("a","b");
        list.add(row1);
        Row row12 = new Row("ab","b");
        list.add(row12);*/

        SharedPreferences prefs = getSharedPreferences("Shared", MODE_PRIVATE);
        String s = prefs.getString("s", null);
        if (s != null && f == 0) {
            for(int i=0;i<Integer.parseInt(s);i++)
            {
                String name = prefs.getString(""+i, "No name defined");
                String[] ss = name.split("-");
                Row r = new Row(ss[0],ss[1]);
                list.add(r);
            }
            //"No name defined" is the default value.
            f=1;//0 is the default value.
        }

        recyclerView=(RecyclerView)findViewById(R.id.dishRecyclerView);
        adapter = new Adapter(list);
        recyclerView.setHasFixedSize(true);
        layoutmanager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutmanager);
        recyclerView.setAdapter(adapter);
        swipe();
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        recyclerView=(RecyclerView)findViewById(R.id.dishRecyclerView);
        adapter = new Adapter(list);
        recyclerView.setHasFixedSize(true);
        layoutmanager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutmanager);
        recyclerView.setAdapter(adapter);
    }


    public void swipe()
    {
        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT)
        {
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                //TODO: Not implemented here
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                //Remove item
                list.remove(viewHolder.getAdapterPosition());
                recyclerView=(RecyclerView)findViewById(R.id.dishRecyclerView);
                adapter = new Adapter(list);
                recyclerView.setHasFixedSize(true);
                layoutmanager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(layoutmanager);
                recyclerView.setAdapter(adapter);
            }
        };
        ItemTouchHelper ss=new ItemTouchHelper(callback);
        ss.attachToRecyclerView(recyclerView);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_task:
                Intent i = new Intent(this,AddActivity.class);
                startActivityForResult(i, 1);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    public void update(View v)
    {
        /*Intent intent = new Intent(MainActivity.this, ViewPagerActivity.class);
        startActivity(intent);*/
        LayoutInflater factory = LayoutInflater.from(this);

        final View textEntryView = factory.inflate(R.layout.dialogbox, null);
        ViewParent a=v.getParent();
        ViewParent ay=a.getParent();
        //text_entry is an Layout XML file containing two text field to display in alert dialog
        final int pos=recyclerView.getChildAdapterPosition((View) ay);
        String s1=list.get(pos).getTitle();
        String s2=list.get(pos).getDetail();
        Log.d("h", "update: ");
        final EditText input1 = (EditText) textEntryView.findViewById(R.id.item_name);
        final EditText input2 = (EditText) textEntryView.findViewById(R.id.item_detail);
        input1.setText(s1, TextView.BufferType.EDITABLE);
        input2.setText(s2, TextView.BufferType.EDITABLE);

        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(
                "Enter the Text:").setView(
                textEntryView).setPositiveButton("Save",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int whichButton) {

                        Log.i("AlertDialog", "TextEntry 1 Entered " + input1.getText().toString());
                        Log.i("AlertDialog", "TextEntry 2 Entered " + input2.getText().toString());
                        list.remove(pos);
                        Row x=new Row(input1.getText().toString(),input2.getText().toString());
                        list.add(x);
                        recyclerView=(RecyclerView)findViewById(R.id.dishRecyclerView);
                        adapter = new Adapter(list);
                        recyclerView.setHasFixedSize(true);
                        layoutmanager = new LinearLayoutManager(getApplicationContext());
                        recyclerView.setLayoutManager(layoutmanager);
                        recyclerView.setAdapter(adapter);
                        /*recyclerView=(RecyclerView)findViewById(R.id.dishRecyclerView);
                        adapter = new Adapter(list);
                        recyclerView.setHasFixedSize(true);
                        layoutmanager = new LinearLayoutManager(getApplicationContext());
                        recyclerView.setLayoutManager(layoutmanager);
                        recyclerView.setAdapter(adapter);
        /* User clicked OK so do some stuff */
                    }
                }).setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int whichButton) {
         /*
         * User clicked cancel so do some stuff
         */
                    }
                });
        alert.show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences.Editor editor = getSharedPreferences("Shared", MODE_PRIVATE).edit();
        editor.putString("s",""+list.size());
        for (int i=0;i<list.size();i++)
        {
            editor.putString(""+i,list.get(i).getTitle()+"-"+list.get(i).getDetail());
        }
        editor.commit();
    }
}
