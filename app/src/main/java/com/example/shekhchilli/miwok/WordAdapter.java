package com.example.shekhchilli.miwok;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.media.MediaPlayer;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by shekh chilli on 9/14/2016.
 */
public class WordAdapter extends ArrayAdapter<Word> {
    private int mcolorResourceid;

    public WordAdapter(Activity context, ArrayList<Word> words, int colorResourceid){
        super(context,0,words);
        mcolorResourceid = colorResourceid;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Word currentword = getItem(position);

        if(convertView == null){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        TextView miwokTextview = (TextView) convertView.findViewById(R.id.miwoktranslation_textview);
        miwokTextview.setText(currentword.getMiwokktranslation());

        TextView defaultTextview = (TextView) convertView.findViewById(R.id.defaulttranslation_textview);
        defaultTextview.setText(currentword.getDefaulttranstion());

        if(currentword.hasImage()) {
            ImageView numberImageview = (ImageView) convertView.findViewById(R.id.number_imageview);
            numberImageview.setImageResource(currentword.getImage());
        }
        else{
            ImageView imageview = (ImageView) convertView.findViewById(R.id.number_imageview);
            imageview.setVisibility(View.GONE);
        }

        View textContainer = (View) convertView.findViewById(R.id.text_container);
        int color = ContextCompat.getColor(getContext(),mcolorResourceid);
        textContainer.setBackgroundColor(color);

        return convertView;
    }
}
