package com.brainuptech.amharicdictionary;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.brainuptech.amharicdictionary.Entities.DictionaryEntitty;

import java.util.Locale;

/**
 * Created by Roger on 4/1/2016.
 */

public class ViewMore extends AppCompatActivity implements TextToSpeech.OnInitListener {

    TextView tv_word1, tv_word2;
    MyDatabase myDatabase;
    TextToSpeech tts;
    ImageButton btn_tts;

    String text_tts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_more);
        setSupportActionBar((Toolbar) findViewById(R.id.detail_toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final String keyWord = getIntent().getExtras().getString("word1");
        final String lang = getIntent().getExtras().getString("lang");

        tv_word1 = (TextView) findViewById(R.id.detail_word1);
        tv_word2 = (TextView) findViewById(R.id.detail_word2);

        text_tts = "";
        tts = new TextToSpeech(this,this);
        btn_tts = (ImageButton) findViewById(R.id.viewmore_tts);

        myDatabase = new MyDatabase(this);
        final DictionaryEntitty dict =  myDatabase.getDetail(keyWord, lang);

        tv_word1.setText(keyWord);
        tv_word2.setText(dict.getDefinition());

        btn_tts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lang.equals("amh"))
                    text_tts = dict.getDefinition();
                else
                    text_tts = keyWord;
                speakOut();
            }
        });
    }

    private void speakOut() {
        tts.speak(text_tts.toString(), TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = tts.setLanguage(Locale.US);
            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {
                btn_tts.setEnabled(true);
                speakOut();
            }
        } else {
            Log.e("TTS", "Initilization Failed!");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}

