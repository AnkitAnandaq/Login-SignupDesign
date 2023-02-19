package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends AppCompatActivity {
EditText n,e,m,p;
Button signup;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        n=findViewById(R.id.username);
        e=findViewById(R.id.email);
        m=findViewById(R.id.mobile);
        p=findViewById(R.id.password);
        signup=findViewById(R.id.Signupbutton);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    SQLiteDatabase conn=openOrCreateDatabase("project",MODE_PRIVATE,null);
                    conn.execSQL("create table  if not exists  stu(name varchar,password varchar,mobile varchar,email varchar);");
                    String name=n.getText().toString();
                    String email=e.getText().toString();
                    String mobile=m.getText().toString();
                    String pass=p.getText().toString();
                    conn.execSQL("insert into stu values("+"'"+name+"'"+","+"'"+pass+"'"+","+"'"+mobile+"'"+","+"'"+email+"'"+");");
                    Intent i=new Intent(Registration.this,login.class);
                    Toast.makeText(getApplicationContext(),"You are register!!!",Toast.LENGTH_LONG).show();
                    startActivity(i);
                    finish();
                }
                catch(Exception obj)
                {
                    Toast.makeText(getApplicationContext(),"Insertion failed:"+obj,Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}