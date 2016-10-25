package com.rentables.testcenter;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resetPasswordTypeface();
    }

    public void userLogin(View view){

        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void forgotPassword(View view){

        ForgotPassDialog passDialog = new ForgotPassDialog();
    }

    public void registerUser(View view){

        System.out.println("Hello");
    }

    public void resetPasswordTypeface(){

        EditText password = (EditText) findViewById(R.id.password_edit_text);
        password.setTypeface(Typeface.DEFAULT);
        password.setTransformationMethod(new PasswordTransformationMethod());
    }
}
