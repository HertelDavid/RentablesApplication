package com.rentables.testcenter;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class RegisterUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_register_user);

        //Setting up toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.rentables_toolbar);
        setSupportActionBar(toolbar);

        resetPasswordTypeface();
        setKeyListeners();

        //Set Focus to Email Edit Text
        setFocusOfEditText(R.id.register_username);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.overflow_menu, menu);
        return true;
    }

    public void registerUser(View view){

        System.out.println(view);
    }

    public void resetPasswordTypeface(){

        EditText password = (EditText) findViewById(R.id.register_password);
        password.setTypeface(Typeface.DEFAULT);
        password.setTransformationMethod(new PasswordTransformationMethod());
    }

    public void setKeyListeners(){


    }

    public void setFocusOfEditText(int viewId){

        EditText viewToFocus = (EditText) findViewById(viewId);
        viewToFocus.requestFocus();
    }

    public void setTextOfView(int viewId, String text){

        System.out.println("Reached");
        EditText viewToChange = (EditText) findViewById(viewId);
        viewToChange.setText("");
    }
}
