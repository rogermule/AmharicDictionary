package com.brainuptech.amharicdictionary;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
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

public class Frag_English extends Fragment {

    private ListView lv_english;
    public EditText inputeSearch;
    MyReportListAdapter adapter;



    private final int AUTOLOAD_THRESHOLD = 10;
    private final int MAXIMUM_ITEMS = 33787;
    private Handler mHandler;
    private boolean mIsLoading = false;
    private boolean mMoreDataAvailable = true;
    private boolean mWasLoading = false;

    boolean isSearching = false;
    private Runnable mAddItemsRunnable = new Runnable() {
        @Override
        public void run() {
            adapter.addMoreItems(100);
            mIsLoading = false;
        }
    };

    public Frag_English() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_english_list, container, false);
        final ArrayList<DictionaryEntitty> wordlist = Splash.englishwords;

        adapter = new MyReportListAdapter(getContext(), wordlist);
        mHandler = new Handler();

        inputeSearch = (EditText) view.findViewById(R.id.inputSearch_eng);
        lv_english = (ListView) view.findViewById(R.id.lv_english);
        lv_english.setTextFilterEnabled(true);

        lv_english.setAdapter(adapter);

        lv_english.setTextFilterEnabled(true);
        lv_english.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(!isSearching) {
                    if (!mIsLoading && mMoreDataAvailable) {
                        if (totalItemCount >= MAXIMUM_ITEMS) {
                            mMoreDataAvailable = false;
                        } else if (totalItemCount - AUTOLOAD_THRESHOLD <= firstVisibleItem + visibleItemCount) {
                            mIsLoading = true;
                            mHandler.postDelayed(mAddItemsRunnable, 10);
                        }
                    }
                }
            }
        });

        inputeSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                 // When user changed the Text
                if(cs.length()<1){
                    isSearching=false;
                    adapter.wordlist = Splash.englishwords;
                    adapter.currentWord= "";
                    adapter.mCount = adapter.wordlist.size();
                    adapter.notifyDataSetChanged();
                }
                else if(cs.length()==1) {
                    isSearching = true;
                    if (cs.toString().startsWith("a") | cs.toString().startsWith("A")) {
                        if(adapter.currentWord.equals("a")){
                            adapter.getFilter().filter(cs);

                        }
                        else {
                            adapter.currentWord = "a";
                            adapter.orig = null;
                            adapter.search(cs.toString(), 0, 3243);
                        }
                    } else if (cs.toString().startsWith("b") | cs.toString().startsWith("B")) {
                        if(adapter.currentWord.equals("b")){
                            adapter.getFilter().filter(cs);

                        }
                        else {
                            adapter.currentWord = "b";
                            adapter.orig = null;
                            adapter.search(cs.toString(), 3244, 6337);
                        }

                    } else if (cs.toString().startsWith("c") | cs.toString().startsWith("C")) {
                        if(adapter.currentWord.equals("c")){
                            adapter.getFilter().filter(cs);

                        }
                        else {
                            adapter.currentWord = "c";
                            adapter.orig = null;
                            adapter.search(cs.toString(), 6338, 11368);
                        }

                    } else if (cs.toString().startsWith("d") | cs.toString().startsWith("D")) {
                        if(adapter.currentWord.equals("d")){
                            adapter.getFilter().filter(cs);
                        }
                        else {
                            adapter.currentWord = "d";
                            adapter.orig = null;
                            adapter.search(cs.toString(), 11369, 14380);
                        }

                    } else if (cs.toString().startsWith("e") | cs.toString().startsWith("E")) {
                        if(adapter.currentWord.equals("e")){
                            adapter.getFilter().filter(cs);
                        }
                        else {
                            adapter.currentWord = "e";
                            adapter.orig = null;
                            adapter.search(cs.toString(), 14381, 15764);
                        }

                    } else if (cs.toString().startsWith("f") | cs.toString().startsWith("F")) {
                        if(adapter.currentWord.equals("f")){
                            adapter.getFilter().filter(cs);
                        }
                        else {
                            adapter.currentWord = "f";
                            adapter.orig = null;
                            adapter.search(cs.toString(), 15765, 17170);
                        }

                    } else if (cs.toString().startsWith("g") | cs.toString().startsWith("G")) {
                        if(adapter.currentWord.equals("g")){
                            adapter.getFilter().filter(cs);
                        }
                        else {
                            adapter.currentWord = "g";
                            adapter.orig = null;
                            adapter.search(cs.toString(), 17171, 18123);
                        }

                    } else if (cs.toString().startsWith("h") | cs.toString().startsWith("H")) {
                        if(adapter.currentWord.equals("h")){
                            adapter.getFilter().filter(cs);
                        }
                        else {
                            adapter.currentWord = "h";
                            adapter.orig = null;
                            adapter.search(cs.toString(), 18124, 19195);
                        }

                    } else if (cs.toString().startsWith("i") | cs.toString().startsWith("I")) {
                        if(adapter.currentWord.equals("i")){
                            adapter.getFilter().filter(cs);
                        }
                        else {
                            adapter.currentWord = "i";
                            adapter.orig = null;
                            adapter.search(cs.toString(), 19196, 20290);
                        }

                    } else if (cs.toString().startsWith("j") | cs.toString().startsWith("J")) {
                        if(adapter.currentWord.equals("j")){
                            adapter.getFilter().filter(cs);
                        }
                        else {
                            adapter.currentWord = "j";
                            adapter.orig = null;
                            adapter.search(cs.toString(), 20291, 20596);
                        }

                    } else if (cs.toString().startsWith("k") | cs.toString().startsWith("K")) {
                        if(adapter.currentWord.equals("k")){
                            adapter.getFilter().filter(cs);
                        }
                        else {
                            adapter.currentWord = "k";
                            adapter.orig = null;
                            adapter.search(cs.toString(), 20597, 20928);
                        }

                    } else if (cs.toString().startsWith("l") | cs.toString().startsWith("L")) {
                        if(adapter.currentWord.equals("l")){
                            adapter.getFilter().filter(cs);
                        }
                        else {
                            adapter.currentWord = "l";
                            adapter.orig = null;
                            adapter.search(cs.toString(), 20929, 21886);
                        }

                    } else if (cs.toString().startsWith("m") | cs.toString().startsWith("M")) {
                        if(adapter.currentWord.equals("m")){
                            adapter.getFilter().filter(cs);
                        }
                        else {
                            adapter.currentWord = "m";
                            adapter.orig = null;
                            adapter.search(cs.toString(), 21887, 23335);
                        }

                    } else if (cs.toString().startsWith("n") | cs.toString().startsWith("N")) {
                        if(adapter.currentWord.equals("n")){
                            adapter.getFilter().filter(cs);
                        }
                        else {
                            adapter.currentWord = "n";
                            adapter.orig = null;
                            adapter.search(cs.toString(), 23336, 24156);
                        }

                    } else if (cs.toString().startsWith("o") | cs.toString().startsWith("O")) {
                        if(adapter.currentWord.equals("o")){
                            adapter.getFilter().filter(cs);
                        }
                        else {
                            adapter.currentWord = "o";
                            adapter.orig = null;
                            adapter.search(cs.toString(), 24157, 25011);
                        }

                    } else if (cs.toString().startsWith("p") | cs.toString().startsWith("P")) {
                        if(adapter.currentWord.equals("p")){
                            adapter.getFilter().filter(cs);
                        }
                        else {
                            adapter.currentWord = "p";
                            adapter.orig = null;
                            adapter.search(cs.toString(), 25012, 27036);
                        }

                    } else if (cs.toString().startsWith("q") | cs.toString().startsWith("Q")) {
                        if(adapter.currentWord.equals("q")){
                            adapter.getFilter().filter(cs);
                        }
                        else {
                            adapter.currentWord = "q";
                            adapter.orig = null;
                            adapter.search(cs.toString(), 27037, 27303);
                        }

                    } else if (cs.toString().startsWith("r") | cs.toString().startsWith("R")) {
                        if(adapter.currentWord.equals("r")){
                            adapter.getFilter().filter(cs);
                        }
                        else {
                            adapter.currentWord = "r";
                            adapter.orig = null;
                            adapter.search(cs.toString(), 27304, 28431);
                        }

                    } else if (cs.toString().startsWith("s") | cs.toString().startsWith("S")) {
                        if(adapter.currentWord.equals("s")){
                            adapter.getFilter().filter(cs);
                        }
                        else {
                            adapter.currentWord = "s";
                            adapter.orig = null;
                            adapter.search(cs.toString(), 28432, 30982);
                        }

                    } else if (cs.toString().startsWith("t") | cs.toString().startsWith("T")) {
                        if(adapter.currentWord.equals("t")){
                            adapter.getFilter().filter(cs);
                        }
                        else {
                            adapter.currentWord = "t";
                            adapter.orig = null;
                            adapter.search(cs.toString(), 30983, 32169);
                        }

                    } else if (cs.toString().startsWith("u") | cs.toString().startsWith("U")) {
                        if(adapter.currentWord.equals("u")){
                            adapter.getFilter().filter(cs);
                        }
                        else {
                            adapter.currentWord = "u";
                            adapter.orig = null;
                            adapter.search(cs.toString(), 32170, 32501);
                        }

                    } else if (cs.toString().startsWith("v") | cs.toString().startsWith("V")) {
                        if(adapter.currentWord.equals("v")){
                            adapter.getFilter().filter(cs);
                        }
                        else {
                            adapter.currentWord = "v";
                            adapter.orig = null;
                            adapter.search(cs.toString(), 32502, 32931);
                        }

                    } else if (cs.toString().startsWith("w") | cs.toString().startsWith("W")) {
                        if(adapter.currentWord.equals("w")){
                            adapter.getFilter().filter(cs);
                        }
                        else {
                            adapter.currentWord = "w";
                            adapter.orig = null;
                            adapter.search(cs.toString(), 32932, 33552);
                        }

                    } else if (cs.toString().startsWith("x") | cs.toString().startsWith("X")) {
                        if(adapter.currentWord.equals("x")){
                            adapter.getFilter().filter(cs);
                        }
                        else {
                            adapter.currentWord = "x";
                            adapter.orig = null;
                            adapter.search(cs.toString(), 33553, 33584);
                        }

                    } else if (cs.toString().startsWith("y") | cs.toString().startsWith("Y")) {
                        if(adapter.currentWord.equals("y")){
                            adapter.getFilter().filter(cs);
                        }
                        else {
                            adapter.currentWord = "y";
                            adapter.orig = null;
                            adapter.search(cs.toString(), 33585, 33709);
                        }

                    } else if (cs.toString().startsWith("z") | cs.toString().startsWith("Z")) {
                        if(adapter.currentWord.equals("z")){
                            adapter.getFilter().filter(cs);
                        }
                        else {
                            adapter.currentWord = "z";
                            adapter.orig = null;
                            adapter.search(cs.toString(), 33710, 33784);
                        }
                    }
                }
                else{
                    isSearching = true;
                    adapter.mCount = adapter.wordlist.size();
                    adapter.getFilter().filter(cs);
                }

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



    public static class MyReportListAdapter extends BaseAdapter implements Filterable, TextToSpeech.OnInitListener
    {
        String currentWord = "";
        public static ArrayList<DictionaryEntitty> wordlist;
        public int currentNum = 0;
        public Context context;
        public ArrayList<DictionaryEntitty> orig;
        private TextToSpeech tts;
        private ImageButton btn_tts;
        String text_tts;

//        private int mCount = 10;
        private int mCount = 1;

        public MyReportListAdapter(Context context,ArrayList<DictionaryEntitty> wordlist)
        {
            this.context = context;
            this.wordlist = wordlist;
            tts = new TextToSpeech(context,this);
            mCount = wordlist.size();
            text_tts = "";
        }

        public void addMoreItems(int count) {
            ArrayList<DictionaryEntitty> entitties = Dictionary.myDB.getWordsEng(mCount);
            for(DictionaryEntitty entiry: entitties){
                wordlist.add(entiry);
            }
            mCount += count-1;
            notifyDataSetChanged();
        }
        public void addMoreItemsSearch(int count) {
            ArrayList<DictionaryEntitty> entitties = Dictionary.myDB.getWordsEng(mCount);
            for(DictionaryEntitty entiry: entitties){
                wordlist.add(entiry);
            }
            mCount += count-1;
            notifyDataSetChanged();
        }

        public void search(String value,int range1,int range2){
            wordlist = new ArrayList<DictionaryEntitty>();
            notifyDataSetChanged();
            ArrayList<DictionaryEntitty> results = Dictionary.myDB.searchEng(value,0,range1,range2);
            wordlist = results;
            mCount = results.size();
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
//            return wordlist.size();
            return mCount;
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
            LayoutInflater inflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflate.inflate(R.layout.single_layout_word, null);

            if(position<=mCount) {
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
                        text_tts = title;
                        speakOut();
                    }
                });

                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context.getApplicationContext(), ViewMoreEnglish.class);
                        Bundle b = new Bundle();
                        b.putInt("id", id);
                        intent.putExtras(b);
                        context.startActivity(intent);
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
                    //btn_tts.setEnabled(true);
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
/*        if (mWasLoading) {
            mWasLoading = false;
            mIsLoading = true;
            mHandler.postDelayed(mAddItemsRunnable, 1000);
        }*/
    }

    @Override
    public void onStop() {
        super.onStop();
        mHandler.removeCallbacks(mAddItemsRunnable);
        mWasLoading = mIsLoading;
        mIsLoading = false;
    }
}
