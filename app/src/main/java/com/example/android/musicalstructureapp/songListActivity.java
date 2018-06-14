package com.example.android.musicalstructureapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class songListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);
        // Create an {@link songUnitAdapter}, whose data source is a list of {@link song}s. The
        // adapter knows how to create list items for each item in the li
        final songUnitAdapter adapter = new songUnitAdapter(this, songUnitCollection.getInstance().songList);
        adapter.isShowFavorite = true; // show favorite image
        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list1, which is declared in the activity_song_list.xml
        ListView listView = (ListView) findViewById(R.id.list1);
        // Make the {@link ListView} use the {@link songUnitAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Song} in the list.
        listView.setAdapter(adapter);
        // When clicking the one item of the list, it calls the new activity (play music activity)
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                songUnit sUnit = (songUnit) adapter.getItem(position);
                Intent callPlayMusicIntent = new Intent(getApplicationContext(), playMusicActivity.class);
                callPlayMusicIntent.putExtra("caller_id", "song_list");
                callPlayMusicIntent.putExtra("song_title", sUnit.getSongTitle());
                callPlayMusicIntent.putExtra("artist_name", sUnit.getArtistName());
                startActivity(callPlayMusicIntent);
            }
        });
        // go back to menu
        setBackArrowFunction();
    }

    // When clicking the backarrow button, it moves the user to the menu activity.
    public void setBackArrowFunction() {
        final ImageView backArrowIV = (ImageView) findViewById(R.id.goback);
        backArrowIV.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent menuIntent = new Intent(getApplicationContext(), showMenuActivity.class);
                startActivity(menuIntent);
            }
        });
    }
}
