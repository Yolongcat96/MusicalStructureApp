package com.example.android.musicalstructureapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Point;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class playMusicActivity extends AppCompatActivity {

    private String current_song_title = "";
    private String current_artist_name = "";
    private int current_album_id;
    private String previous_activity_name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        // adjust size
        adjustSizes();
        // get intents and data from the previous activity
        getDataFromActivity();
        // set the display textviews
        setSongInfoToTextView();
        // set the diaply album image
        setSongAlbumImage();
        // set the play-related button
        setPlayButtonFunctions();
        // set the back arrow function
        setBackArrowFunction();
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
        layout.getLayoutParams().height = (int) (height*0.23);
    }

    private void getDataFromActivity() {
        Intent currIntent = getIntent();
        if (currIntent != null) {
            current_song_title = currIntent.getStringExtra("song_title");
            current_artist_name = currIntent.getStringExtra("artist_name");
            previous_activity_name = currIntent.getStringExtra("caller_id");
        }
    }

    private void setSongInfoToTextView() {
        // song_title
        TextView songTitleTV = (TextView) findViewById(R.id.songTitle);
        songTitleTV.setText(current_song_title);
        // artist_name
        TextView artistNameTV = (TextView) findViewById(R.id.artistName);
        artistNameTV.setText(current_artist_name);
    }

    private void setSongAlbumImage() {
        current_album_id = songUnitCollection.getInstance().getAlbumDrawableID(current_song_title);
        // song album image
        ImageView songAlbumIV = (ImageView) findViewById(R.id.albumImage);
        songAlbumIV.setImageResource(current_album_id);
    }

    public void setPlayButtonFunctions() {
        // play button & pause button
        final ImageView playbutton = findViewById(R.id.play_button);
        playbutton.setTag(1); // playbutton image
        playbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (playbutton.getTag().equals(1)) {
                    playbutton.setImageResource(R.drawable.pause_button_image);
                    playbutton.setTag(2);
                } else {
                    playbutton.setImageResource(R.drawable.play_button_image);
                    playbutton.setTag(1);
                }

            }
        });
        // play rewind: the function will be added
        final ImageView rewindButton = findViewById(R.id.rewind_button);
        rewindButton.setTag(1); // unclicked
        rewindButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            public void onClick(View v) {
                if (rewindButton.getTag().equals(1)) {
                    rewindButton.setImageAlpha(80);
                    rewindButton.setTag(2);
                } else {
                    rewindButton.setImageAlpha(255);
                    rewindButton.setTag(1);
                }
            }
        });
        // play rewind: the function will be added
        final ImageView fastForwardButton = findViewById(R.id.fast_button);
        fastForwardButton.setTag(1); // unclicked
        fastForwardButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            public void onClick(View v) {
                if (fastForwardButton.getTag().equals(1)) {
                    fastForwardButton.setImageAlpha(80);
                    fastForwardButton.setTag(2);
                } else {
                    fastForwardButton.setImageAlpha(255);
                    fastForwardButton.setTag(1);
                }
            }
        });

    }

    public void setBackArrowFunction() {
        final ImageView backArrowIV = (ImageView) findViewById(R.id.goback);
        backArrowIV.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (previous_activity_name.compareTo("song_list") == 0) {
                    callSongListActivity();
                } else {
                    callMyFavoriteActivity();
                }
            }
        });
    }

    private void callSongListActivity() {
        // call song list activity
        Intent callSongListIntent = new Intent(getApplicationContext(), songListActivity.class);
        startActivity(callSongListIntent);
    }

    private void callMyFavoriteActivity() {
        // call myFavorite activity
        Intent myFavoriteIntent = new Intent(getApplicationContext(), myFavoriteActivity.class);
        startActivity(myFavoriteIntent);
    }
}
