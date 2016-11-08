package com.rentables.testcenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstance){

        super.onCreate(savedInstance);
        setContentView(R.layout.activity_home);

        Toolbar toolbarMain = (Toolbar) findViewById(R.id.rentables_toolbar);
        setSupportActionBar(toolbarMain);

        Toolbar toolbarNavigate = (Toolbar) findViewById(R.id.navigate_toolbar);
        setSupportActionBar(toolbarNavigate);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.overflow_menu, menu);

        return true;
    }

    public void userLogin(View view){

        Intent intent = new Intent();
        intent.setClass(this, com.rentables.testcenter.NavigateActivity.class);
        startActivity(intent);
    }

}
