package com.salma.searchlocalandremoterx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
Button localSearchBtn;
Button remoteSearchBtn;
boolean remoteSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        localSearchBtn=findViewById(R.id.localBtn);
        remoteSearchBtn=findViewById(R.id.remoteBtn);
        localSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remoteSearch=false;
                Intent intent = new Intent(MainActivity.this,SearchActivity.class);
                intent.putExtra("searchType",remoteSearch);
                startActivity(intent);

            }
        });
        remoteSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remoteSearch=true;
                Intent intent = new Intent(MainActivity.this,SearchActivity.class);
                intent.putExtra("searchType",remoteSearch);
                startActivity(intent);
            }
        });
    }
}
