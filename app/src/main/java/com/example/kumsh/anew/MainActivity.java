package com.example.kumsh.anew;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button login_main,signup_main;
    private EditText mobilelog,passwordlog;
    private DbHelper db;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db=new DbHelper(this);
        session=new Session(this);
        login_main=(Button)findViewById(R.id.login_main);
        signup_main=(Button)findViewById(R.id.signup_main);
        mobilelog=(EditText)findViewById(R.id.mobilelog);
        passwordlog=(EditText)findViewById(R.id.passwordlog);
        onlogin_mainClick();
        onsignup_mainClick();

        if(session.loggedIn())
        {
            Intent alwayslog=new Intent(MainActivity.this,MapsActivity.class);
            startActivity(alwayslog);
            finish();
        }
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.login_main:
                onlogin_mainClick();
                break;
            case R.id.signup_main:
                onsignup_mainClick();
                break;
            default:
        }
    }

    private void onlogin_mainClick() {
        login_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    String mobile=mobilelog.getText().toString();
                    String password=passwordlog.getText().toString();
                    if(TextUtils.isEmpty(mobile))
                    {
                        Toast.makeText(MainActivity.this,"Please enter mobile number!!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(TextUtils.isEmpty(password))
                    {
                        Toast.makeText(MainActivity.this,"Please enter password!!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (db.getUser(mobile,password))
                    {
                        session.setLoggedIn(true);
                        Intent mapping=new Intent(MainActivity.this,MapsActivity.class);
                        startActivity(mapping);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this,"Try again!!! Mobile no. and Password doesn't match",Toast.LENGTH_SHORT).show();
                    }
            }
        });
    }

    private void onsignup_mainClick() {
        signup_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sign=new Intent(MainActivity.this,Signup_activity.class);
                startActivity(sign);
            }
        });
    }
}
