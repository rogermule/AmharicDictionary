package com.brainuptech.amharicdictionary;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.google.android.gms.ads.NativeExpressAdView;

import java.util.ArrayList;
import java.util.Locale;

public class Frag_Amharic extends Fragment {

    private ListView lv_amharic;
    public static EditText inputeSearch;
    MyReportListAdapter adapter;
    public static CustomKeyboard mCustomKeyboard;

    public static View mainAdView;
    public static NativeExpressAdView adView;
    public Frag_Amharic() {
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
        View view = inflater.inflate(R.layout.fragment_amharic_list, container, false);

        inputeSearch = (EditText) view.findViewById(R.id.inputSearch_amh);
        lv_amharic = (ListView) view.findViewById(R.id.lv_amharic);


/*        adRequest = new AdRequest.Builder().build();
        mAdView = (AdView) mainAdView.findViewById(R.id.adView);
        mAdView.loadAd(adRequest);*/

        mainAdView = inflater.inflate(R.layout.adview,container,false);
        adView = (NativeExpressAdView) mainAdView.findViewById(R.id.adViewNativeSmall);
//        adView.loadAd(new AdRequest.Builder().build());
        adView.loadAd(MainActivity.adRequest);

        mCustomKeyboard= new CustomKeyboard(getActivity(),view, R.id.keyboardview, R.xml.kbd );
        mCustomKeyboard.registerEditText(inputeSearch);
        inputeSearch.clearFocus();

        final ArrayList<DictionaryEntitty> wordlist = Splash.amharicwords;
        adapter = new MyReportListAdapter(getContext(), wordlist);
        lv_amharic.setAdapter(adapter);
        lv_amharic.setTextFilterEnabled(true);

        inputeSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text

                adapter.getFilter().filter(cs);
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
        adView.destroy();
        super.onDestroy();
    }

    @Override
    public void onPause() {
        adView.pause();
        super.onPause();
    }

    @Override
    public void onResume() {
        adView.resume();
        super.onResume();
    }

    public static View getAds(){
        return adView;
    }

    public class MyReportListAdapter extends BaseAdapter implements Filterable, TextToSpeech.OnInitListener
    {

        ArrayList<DictionaryEntitty> wordlist;
        public int currentNum = 0;
        private ImageButton btn_tts;
        private TextToSpeech tts;
        public Context context;
        public ArrayList<DictionaryEntitty> orig;
        String text_tts;
        private int mCount;

        public MyReportListAdapter(Context context,ArrayList<DictionaryEntitty> wordlist)
        {
            this.context = context;
            this.wordlist = wordlist;
            tts = new TextToSpeech(context,this);
            mCount = wordlist.size();
            text_tts = "";
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return mCount;
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            if(arg0%13==0 && arg0!=0){
                return getAds();
            }
            return wordlist.get(arg0);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflate = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

//            if(position%13==0 && position!=0){
//                return getAds();
//            }


                convertView = inflate.inflate(R.layout.single_layout_word, null);

                if (position <= mCount) {
                    TextView tv_title = (TextView) convertView.findViewById(R.id.word_title);
                    TextView tv_definition = (TextView) convertView.findViewById(R.id.word_definition);
                    btn_tts = (ImageButton) convertView.findViewById(R.id.main_tts);

                    final String title = wordlist.get(position).getWord1();
                    final String definition = wordlist.get(position).getDefinition();
                    final int id = wordlist.get(position).getId();
                    currentNum = position;
                    tv_title.setText(title);
                    tv_definition.setText(definition);

                    btn_tts.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            text_tts = definition;
                            speakOut();
                        }
                    });

                    convertView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), ViewMoreAmharic.class);
                            Bundle b = new Bundle();
                            b.putInt("id", id);
                            intent.putExtras(b);
                            startActivity(intent);
                        }
                    });
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
                    mCount=wordlist.size();
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


    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();

    }

}
