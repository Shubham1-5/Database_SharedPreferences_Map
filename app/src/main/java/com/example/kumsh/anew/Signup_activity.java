package com.example.kumsh.anew;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup_activity extends AppCompatActivity implements View.OnClickListener{
    private Button signup_sign,login_sign;
    private EditText namesign,numbersign,passwordsign,emailsign;
    private CheckBox checkBox;
    private DbHelper db;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_activity);
        db=new DbHelper(this);
        progressDialog=new ProgressDialog(this);
        firebaseAuth=FirebaseAuth.getInstance();
        namesign=(EditText)findViewById(R.id.namesign);
        numbersign=(EditText)findViewById(R.id.numbersign);
        passwordsign=(EditText)findViewById(R.id.passwordsign);
        signup_sign=(Button)findViewById(R.id.signup_sign);
        login_sign=(Button)findViewById(R.id.login_sign);
        checkBox=(CheckBox)findViewById(R.id.checkBox);
        onsignup_signClick();
        onlogin_signClick();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.signup_sign:
                onsignup_signClick();
                break;
            case R.id.login_sign:
                onlogin_signClick();
                finish();
                break;
            default:
        }
    }

    private void onlogin_signClick() {
        login_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent log=new Intent(Signup_activity.this,MainActivity.class);
                startActivity(log);
            }
        });
    }

    private void onsignup_signClick() {
        signup_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=namesign.getText().toString();
                String number=numbersign.getText().toString();
                String password=passwordsign.getText().toString();
                if(TextUtils.isEmpty(name))
                {
                    Toast toast=Toast.makeText(Signup_activity.this,"Enter your name",Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                    return;
                }
                else if(TextUtils.isEmpty(number))
                {
                    Toast toast=Toast.makeText(Signup_activity.this,"Enter your number",Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                    return;
                }
                else if(TextUtils.isEmpty(password))
                {
                    Toast toast=Toast.makeText(Signup_activity.this,"Create a password",Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                    return;
                }
                else if(checkBox.isChecked()==false)
                {
                    Toast toast=Toast.makeText(Signup_activity.this,"Please accept terms and conditions",Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                }
                else
                {
                    if(numbersign.getText().toString().startsWith("7")||numbersign.getText().toString().startsWith("8")||numbersign.getText().toString().startsWith("9"))
                    {
                        if(numbersign.length()==10)
                        {
                            if(namesign.length()>2)
                            {
                                if(passwordsign.length()>3)
                                {
                                    db.addUser(name,number,password);
                                    Toast.makeText(Signup_activity.this,"User registered!!",Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                                else
                                {
                                    Toast.makeText(Signup_activity.this,"Password should be of more than 5 characters", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else
                            {
                                Toast.makeText(Signup_activity.this,"Enter a valid name",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(Signup_activity.this,"Enter 10 digit number",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        numbersign.setError("Number should start from either 7 or 8 or 9");
                    }
                }
            }
        });
    }
}
