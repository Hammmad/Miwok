package com.example.shekhchilli.miwok;


import android.media.MediaPlayer;
import android.os.Bundle;
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
public class PhrasesFragment extends Fragment {

    private MediaPlayer mediaPlayer;

    private MediaPlayer.OnCompletionListener Completionlistener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    public PhrasesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.word_list,container,false);

        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("what is your name","masookka otiiko masookka" , R.raw.phrase_are_you_coming));
        words.add(new Word("what do you do","otiiko kenekaku masookka",R.raw.phrase_come_here));
        words.add(new Word("i am going home","toolokosu oyyissa ", R.raw.phrase_how_are_you_feeling));
        words.add(new Word("please take tea","oyyissa masookka dikoko", R.raw.phrase_im_coming));
        words.add(new Word("more work still to do","kawinta tlakiki ra'b", R.raw.phrase_im_feeling_good));
        words.add(new Word("call me back as soon as possible","temokka okooruka takadidi", R.raw.phrase_lets_go));
        words.add(new Word("where you live jack","kenekaku kawinta",R.raw.phrase_my_name_is));
        words.add(new Word("this language is difficult","kawinta dikoko masookka", R.raw.phrase_where_are_you_going));

        WordAdapter wordAdapter = new WordAdapter(getActivity(), words,R.color.catagoryPhrase);

        ListView list = (ListView) rootview.findViewById(R.id.list_view);

        list.setAdapter(wordAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = words.get(position);
                releaseMediaPlayer();
                mediaPlayer = MediaPlayer.create(getActivity(),word.getSoundResourceid());
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(Completionlistener);

            }

        });

        return rootview;
    }

    public void releaseMediaPlayer(){

        if(mediaPlayer!= null){
            mediaPlayer.release();
            mediaPlayer=null;
        }
    }

}
