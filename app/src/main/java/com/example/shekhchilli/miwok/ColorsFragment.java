package com.example.shekhchilli.miwok;


import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ColorsFragment extends Fragment {

    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;

    private AudioManager.OnAudioFocusChangeListener FocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT){
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            }
            else if(focusChange == AudioManager.AUDIOFOCUS_LOSS){
                mediaPlayer.stop();
                releaseMediaPlayer();
            }
            else if(focusChange == AudioManager.AUDIOFOCUS_GAIN_TRANSIENT){
                mediaPlayer.start();
            }
        }
    };


    private MediaPlayer.OnCompletionListener CompletioListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    private void releaseMediaPlayer() {
        if(mediaPlayer!=null){
            mediaPlayer.release();
            mediaPlayer = null;

            audioManager.abandonAudioFocus(FocusChangeListener);
        }
    }


    public ColorsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View rootview = inflater.inflate(R.layout.word_list, container, false);

        audioManager = (AudioManager)getActivity().getSystemService(getContext().AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("black","lutti",R.drawable.color_black, R.raw.color_black ));
        words.add(new Word("brown","otiiko",R.drawable.color_brown, R.raw.color_brown));
        words.add(new Word("dusty yellw","toolokosu",R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        words.add(new Word("gray","oyyissa",R.drawable.color_gray, R.raw.color_gray));
        words.add(new Word("green","masookka",R.drawable.color_green, R.raw.color_green));
        words.add(new Word("mustard yellow","temokka",R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));
        words.add(new Word("red","kenekaku",R.drawable.color_red, R.raw.color_red));
        words.add(new Word("white","kawinta",R.drawable.color_white, R.raw.color_white));

        WordAdapter wordAdapter = new WordAdapter(getActivity(), words,R.color.catagoryColor);

        ListView list = (ListView) rootview.findViewById(R.id.list_view);

        list.setAdapter(wordAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = words.get(position);
                releaseMediaPlayer();
                int result = audioManager.requestAudioFocus(FocusChangeListener, AudioManager.USE_DEFAULT_STREAM_TYPE,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if(result== audioManager.AUDIOFOCUS_REQUEST_GRANTED)
                {
                    mediaPlayer = MediaPlayer.create(getActivity(), word.getSoundResourceid());
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(CompletioListener);
                }
            }
        });


        return rootview;
    }

}
