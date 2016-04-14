package com.brainuptech.amharicdictionary;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.brainuptech.amharicdictionary.Entities.DictionaryEntitty;

import java.util.ArrayList;
import java.util.Locale;

public class Frag_English extends Fragment implements SearchView.OnQueryTextListener{

    private ListView lv_english;
    private SearchView mSearchView;

    MyDatabase myDB;
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

        mSearchView = (SearchView) view.findViewById(R.id.search_view);
        lv_english = (ListView) view.findViewById(R.id.lv_english);
        lv_english.setTextFilterEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            lv_english.setNestedScrollingEnabled(true);
        }

        myDB = new MyDatabase(getActivity());
        ArrayList<DictionaryEntitty> wordlist = myDB.getWords();
        lv_english.setAdapter(new MyReportListAdapter(getContext(), wordlist));
        lv_english.setTextFilterEnabled(true);

        setupSearchView();

        return view;
    }



    private void setupSearchView() {
        mSearchView.setIconifiedByDefault(false);
        mSearchView.setOnQueryTextListener(this);
        mSearchView.setQueryHint("Search Here");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (TextUtils.isEmpty(newText)) {
            lv_english.clearTextFilter();
        } else {
            lv_english.setFilterText(newText);
        }
        return true;
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


            final String title = wordlist.get(position).getWord1();
            final String definition = wordlist.get(position).getDefinition();

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
                    Intent intent = new Intent(getActivity(),ViewMore.class);
                    Bundle b = new Bundle();
                    b.putString("lang","eng");
                    b.putString("word1",title);
                    intent.putExtras(b);
                    startActivity(intent);
                }
            });

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
