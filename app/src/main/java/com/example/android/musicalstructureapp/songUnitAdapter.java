package com.example.android.musicalstructureapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class songUnitAdapter extends ArrayAdapter<songUnit> {

    ImageView favoriteImageView;
    boolean isShowFavorite;

    public songUnitAdapter(Activity context, ArrayList<songUnit> songs) {
        super(context, 0, songs);
    }

    @Override
    public View getView(int position, View listItemView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.song_list_item, parent, false);
        }
        // set the background color per each item's position
        if (position % 2 == 0) {// item at the even position
            listItemView.setBackgroundResource(R.color.mainBGColorBrown);
        } else { // item at the odd position
            listItemView.setBackgroundResource(R.color.mainBGColorSkyblue);
        }
        // get the songUnit object located at this position in the list
        final songUnit currentSong = getItem(position);
        // Find the ImageView in the song_list_item.xml layout with the ID song_album_thumnail
        ImageView thumbnailView = (ImageView) listItemView.findViewById(R.id.song_album_thumnail);
        // set the thumbnail image
        int thumbnailID = songUnitCollection.getInstance().getAlbumDrawableID(currentSong.getSongTitle());
        thumbnailView.setImageResource(thumbnailID);
        // Find the TextView in the song_list_item.xml layout with the ID songTitle
        TextView songTitleTextView = (TextView) listItemView.findViewById(R.id.songTitle);
        // set this text on the name TextView
        songTitleTextView.setText(currentSong.getSongTitle());
        // Find the TextView in the song_list_item.xml layout with the ID artistName
        TextView artistNameTextView = (TextView) listItemView.findViewById(R.id.artistName);
        // set this text on the artist name TextView
        artistNameTextView.setText(currentSong.getArtistName());
        // set the favorite image based on the saved favorite state from the current song: if isShowFavorite == true, shows the favorite image
        if (this.isShowFavorite) {
            final int songIndex = songUnitCollection.getInstance().getSongIndex(currentSong.getSongTitle()); // index for songList
            final boolean isFavorite = currentSong.getIsFavorite();
            favoriteImageView = (ImageView) listItemView.findViewById(R.id.song_favorite);
            // Combined Tag: tenth unit stands for the song index and one unit stans for the favorite status.
            // For example,
            int combinedTag = songIndex * 10;
            if (isFavorite == true) {
                favoriteImageView.setImageResource(R.drawable.favorite_chosen);
                combinedTag = combinedTag + 1;
            } else {
                favoriteImageView.setImageResource(R.drawable.favorite_not_chosen);
                combinedTag = combinedTag + 2;
            }
            favoriteImageView.setTag(combinedTag);
            currentSong.favoriteIV = favoriteImageView; // assign the favorite imageView to the song list collection
            favoriteImageView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    int _tag = (Integer) v.getTag();
                    int _tag_song = _tag / 10;
                    int _tag_state = _tag - _tag_song * 10;
                    int newTag = 10 * _tag_song;
                    songUnit _song = songUnitCollection.getInstance().songList.get(_tag_song);
                    if (_song.getIsFavorite()) {
                        _song.favoriteIV.setImageResource(R.drawable.favorite_not_chosen);
                        songUnitCollection.getInstance().songList.get(_tag_song).isFavorite = false;
                        newTag = newTag + 2;
                    } else {
                        _song.favoriteIV.setImageResource(R.drawable.favorite_chosen);
                        songUnitCollection.getInstance().songList.get(songIndex).isFavorite = true;
                        newTag = newTag + 1;
                    }
                    _song.favoriteIV.setTag(newTag);

                }
            });
        }
        // Return the whole list item layout (containing 2 TextViews and an 2 ImageViews)
        // so that it can be shown in the ListView of each activity layout
        return listItemView;
    }

    public void setShowHideFavoriteIV(boolean show) {
        this.isShowFavorite = show;
    }
}