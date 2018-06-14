package com.example.android.musicalstructureapp;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;

import java.util.ArrayList;
import java.util.List;

// It saves information about the song list.
public class songUnitCollection {
    public static songUnitCollection instance = null;
    public Context context;

    ArrayList<songUnit> songList = new ArrayList<songUnit>();

    public static songUnitCollection getInstance() {
        if (instance == null) {
            instance = new songUnitCollection();
        }
        return (instance);
    }

    public void addSong(songUnit song) {
        songList.add(song);
    }

    public songUnit getSongUnit(String _songTitle) {
        for (int i = 0; i < songList.size(); i++) {
            if (songList.get(i).songTitle.compareTo(_songTitle) == 0) {
                return songList.get(i);
            }
        }
        return null;
    }

    public int getSongIndex(String _songTitle) {
        for (int i = 0; i < songList.size(); i++) {
            if (songList.get(i).songTitle.compareTo(_songTitle) == 0) {
                return i;
            }
        }
        return 0;
    }

    // get album drawable id using the song title.
    public int getAlbumDrawableID(String _songTitle) {
        int arrayIndex = 0;
        for (int i = 0; i < songList.size(); i++) {
            if (songList.get(i).songTitle.compareTo(_songTitle) == 0) {
                arrayIndex = i;
            }
        }
        switch (arrayIndex) {
            case 0:
                return R.drawable.onekiss_album_image;
            case 1:
                return R.drawable.godplan_album_image;
            case 2:
                return R.drawable.niceforwhat_album_image;
            case 3:
                return R.drawable.havana_album_image;
            case 4:
                return R.drawable.psycho_album_image;
            case 5:
                return R.drawable.inmyblood_album_image;
            case 6:
                return R.drawable.ilikeit_album_image;
            case 7:
                return R.drawable.themiddle_album_image;
            case 8:
                return R.drawable.flames_album_image;
            case 9:
                return R.drawable.friends_album_image;
            default:
                return R.drawable.onekiss_album_image;
        }
    }

}
