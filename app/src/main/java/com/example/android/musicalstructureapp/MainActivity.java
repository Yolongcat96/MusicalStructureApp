package com.example.android.musicalstructureapp;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set main content view
        setContentView(R.layout.activity_main);
        // adjust size
        adjustSizes();
        // receive the song list from the file
        getSongDataFromJsonFile();
        // set click event on the START button
        setClickEvent4START();
    }

    // when click START button, it moves the user to the menu (category) screen
    public void setClickEvent4START() {
        final Button startButton = (Button) findViewById(R.id.startButton);
        // call menu screen activity
        startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent menuIntent = new Intent(getApplicationContext(), showMenuActivity.class);
                startActivity(menuIntent);
            }
        });

    }

    private void adjustSizes() {
        // Gets linearlayout
        LinearLayout layout = findViewById(R.id.bottomLayout);
        // Gets the layout params that will allow you to resize the layout
        // Changes the height and width to the specified *pixels
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int height = size.y;
        layout.getLayoutParams().height = (int) (height*0.43);
    }

    //-------------------------------------------------------------------
    // Parse Json file from Assets and save them on the singleton class
    //-------------------------------------------------------------------
    public void getSongDataFromJsonFile() {
        // read json string to get the each song information
        String jsonString = loadJSONFromAsset("songList.json");
        // save the song data to the songUnitCollection singleton
        saveSongList(jsonString);
    }

    public void saveSongList(String jsonString) {
        try {
            JSONObject obj = new JSONObject(jsonString);
            JSONArray proArray = obj.getJSONArray("List");
            for (int i = 0; i < proArray.length(); i++) {
                // receive the variables from the json file
                JSONObject object = proArray.getJSONObject(i);
                int mSongIndex = object.getInt("songIndex");
                String mSongTitle = object.getString("songTitle");
                String mArtistName = object.getString("artistName");
                String mSongAlbumImageName = object.getString("songAlbumImageFileName");
                Boolean mIsFavorite = object.getBoolean("isFavorite");
                // create the song unit
                songUnit sUnit = new songUnit();
                sUnit.createSongUnit(mSongIndex, mSongTitle, mArtistName, mSongAlbumImageName, mIsFavorite, null); // favoriteImageView will be added after adding the song list to the listView
                // save song unit information at the songUnitCollection (singleton)
                songUnitCollection.getInstance().addSong(sUnit);
            }

        } catch (Throwable t) {
            Log.e("Music App", "Could not parse malformed JSON: \"" + jsonString + "\"");
        }

    }

    private String loadJSONFromAsset(String fileName) {
        String json = null;
        try {
            Log.d("Music App", "Load Json file from Assets: " + fileName);
            InputStream is = getAssets().open(fileName);
            StringBuilder buf = new StringBuilder();
            BufferedReader in =
                    new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String str;
            while ((str = in.readLine()) != null) {
                buf.append(str);
            }
            in.close();
            json = buf.toString();

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}