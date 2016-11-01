package com.rentables.testcenter;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ForgotPasswordDialog extends DialogFragment {

    public ForgotPasswordDialog(){
        //Empty constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.activity_forgot_pass, container);


        return view;
    }

}
