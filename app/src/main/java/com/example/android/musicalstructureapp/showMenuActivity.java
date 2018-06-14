package com.example.android.musicalstructureapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.Timer;

public class showMenuActivity extends AppCompatActivity {

    // set two menu imageview objects
    private ImageView mMyFavoriteMenuIV;
    private ImageView mSongListMenuIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_menu);
        // set the touch event listener for two menu buttons
        setTouchEvent4Menu();
    }

    public void setTouchEvent4Menu() {
        mMyFavoriteMenuIV = findViewById(R.id.myFavoriteImageView);
        mMyFavoriteMenuIV.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            public void onClick(View v) {
                callMyFavoriteActivity();
            }
        });
        // set the touch event listener to the view result icon
        mSongListMenuIV = findViewById(R.id.songListImageView);
        mSongListMenuIV.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            public void onClick(View v) {
                callSongListActivity();
            }
        });

    }

    // When clicking the go home icon button, call the main activity
    private void callMainActivity() {
        // call myFavorite activity
        Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(mainIntent);
    }

    // When clicking the myFavorite menu button, call the myFavorite activity
    private void callMyFavoriteActivity() {
        // call myFavorite activity
        Intent myFavoriteIntent = new Intent(getApplicationContext(), myFavoriteActivity.class);
        startActivity(myFavoriteIntent);
    }

    // When clicking the song list icon, this function is called.
    private void callSongListActivity() {
        // call song list activity
        Intent callSongListIntent = new Intent(getApplicationContext(), songListActivity.class);
        startActivity(callSongListIntent);
    }
}