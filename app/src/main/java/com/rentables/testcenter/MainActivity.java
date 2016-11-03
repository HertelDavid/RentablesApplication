package com.rentables.testcenter;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;

import java.net.URI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.rentables_toolbar);
        setSupportActionBar(toolbar);

        //Adding onKeyListener for password EditText
        onKeyListenerForPassword();

        resetPasswordTypeface();

        EditText userName = (EditText) findViewById(R.id.username_edit_text);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.overflow_menu, menu);

        return true;
    }

    public void userLogin(View view){

        EditText userName = (EditText) findViewById(R.id.username_edit_text);
        EditText password = (EditText) findViewById(R.id.password_edit_text);

        if(userName.getText().toString().trim().equals("")){

            userName.setError("Username Required");
        }

        if(password.getText().toString().trim().equals("")){

            password.setError("Password Required");
        }

        /*
        Intent intent = new Intent();
        intent.setClass(this, com.rentables.testcenter.HomeActivity.class);
        startActivity(intent);*/
    }

    public void forgotPassword(View view){

        FragmentManager fm = getSupportFragmentManager();
        ForgotPasswordDialog forgotPass = new ForgotPasswordDialog();
        forgotPass.show(fm, "forgot_password");
    }


    public void startRegisterUserActivity(View view){

        Intent registerIntent = new Intent(this, RegisterUserActivity.class);
        startActivity(registerIntent);

    }

    public void resetPasswordTypeface(){

        EditText password = (EditText) findViewById(R.id.password_edit_text);
        password.setTypeface(Typeface.DEFAULT);
        password.setTransformationMethod(new PasswordTransformationMethod());
    }

    public void onKeyListenerForPassword(){

        final EditText passText = (EditText) findViewById(R.id.password_edit_text);

        passText.setOnKeyListener(new OnKeyListener(){

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event){

                if(event.getKeyCode() == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP){

                    userLogin(passText);
                    return true;
                }

                return false;
            }
        });
    }
}
