package com.rentables.testcenter;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

public class RegisterUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_register_user);

        resetPasswordTypeface();
        setKeyListeners();

        //Set Focus to Email Edit Text
        setFocusOfEditText(R.id.register_username);
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
