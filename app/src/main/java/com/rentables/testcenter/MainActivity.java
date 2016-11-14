package com.rentables.testcenter;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;

import dataobjects.User;

public class MainActivity extends AppCompatActivity implements ThreadListener{

    User firstUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Rentables");

        //Adding onKeyListener for password EditText
        onKeyListenerForPassword();

        //Resetting weird password typeface
        resetPasswordTypeface();

        testServerConnection();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.overflow_menu, menu);

        return true;
    }

    @Override
    public void notifyOfThreadCompletion(final NotifyingThread notifyingThread){

        firstUser.printProperties();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch(item.getItemId()){

            case R.id.overflow_settings_option:
                System.out.println("Reached");
                return true;
            case R.id.overflow_account_option:
                System.out.println("Reached");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void testServerConnection(){

        firstUser = new User(32);
        ThreadGetUser get = new ThreadGetUser(firstUser);
        get.addListener(this);

        //Starting the thread.
        new Thread(get).start();

    }

    public void userLogin(View view){

        EditText userName = (EditText) findViewById(R.id.username_edit_text);
        EditText password = (EditText) findViewById(R.id.password_edit_text);
        boolean complete = true;

        if(userName.getText().toString().trim().equals("")){

            userName.setError("Username Required");
            complete = false;
        }

        if(password.getText().toString().trim().equals("")){

            password.setError("Password Required");
            complete = false;
        }

        if(complete){

            Intent loginIntent = new Intent();
            loginIntent.setClass(this, HomeActivity.class);
            startActivity(loginIntent);
        }
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
