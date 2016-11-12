package com.rentables.testcenter;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.View.OnKeyListener;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class RegisterUserActivity extends AppCompatActivity {

    private boolean passwordsMatch = false;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        System.out.println("Reached");

        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_register_user);

        //Setting up toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.rentables_toolbar);
        setSupportActionBar(toolbar);

        //Resetting password typeface for both password EditTexts.
        resetPasswordTypeface();

        //Adding listeners to the confirm password EditText
        passwordChangeTextListener();
        confirmPasswordListener();
        confirmPasswordChangeTextListener();

        //Set Focus to Email Edit Text
        setFocusOfEditText(R.id.register_username);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.overflow_menu, menu);
        return true;
    }

    public void registerUser(View view){

        /*
        Before user can register make sure that the two passwords match. Also, make sure that
        all fields in the form are filled in completely.
         */

        if(!isFormCompleted() || !passwordsMatch) {

            //Checking to see if forms have been filled out completely and
            //that the passwords match correctly.
            return;
        }else{

            System.out.println("Hello");
        }
    }

    public void resetPasswordTypeface(){

        EditText password = (EditText) findViewById(R.id.register_password);
        EditText confirmPassword = (EditText) findViewById(R.id.register_password_confirm);

        password.setTypeface(Typeface.DEFAULT);
        confirmPassword.setTypeface(Typeface.DEFAULT);

        password.setTransformationMethod(new PasswordTransformationMethod());
        confirmPassword.setTransformationMethod((new PasswordTransformationMethod()));
    }

    public void setFocusOfEditText(int viewId){

        EditText viewToFocus = (EditText) findViewById(viewId);
        viewToFocus.requestFocus();
    }

    public void confirmPasswordListener(){

        //A listener to check to see if the fields in the form are completely filled out.

        final EditText confirmPassText = (EditText) findViewById(R.id.register_password_confirm);

        confirmPassText.setOnKeyListener(new OnKeyListener(){

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if(event.getKeyCode() == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){

                   registerUser(confirmPassText);
                }

                return false;
            }
        });
    }

    public void passwordChangeTextListener(){

        final EditText passText = (EditText) findViewById(R.id.register_password);

        passText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                confirmPassword();
            }
        });
    }

    public void confirmPasswordChangeTextListener(){

        //On text changed listener for the register_password_confirm EditText.
        //Used to confirm that both password EditTexts match.

        final EditText confirmPassText = (EditText) findViewById(R.id.register_password_confirm);

        confirmPassText.addTextChangedListener(new TextWatcher(){

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                confirmPassword();
            }
        });
    }

    public void confirmPassword(){

        EditText passwordEditText = (EditText) findViewById(R.id.register_password);
        EditText confirmPasswordEditText = (EditText) findViewById(R.id.register_password_confirm);

        String password = passwordEditText.getText().toString();
        String confirmPassword = confirmPasswordEditText.getText().toString();

        if(confirmPassword.equals(password)){

            passwordsMatch = true;
            confirmPasswordEditText.setError(null);

        }else{

            passwordsMatch = false;
            confirmPasswordEditText.setError("Passwords must match!");

        }
    }

    public boolean isFormCompleted(){

        EditText username = (EditText) findViewById(R.id.register_username);
        EditText firstName = (EditText) findViewById(R.id.register_firstname);
        EditText lastName = (EditText) findViewById(R.id.register_lastname);
        EditText password = (EditText) findViewById(R.id.register_password);
        EditText confirmPassword = (EditText) findViewById(R.id.register_password_confirm);

        boolean formCompleted = true;

        if(username.getText().toString().trim().equals("")){

            formCompleted = false;
            username.setError("Username Required");
        }

        if(firstName.getText().toString().trim().equals("")){

            formCompleted = false;
            firstName.setError("First Name Required");
        }

        if(lastName.getText().toString().trim().equals("")){

            formCompleted = false;
            lastName.setError("Last Name Required");
        }

        if(password.getText().toString().trim().equals("")){

            formCompleted = false;
            password.setError("Password Required");
        }

        if(confirmPassword.getText().toString().trim().equals("")){

            formCompleted = false;
        }

        return formCompleted;
    }
}
