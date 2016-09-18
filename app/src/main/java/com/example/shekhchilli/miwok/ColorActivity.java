package com.example.shekhchilli.miwok;

import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorActivity extends AppCompatActivity {
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        audioManager = (AudioManager)getSystemService(AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("black","lutti",R.drawable.color_black, R.raw.color_black ));
        words.add(new Word("brown","otiiko",R.drawable.color_brown, R.raw.color_brown));
        words.add(new Word("dusty yellw","toolokosu",R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        words.add(new Word("gray","oyyissa",R.drawable.color_gray, R.raw.color_gray));
        words.add(new Word("green","masookka",R.drawable.color_green, R.raw.color_green));
        words.add(new Word("mustard yellow","temokka",R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));
        words.add(new Word("red","kenekaku",R.drawable.color_red, R.raw.color_red));
        words.add(new Word("white","kawinta",R.drawable.color_white, R.raw.color_white));

        WordAdapter wordAdapter = new WordAdapter(this, words,R.color.catagoryColor);

        ListView list = (ListView) findViewById(R.id.list_view);

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
                mediaPlayer = MediaPlayer.create(ColorActivity.this, word.getSoundResourceid());
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(CompletioListener);
                }
            }
        });
    }

    private void releaseMediaPlayer() {
        if(mediaPlayer!=null){
            mediaPlayer.release();
            mediaPlayer = null;

            audioManager.abandonAudioFocus(FocusChangeListener);
        }
    }
}
