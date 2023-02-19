package com.example.splashscreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {
Button Login;
EditText user,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView signup=(TextView)findViewById(R.id.signup);
        Login = (Button)findViewById(R.id.loginButton);
        user=findViewById(R.id.username);
        pass=findViewById(R.id.password);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String u=user.getText().toString();
                String p=pass.getText().toString();
                try
                {
                    SQLiteDatabase conn=openOrCreateDatabase("project",MODE_PRIVATE,null);
                    Cursor c=conn.rawQuery("select * from stu where name="+"'"+u+"'"+" and password="+"'"+p+"'"+"",null);
                    if(c.moveToFirst()){
                        Toast.makeText(getApplicationContext(),"LOGIN SUCCESSFULLY",Toast.LENGTH_LONG).show();
                    }
                }
                catch (Exception obj){
                    Toast.makeText(getApplicationContext(),"Insertation Failed!!!"+obj,Toast.LENGTH_LONG).show();
                }
            }
        });
        String text="Not Register Yet?Sign UP";
        SpannableStringBuilder ssb=new SpannableStringBuilder(text);
//        ForegroundColorSpan fcsBlue=new ForegroundColorSpan(Color.BLUE);
//        ForegroundColorSpan Blue=new ForegroundColorSpan(Color.BLUE);
//        ssb.setSpan(fcsBlue,17,21, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        ssb.setSpan(Blue,22,24,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        signup.setText(ssb);
        ClickableSpan clickspan1=new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Intent i=new Intent(getApplicationContext(),Registration.class);
                startActivity(i);
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.BLUE);
            }
        };
        ssb.setSpan(clickspan1,17,24,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        signup.setText(ssb);
        signup.setMovementMethod(LinkMovementMethod.getInstance());
    }
}