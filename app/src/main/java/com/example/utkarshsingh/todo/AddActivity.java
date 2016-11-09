package com.example.utkarshsingh.todo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Button mButton = (Button)findViewById(R.id.button1);
        mButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                EditText mEdit = (EditText) findViewById(R.id.editText1);
                EditText edit = (EditText) findViewById(R.id.editText2);
                String itemText = mEdit.getText().toString();
                String itemText2 = edit.getText().toString();
                Row x=new Row(itemText,itemText2);
                MainActivity.list.add(x);
                Intent intent = new Intent();
                intent.putExtra("value", 1);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    /*public void click(View v)
    {
        EditText title = (EditText) findViewById(R.id.title);
        EditText detail = (EditText) findViewById(R.id.detail);
        if(!title.getText().toString().equals("") && !detail.getText().toString().equals(""))
        {
            Row x=new Row(title.getText().toString(),detail.getText().toString());
            MainActivity.list.add(x);
            Toast.makeText(getApplicationContext(), "Added !!", Toast.LENGTH_LONG).show();
            Button b=(Button)findViewById(R.id.b);
            b.setEnabled(false);
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Please fill both title and detail",Toast.LENGTH_LONG).show();
        }
    }*/
}
