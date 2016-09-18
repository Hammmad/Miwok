package com.example.shekhchilli.miwok;

import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    private MediaPlayer.OnCompletionListener Completionlistener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
                releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

         final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("what is your name","masookka otiiko masookka" , R.raw.phrase_are_you_coming));
        words.add(new Word("what do you do","otiiko kenekaku masookka",R.raw.phrase_come_here));
        words.add(new Word("i am going home","toolokosu oyyissa ", R.raw.phrase_how_are_you_feeling));
        words.add(new Word("please take tea","oyyissa masookka dikoko", R.raw.phrase_im_coming));
        words.add(new Word("more work still to do","kawinta tlakiki ra'b", R.raw.phrase_im_feeling_good));
        words.add(new Word("call me back as soon as possible","temokka okooruka takadidi", R.raw.phrase_lets_go));
        words.add(new Word("where you live jack","kenekaku kawinta",R.raw.phrase_my_name_is));
        words.add(new Word("this language is difficult","kawinta dikoko masookka", R.raw.phrase_where_are_you_going));

        WordAdapter wordAdapter = new WordAdapter(this, words,R.color.catagoryPhrase);

        ListView list = (ListView) findViewById(R.id.list_view);

        list.setAdapter(wordAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = words.get(position);
                releaseMediaPlayer();
                mediaPlayer = MediaPlayer.create(PhrasesActivity.this,word.getSoundResourceid());
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(Completionlistener);

               }

        });
    }

    public void releaseMediaPlayer(){

        if(mediaPlayer!= null){
            mediaPlayer.release();
            mediaPlayer=null;
        }
    }


    }

