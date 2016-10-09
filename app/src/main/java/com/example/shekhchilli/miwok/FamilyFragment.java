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
public class FamilyFragment extends Fragment {

    private MediaPlayer mediaPlayer;
    private MediaPlayer.OnCompletionListener CompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMedidaPlayer();
        }
    };

    public void releaseMedidaPlayer(){
        if (mediaPlayer!=null){
            mediaPlayer.release();
            mediaPlayer =null;
        }
    }


    public FamilyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View rootview = inflater.inflate(R.layout.word_list, container,false);

        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("daughter","lutti",R.drawable.family_daughter, R.raw.family_daughter));
        words.add(new Word("father","otiiko",R.drawable.family_father, R.raw.family_father));
        words.add(new Word("grand father","toolokosu",R.drawable.family_grandfather, R.raw.family_grandfather));
        words.add(new Word("grand mother","oyyissa",R.drawable.family_grandmother, R.raw.family_grandmother));
        words.add(new Word("brother","masookka",R.drawable.family_older_brother, R.raw.family_older_brother));
        words.add(new Word("sister","temokka",R.drawable.family_older_sister, R.raw.family_older_sister));
        words.add(new Word("son","kenekaku",R.drawable.family_son, R.raw.family_son));
        words.add(new Word("younger brother","kawinta",R.drawable.family_younger_brother, R.raw.family_younger_brother));
        words.add(new Word("younger sister","wo'r",R.drawable.family_younger_sister, R.raw.family_younger_sister));


        WordAdapter wordAdapter = new WordAdapter(getActivity(), words,R.color.catagoryFamily);

        ListView list = (ListView) rootview.findViewById(R.id.list_view);

        list.setAdapter(wordAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = words.get(position);
                releaseMedidaPlayer();
                MediaPlayer mediaPlayer = MediaPlayer.create(getActivity(), word.getSoundResourceid());
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(CompletionListener);

            }
        });


        return rootview;
    }

}
