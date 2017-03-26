package com.example.victor.ccflouncher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showApps(View view) {

        Intent intent = new Intent(this,AppsListActivity.class);
        startActivity(intent);

    }
}
