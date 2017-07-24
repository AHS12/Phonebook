package com.mdazizulhakim.phonebook;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class AboutDev extends AppCompatActivity {
    ActionBar actionBar;
    ImageButton btnfb,btnGoogle,btnGit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_dev);
        actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDefaultDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("About Developer");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ED9F23")));



        btnfb = (ImageButton) findViewById(R.id.btnFb);
        btnGoogle = (ImageButton) findViewById(R.id.btnGoogle);
        btnGit = (ImageButton) findViewById(R.id.btnGit);

        btnfb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/mdazizul.hakim.14"));
                startActivity(intent);
            }
        });

        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://plus.google.com/106475344288458932138"));
                startActivity(intent);
            }
        });

        btnGit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/AHS12"));
                startActivity(intent);
            }
        });
    }
    
    /////editing for update


}
