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

public class Frag_Amharic extends Fragment {

    private ListView lv_amharic;
    public static EditText inputeSearch;
    MyReportListAdapter adapter;
    public static CustomKeyboard mCustomKeyboard;

    private final int AUTOLOAD_THRESHOLD = 10;
    private final int MAXIMUM_ITEMS = 33787;
    private Handler mHandler;
    private boolean mIsLoading = false;
    private boolean mMoreDataAvailable = true;
    private boolean mWasLoading = false;

    boolean isSearching = false;
/*    private Runnable mAddItemsRunnable2 = new Runnable() {
        @Override
        public void run() {
            adapter.addMoreItems(100);
            mIsLoading = false;
        }
    };*/


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

        mCustomKeyboard= new CustomKeyboard(getActivity(),view, R.id.keyboardview, R.xml.kbd );
        mCustomKeyboard.registerEditText(inputeSearch);
        inputeSearch.clearFocus();

        mHandler = new Handler();
//        final ArrayList<DictionaryEntitty> wordlist = Dictionary.myDB.getWordsAmh(0);
        final ArrayList<DictionaryEntitty> wordlist = Splash.amharicwords;
        adapter = new MyReportListAdapter(getContext(), wordlist);
        lv_amharic.setAdapter(adapter);
        lv_amharic.setTextFilterEnabled(true);

        lv_amharic.setOnScrollListener(new AbsListView.OnScrollListener() {

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
//                            mHandler.postDelayed(mAddItemsRunnable2, 10);
                        }
                    }
                }
            }
        });
        inputeSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
/*                if(cs.length()<1){
                    isSearching=false;
                    adapter.wordlist = wordlist;
                    adapter.currentWord= "";
                    adapter.mCount = adapter.wordlist.size();
                    adapter.notifyDataSetChanged();
                }
                else if(cs.length()==1) {
                    isSearching = true;
                    if (cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_0[0]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_0[1])
                            |cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_0[2]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_0[3])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_0[4]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_0[5])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_0[6])){
                        if (adapter.currentWord.equals(CustomKeyboard.AMHARIC_WORDS_0[0])) {
                            adapter.getFilter().filter(cs);

                        } else {
                            adapter.currentWord = CustomKeyboard.AMHARIC_WORDS_0[0];
                            adapter.orig = null;
                            adapter.search(cs.toString(), 0, 154);
                        }
                    }
                    else if (cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_1[0]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_1[1])
                            |cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_1[2]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_1[3])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_1[4]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_1[5])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_1[6])){
                        if (adapter.currentWord.equals(CustomKeyboard.AMHARIC_WORDS_1[0])) {
                            adapter.getFilter().filter(cs);

                        } else {
                            adapter.currentWord = CustomKeyboard.AMHARIC_WORDS_1[0];
                            adapter.orig = null;
                            adapter.search(cs.toString(), 155, 278);
                        }
                    }

                    else if (cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[0]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[1])
                            |cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[2]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[3])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[4]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[5])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[6])){
                        if (adapter.currentWord.equals(CustomKeyboard.AMHARIC_WORDS_2[0])) {
                            adapter.getFilter().filter(cs);

                        } else {
                            adapter.currentWord = CustomKeyboard.AMHARIC_WORDS_2[0];
                            adapter.orig = null;
                            adapter.search(cs.toString(), 155, 278);
                        }
                    }

                    else if (cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[0]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[1])
                            |cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[2]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[3])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[4]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[5])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[6])){
                        if (adapter.currentWord.equals(CustomKeyboard.AMHARIC_WORDS_2[0])) {
                            adapter.getFilter().filter(cs);

                        } else {
                            adapter.currentWord = CustomKeyboard.AMHARIC_WORDS_2[0];
                            adapter.orig = null;
                            adapter.search(cs.toString(), 155, 278);
                        }
                    }

                    else if (cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[0]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[1])
                            |cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[2]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[3])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[4]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[5])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[6])){
                        if (adapter.currentWord.equals(CustomKeyboard.AMHARIC_WORDS_2[0])) {
                            adapter.getFilter().filter(cs);

                        } else {
                            adapter.currentWord = CustomKeyboard.AMHARIC_WORDS_2[0];
                            adapter.orig = null;
                            adapter.search(cs.toString(), 155, 278);
                        }
                    }

                    else if (cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[0]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[1])
                            |cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[2]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[3])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[4]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[5])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[6])){
                        if (adapter.currentWord.equals(CustomKeyboard.AMHARIC_WORDS_2[0])) {
                            adapter.getFilter().filter(cs);

                        } else {
                            adapter.currentWord = CustomKeyboard.AMHARIC_WORDS_2[0];
                            adapter.orig = null;
                            adapter.search(cs.toString(), 155, 278);
                        }
                    }

                    else if (cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[0]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[1])
                            |cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[2]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[3])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[4]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[5])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[6])){
                        if (adapter.currentWord.equals(CustomKeyboard.AMHARIC_WORDS_2[0])) {
                            adapter.getFilter().filter(cs);

                        } else {
                            adapter.currentWord = CustomKeyboard.AMHARIC_WORDS_2[0];
                            adapter.orig = null;
                            adapter.search(cs.toString(), 155, 278);
                        }
                    }

                    else if (cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[0]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[1])
                            |cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[2]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[3])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[4]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[5])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[6])){
                        if (adapter.currentWord.equals(CustomKeyboard.AMHARIC_WORDS_2[0])) {
                            adapter.getFilter().filter(cs);

                        } else {
                            adapter.currentWord = CustomKeyboard.AMHARIC_WORDS_2[0];
                            adapter.orig = null;
                            adapter.search(cs.toString(), 155, 278);
                        }
                    }

                    else if (cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[0]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[1])
                            |cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[2]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[3])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[4]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[5])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[6])){
                        if (adapter.currentWord.equals(CustomKeyboard.AMHARIC_WORDS_2[0])) {
                            adapter.getFilter().filter(cs);

                        } else {
                            adapter.currentWord = CustomKeyboard.AMHARIC_WORDS_2[0];
                            adapter.orig = null;
                            adapter.search(cs.toString(), 155, 278);
                        }
                    }

                    else if (cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[0]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[1])
                            |cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[2]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[3])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[4]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[5])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[6])){
                        if (adapter.currentWord.equals(CustomKeyboard.AMHARIC_WORDS_2[0])) {
                            adapter.getFilter().filter(cs);

                        } else {
                            adapter.currentWord = CustomKeyboard.AMHARIC_WORDS_2[0];
                            adapter.orig = null;
                            adapter.search(cs.toString(), 155, 278);
                        }
                    }

                    else if (cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[0]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[1])
                            |cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[2]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[3])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[4]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[5])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[6])){
                        if (adapter.currentWord.equals(CustomKeyboard.AMHARIC_WORDS_2[0])) {
                            adapter.getFilter().filter(cs);

                        } else {
                            adapter.currentWord = CustomKeyboard.AMHARIC_WORDS_2[0];
                            adapter.orig = null;
                            adapter.search(cs.toString(), 155, 278);
                        }
                    }

                    else if (cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[0]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[1])
                            |cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[2]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[3])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[4]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[5])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[6])){
                        if (adapter.currentWord.equals(CustomKeyboard.AMHARIC_WORDS_2[0])) {
                            adapter.getFilter().filter(cs);

                        } else {
                            adapter.currentWord = CustomKeyboard.AMHARIC_WORDS_2[0];
                            adapter.orig = null;
                            adapter.search(cs.toString(), 155, 278);
                        }
                    }

                    else if (cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[0]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[1])
                            |cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[2]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[3])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[4]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[5])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[6])){
                        if (adapter.currentWord.equals(CustomKeyboard.AMHARIC_WORDS_2[0])) {
                            adapter.getFilter().filter(cs);

                        } else {
                            adapter.currentWord = CustomKeyboard.AMHARIC_WORDS_2[0];
                            adapter.orig = null;
                            adapter.search(cs.toString(), 155, 278);
                        }
                    }

                    else if (cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[0]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[1])
                            |cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[2]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[3])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[4]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[5])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[6])){
                        if (adapter.currentWord.equals(CustomKeyboard.AMHARIC_WORDS_2[0])) {
                            adapter.getFilter().filter(cs);

                        } else {
                            adapter.currentWord = CustomKeyboard.AMHARIC_WORDS_2[0];
                            adapter.orig = null;
                            adapter.search(cs.toString(), 155, 278);
                        }
                    }

                    else if (cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[0]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[1])
                            |cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[2]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[3])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[4]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[5])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[6])){
                        if (adapter.currentWord.equals(CustomKeyboard.AMHARIC_WORDS_2[0])) {
                            adapter.getFilter().filter(cs);

                        } else {
                            adapter.currentWord = CustomKeyboard.AMHARIC_WORDS_2[0];
                            adapter.orig = null;
                            adapter.search(cs.toString(), 155, 278);
                        }
                    }

                    else if (cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[0]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[1])
                            |cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[2]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[3])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[4]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[5])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[6])){
                        if (adapter.currentWord.equals(CustomKeyboard.AMHARIC_WORDS_2[0])) {
                            adapter.getFilter().filter(cs);

                        } else {
                            adapter.currentWord = CustomKeyboard.AMHARIC_WORDS_2[0];
                            adapter.orig = null;
                            adapter.search(cs.toString(), 155, 278);
                        }
                    }

                    else if (cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[0]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[1])
                            |cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[2]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[3])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[4]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[5])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[6])){
                        if (adapter.currentWord.equals(CustomKeyboard.AMHARIC_WORDS_2[0])) {
                            adapter.getFilter().filter(cs);

                        } else {
                            adapter.currentWord = CustomKeyboard.AMHARIC_WORDS_2[0];
                            adapter.orig = null;
                            adapter.search(cs.toString(), 155, 278);
                        }
                    }

                    else if (cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[0]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[1])
                            |cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[2]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[3])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[4]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[5])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[6])){
                        if (adapter.currentWord.equals(CustomKeyboard.AMHARIC_WORDS_2[0])) {
                            adapter.getFilter().filter(cs);

                        } else {
                            adapter.currentWord = CustomKeyboard.AMHARIC_WORDS_2[0];
                            adapter.orig = null;
                            adapter.search(cs.toString(), 155, 278);
                        }
                    }

                    else if (cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[0]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[1])
                            |cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[2]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[3])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[4]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[5])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[6])){
                        if (adapter.currentWord.equals(CustomKeyboard.AMHARIC_WORDS_2[0])) {
                            adapter.getFilter().filter(cs);

                        } else {
                            adapter.currentWord = CustomKeyboard.AMHARIC_WORDS_2[0];
                            adapter.orig = null;
                            adapter.search(cs.toString(), 155, 278);
                        }
                    }

                    else if (cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[0]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[1])
                            |cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[2]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[3])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[4]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[5])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[6])){
                        if (adapter.currentWord.equals(CustomKeyboard.AMHARIC_WORDS_2[0])) {
                            adapter.getFilter().filter(cs);

                        } else {
                            adapter.currentWord = CustomKeyboard.AMHARIC_WORDS_2[0];
                            adapter.orig = null;
                            adapter.search(cs.toString(), 155, 278);
                        }
                    }

                    else if (cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[0]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[1])
                            |cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[2]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[3])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[4]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[5])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[6])){
                        if (adapter.currentWord.equals(CustomKeyboard.AMHARIC_WORDS_2[0])) {
                            adapter.getFilter().filter(cs);

                        } else {
                            adapter.currentWord = CustomKeyboard.AMHARIC_WORDS_2[0];
                            adapter.orig = null;
                            adapter.search(cs.toString(), 155, 278);
                        }
                    }

                    else if (cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[0]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[1])
                            |cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[2]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[3])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[4]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[5])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[6])){
                        if (adapter.currentWord.equals(CustomKeyboard.AMHARIC_WORDS_2[0])) {
                            adapter.getFilter().filter(cs);

                        } else {
                            adapter.currentWord = CustomKeyboard.AMHARIC_WORDS_2[0];
                            adapter.orig = null;
                            adapter.search(cs.toString(), 155, 278);
                        }
                    }

                    else if (cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[0]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[1])
                            |cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[2]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[3])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[4]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[5])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[6])){
                        if (adapter.currentWord.equals(CustomKeyboard.AMHARIC_WORDS_2[0])) {
                            adapter.getFilter().filter(cs);

                        } else {
                            adapter.currentWord = CustomKeyboard.AMHARIC_WORDS_2[0];
                            adapter.orig = null;
                            adapter.search(cs.toString(), 155, 278);
                        }
                    }

                    else if (cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[0]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[1])
                            |cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[2]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[3])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[4]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[5])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[6])){
                        if (adapter.currentWord.equals(CustomKeyboard.AMHARIC_WORDS_2[0])) {
                            adapter.getFilter().filter(cs);

                        } else {
                            adapter.currentWord = CustomKeyboard.AMHARIC_WORDS_2[0];
                            adapter.orig = null;
                            adapter.search(cs.toString(), 155, 278);
                        }
                    }

                    else if (cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[0]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[1])
                            |cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[2]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[3])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[4]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[5])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[6])){
                        if (adapter.currentWord.equals(CustomKeyboard.AMHARIC_WORDS_2[0])) {
                            adapter.getFilter().filter(cs);

                        } else {
                            adapter.currentWord = CustomKeyboard.AMHARIC_WORDS_2[0];
                            adapter.orig = null;
                            adapter.search(cs.toString(), 155, 278);
                        }
                    }

                    else if (cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[0]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[1])
                            |cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[2]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[3])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[4]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[5])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[6])){
                        if (adapter.currentWord.equals(CustomKeyboard.AMHARIC_WORDS_2[0])) {
                            adapter.getFilter().filter(cs);

                        } else {
                            adapter.currentWord = CustomKeyboard.AMHARIC_WORDS_2[0];
                            adapter.orig = null;
                            adapter.search(cs.toString(), 155, 278);
                        }
                    }

                    else if (cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[0]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[1])
                            |cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[2]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[3])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[4]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[5])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[6])){
                        if (adapter.currentWord.equals(CustomKeyboard.AMHARIC_WORDS_2[0])) {
                            adapter.getFilter().filter(cs);

                        } else {
                            adapter.currentWord = CustomKeyboard.AMHARIC_WORDS_2[0];
                            adapter.orig = null;
                            adapter.search(cs.toString(), 155, 278);
                        }
                    }

                    else if (cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[0]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[1])
                            |cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[2]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[3])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[4]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[5])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[6])){
                        if (adapter.currentWord.equals(CustomKeyboard.AMHARIC_WORDS_2[0])) {
                            adapter.getFilter().filter(cs);

                        } else {
                            adapter.currentWord = CustomKeyboard.AMHARIC_WORDS_2[0];
                            adapter.orig = null;
                            adapter.search(cs.toString(), 155, 278);
                        }
                    }

                    else if (cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[0]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[1])
                            |cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[2]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[3])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[4]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[5])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[6])){
                        if (adapter.currentWord.equals(CustomKeyboard.AMHARIC_WORDS_2[0])) {
                            adapter.getFilter().filter(cs);

                        } else {
                            adapter.currentWord = CustomKeyboard.AMHARIC_WORDS_2[0];
                            adapter.orig = null;
                            adapter.search(cs.toString(), 155, 278);
                        }
                    }

                    else if (cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[0]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[1])
                            |cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[2]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[3])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[4]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[5])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[6])){
                        if (adapter.currentWord.equals(CustomKeyboard.AMHARIC_WORDS_2[0])) {
                            adapter.getFilter().filter(cs);

                        } else {
                            adapter.currentWord = CustomKeyboard.AMHARIC_WORDS_2[0];
                            adapter.orig = null;
                            adapter.search(cs.toString(), 155, 278);
                        }
                    }

                    else if (cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[0]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[1])
                            |cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[2]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[3])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[4]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[5])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[6])){
                        if (adapter.currentWord.equals(CustomKeyboard.AMHARIC_WORDS_2[0])) {
                            adapter.getFilter().filter(cs);

                        } else {
                            adapter.currentWord = CustomKeyboard.AMHARIC_WORDS_2[0];
                            adapter.orig = null;
                            adapter.search(cs.toString(), 155, 278);
                        }
                    }

                    else if (cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[0]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[1])
                            |cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[2]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[3])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[4]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[5])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[6])){
                        if (adapter.currentWord.equals(CustomKeyboard.AMHARIC_WORDS_2[0])) {
                            adapter.getFilter().filter(cs);

                        } else {
                            adapter.currentWord = CustomKeyboard.AMHARIC_WORDS_2[0];
                            adapter.orig = null;
                            adapter.search(cs.toString(), 155, 278);
                        }
                    }

                    else if (cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[0]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[1])
                            |cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[2]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[3])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[4]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[5])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[6])){
                        if (adapter.currentWord.equals(CustomKeyboard.AMHARIC_WORDS_2[0])) {
                            adapter.getFilter().filter(cs);

                        } else {
                            adapter.currentWord = CustomKeyboard.AMHARIC_WORDS_2[0];
                            adapter.orig = null;
                            adapter.search(cs.toString(), 155, 278);
                        }
                    }

                    else if (cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[0]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[1])
                            |cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[2]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[3])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[4]) | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[5])
                            | cs.toString().startsWith(CustomKeyboard.AMHARIC_WORDS_2[6])){
                        if (adapter.currentWord.equals(CustomKeyboard.AMHARIC_WORDS_2[0])) {
                            adapter.getFilter().filter(cs);

                        } else {
                            adapter.currentWord = CustomKeyboard.AMHARIC_WORDS_2[0];
                            adapter.orig = null;
                            adapter.search(cs.toString(), 155, 278);
                        }
                    }
                }
                else{
                    isSearching = true;
                    adapter.mCount = adapter.wordlist.size();
                    adapter.getFilter().filter(cs);
                }*/

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
        super.onDestroy();
    }




    public class MyReportListAdapter extends BaseAdapter implements Filterable, TextToSpeech.OnInitListener
    {
        String currentWord = "";

        ArrayList<DictionaryEntitty> wordlist;
        public int currentNum = 0;
        private ImageButton btn_tts;
        private TextToSpeech tts;
        public Context context;
        public ArrayList<DictionaryEntitty> orig;
        String text_tts;
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
            ArrayList<DictionaryEntitty> entitties = Dictionary.myDB.getWordsAmh(mCount);
            for(DictionaryEntitty entiry: entitties){
                wordlist.add(entiry);
            }
            mCount += count-1;
            notifyDataSetChanged();
        }

        public void search(String value,int range1,int range2){
            wordlist = new ArrayList<DictionaryEntitty>();
            notifyDataSetChanged();
            ArrayList<DictionaryEntitty> results = Dictionary.myDB.searchAmh(value,0,range1,range2);
            wordlist = results;
            mCount = results.size();
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return mCount;
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflate = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflate.inflate(R.layout.single_layout_word,null);

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
//        if (mWasLoading) {
//            mWasLoading = false;
//            mIsLoading = true;
//            mHandler.postDelayed(mAddItemsRunnable, 10);
//        }
    }

    @Override
    public void onStop() {
        super.onStop();
//        mHandler.removeCallbacks(mAddItemsRunnable);
//        mWasLoading = mIsLoading;
//        mIsLoading = false;
    }

}
