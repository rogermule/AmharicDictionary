package com.brainuptech.amharicdictionary;

import android.content.Context;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.brainuptech.amharicdictionary.Entities.DictionaryEntitty;

import java.util.Locale;

/**
 * Created by Roger on 4/1/2016.
 */

public class ViewMore extends AppCompatActivity implements TextToSpeech.OnInitListener {

    TextView tv_word1, tv_word2;
    MyDatabase myDatabase;
    TextToSpeech tts;
    ImageButton btn_tts, btn_copy;

    String text_tts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_more);
        setSupportActionBar((Toolbar) findViewById(R.id.detail_toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        final String keyWord = getIntent().getExtras().getString("word1");
        final int id = getIntent().getExtras().getInt("id");
        final String lang = getIntent().getExtras().getString("lang");

        tv_word1 = (TextView) findViewById(R.id.detail_word1);
        tv_word2 = (TextView) findViewById(R.id.detail_word2);

        text_tts = "";
        tts = new TextToSpeech(this,this);
        btn_tts = (ImageButton) findViewById(R.id.viewmore_tts);
        btn_copy = (ImageButton) findViewById(R.id.ib_copy);

        myDatabase = new MyDatabase(this);
        Log.i("Test", "id: "+ id + "\nlang: "+lang);
        final DictionaryEntitty dict =  myDatabase.getDetail(lang,id);
        if(dict!=null) {
            tv_word1.setText(dict.getWord1());
            tv_word2.setText(dict.getDefinition());
        }
        tv_word2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                copyToClipboard(ViewMore.this,dict.getDefinition());
                Toast.makeText(getApplicationContext(),"Text copied to clipboard",Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        btn_tts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lang.equals("amh"))
                    text_tts = dict.getDefinition();
                else
                    text_tts = dict.getWord1();
                speakOut();
            }
        });

        btn_copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                copyToClipboard(ViewMore.this,dict.getWord1());
                Toast.makeText(getApplicationContext(),"Text copied to clipboard",Toast.LENGTH_SHORT).show();
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


    @SuppressWarnings("deprecation")
    public boolean copyToClipboard(Context context, String text) {
        try {
            int sdk = android.os.Build.VERSION.SDK_INT;
            if (sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
                android.text.ClipboardManager clipboard = (android.text.ClipboardManager) context
                        .getSystemService(context.CLIPBOARD_SERVICE);
                clipboard.setText(text);
            } else {
                android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context
                        .getSystemService(context.CLIPBOARD_SERVICE);
                android.content.ClipData clip = android.content.ClipData
                        .newPlainText(
                                context.getResources().getString(
                                        R.string.app_name), text);
                clipboard.setPrimaryClip(clip);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}

