package com.example.shekhchilli.miwok;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NumbersOnClickListener();
        ColorOnClickListener();
        PhrasesOnClickListener();
        MembersOnClickListener();

    }

   public void NumbersOnClickListener() {
        TextView numbersTextview = (TextView) findViewById(R.id.numbers_textview);
        numbersTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NumbersActivity.class);
                startActivity(intent);
            }
        });
    }

    public void ColorOnClickListener(){
        TextView colorTextView = (TextView) findViewById(R.id.colors_textview);
        colorTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ColorActivity.class);
                startActivity(intent);
            }
        });
    }

    public void MembersOnClickListener(){
        TextView membersTextView = (TextView) findViewById(R.id.family_members_textview);
        membersTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FamilyMembersActivity.class);
                startActivity(intent);
            }
        });
    }

    public void PhrasesOnClickListener(){
        TextView phrasesTextView = (TextView) findViewById(R.id.phrases_textview);
        phrasesTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PhrasesActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();

    }
}

