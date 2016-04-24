package com.brainuptech.amharicdictionary;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.brainuptech.amharicdictionary.Entities.DictionaryEntitty;

import java.util.ArrayList;
import java.util.Locale;

public class Frag_English extends Fragment{

    private ListView lv_english;
    public EditText inputeSearch;
    MyReportListAdapter adapter;

    public Frag_English() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_english_list, container, false);

        inputeSearch = (EditText) view.findViewById(R.id.inputSearch_eng);
        lv_english = (ListView) view.findViewById(R.id.lv_english);
        lv_english.setTextFilterEnabled(true);


        ArrayList<DictionaryEntitty> wordlist = Splash.englishwords;
        adapter = new MyReportListAdapter(getContext(), (ArrayList<DictionaryEntitty>) wordlist);
        lv_english.setAdapter(adapter);
        lv_english.setTextFilterEnabled(true);

        inputeSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                 // When user changed the Text
                 Frag_English.this.adapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });
        return view;
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
    }



    public class MyReportListAdapter extends BaseAdapter implements Filterable, TextToSpeech.OnInitListener
    {
        ArrayList<DictionaryEntitty> wordlist;
        public int currentNum = 0;

        public Context context;
        public ArrayList<DictionaryEntitty> orig;
        private TextToSpeech tts;
        private ImageButton btn_tts;

        String text_tts;
        public MyReportListAdapter(Context context,ArrayList<DictionaryEntitty> wordlist)
        {
            this.context = context;
            this.wordlist = wordlist;
            tts = new TextToSpeech(context,this);
            text_tts = "";
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return wordlist.size();
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return wordlist.get(arg0);
        }


        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflate = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflate.inflate(R.layout.single_layout_word,null);

            TextView tv_title = (TextView) convertView.findViewById(R.id.word_title);
            TextView tv_definition = (TextView) convertView.findViewById(R.id.word_definition);
            btn_tts = (ImageButton) convertView.findViewById(R.id.main_tts);

            byte[] byte_word1,byte_word2;
            final String decrypted_word1,decrypted_word2;

            final String title = wordlist.get(position).getWord1();
            final String definition = wordlist.get(position).getDefinition();
            final int id = wordlist.get(position).getId();

                try {
                    byte_word1 = Enc.decodeFile(MyDatabase.keycode, Base64.decode(title, 0));
                    byte_word2 = Enc.decodeFile(MyDatabase.keycode, Base64.decode(definition, 0));

                    if (byte_word1 != null | byte_word2 != null) {
                        decrypted_word1 = new String(byte_word1);
                        decrypted_word2 = new String(byte_word2, "UTF-16BE");

                        currentNum = position;
                        tv_title.setText(decrypted_word1);
                        tv_definition.setText(decrypted_word2);

                        btn_tts.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                text_tts = decrypted_word1;
                                speakOut();
                            }
                        });

                        convertView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getActivity(), ViewMoreEnglish.class);
                                Bundle b = new Bundle();
                                b.putInt("id", id);
                                intent.putExtras(b);
                                startActivity(intent);
                            }
                        });
                    }
                } catch (Exception e){

                }
            return convertView;
        }

        @Override
        public Filter getFilter() {
            return new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    final FilterResults oReturn = new FilterResults();
                    final ArrayList<DictionaryEntitty> results = new ArrayList<DictionaryEntitty>();
                    if (orig == null)
                        orig = wordlist;
                    if (constraint != null) {
                        if (orig != null && orig.size() > 0) {
                            for (final DictionaryEntitty g : orig) {
                                if (g.getWord1().toLowerCase()
                                        .contains(constraint.toString()))
                                    results.add(g);
                            }
                        }
                        oReturn.values = results;
                    }
                    return oReturn;
                }

                @SuppressWarnings("unchecked")
                @Override
                protected void publishResults(CharSequence constraint,
                                              FilterResults results) {
                    wordlist = (ArrayList<DictionaryEntitty>) results.values;
                    notifyDataSetChanged();
                }
            };
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

        private void speakOut() {
            tts.speak(text_tts, TextToSpeech.QUEUE_FLUSH, null);
        }

    }


    }
