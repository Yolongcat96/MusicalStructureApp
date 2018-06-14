package com.example.android.musicalstructureapp;
// create the song unit

import android.widget.ImageView;

// It includes all information for individual song.
public class songUnit {

    public int songIndex;
    public String songTitle;
    public String artistName;

    public String songAlbumImageFile;
    public boolean isFavorite;
    public ImageView favoriteIV;

    public void createSongUnit(int _songIndex, String _songTitle, String _artistName, String _songAlbumImageFileName, boolean _isFavorite, ImageView _favoriteIV) {
        this.songIndex = _songIndex;
        this.songTitle = _songTitle;
        this.artistName = _artistName;
        this.songAlbumImageFile = _songAlbumImageFileName;
        this.isFavorite = _isFavorite;
        this.favoriteIV = _favoriteIV;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getImageFileID() {
        return songAlbumImageFile;
    }

    public boolean getIsFavorite() {
        return isFavorite;
    }

    public ImageView getFavoriteIV() {
        return favoriteIV;
    }
}