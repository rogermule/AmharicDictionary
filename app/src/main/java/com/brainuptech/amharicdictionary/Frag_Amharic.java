package com.brainuptech.amharicdictionary;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.brainuptech.amharicdictionary.Entities.DictionaryEntitty;

import java.util.ArrayList;

public class Frag_Amharic extends Fragment implements SearchView.OnQueryTextListener{

    private ListView lv_amharic;
    private EditText ed_amharic;
    private SearchView mSearchView;
    MyDatabase myDB;

    public CustomKeyboard3 mCustomKeyboard;

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

        mSearchView = (SearchView) view.findViewById(R.id.search_view_amharic);
        ed_amharic = (EditText) view.findViewById(R.id.ed_amharic);

        mCustomKeyboard= new CustomKeyboard3(getActivity(), R.id.keyboardview, R.xml.kbd );
        mCustomKeyboard.registerEditText(ed_amharic);

        lv_amharic = (ListView) view.findViewById(R.id.lv_amharic);
        lv_amharic.setTextFilterEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            lv_amharic.setNestedScrollingEnabled(true);
        }

        myDB = new MyDatabase(getActivity());
        ArrayList<DictionaryEntitty> wordlist = myDB.getWordsAmharic();
        lv_amharic.setAdapter(new MyReportListAdapter(getContext(), (ArrayList<DictionaryEntitty>) wordlist));
        lv_amharic.setTextFilterEnabled(true);
        setupSearchView();

        return view;
    }

    private void setupSearchView() {
        mSearchView.setIconifiedByDefault(false);
        mSearchView.setOnQueryTextListener(this);
        mSearchView.setQueryHint("እዚህ ፈልግ");


    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (TextUtils.isEmpty(newText)) {
            lv_amharic.clearTextFilter();
        } else {
            lv_amharic.setFilterText(newText);
        }
        return true;
    }


    public class MyReportListAdapter extends BaseAdapter implements Filterable
    {
        ArrayList<DictionaryEntitty> wordlist;
        public int currentNum = 0;

        public Context context;
        public ArrayList<DictionaryEntitty> orig;

        public MyReportListAdapter(Context context,ArrayList<DictionaryEntitty> wordlist)
        {
            this.context = context;
            this.wordlist = wordlist;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return wordlist.size();
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

            TextView tv_title = (TextView) convertView.findViewById(R.id.word_title);
            TextView tv_definition = (TextView) convertView.findViewById(R.id.word_definition);


            final String title = wordlist.get(position).getWord1();
            final String definition = wordlist.get(position).getDefinition();

            currentNum = position;
            tv_title.setText(title);
            tv_definition.setText(definition);

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(),ViewMore.class);
                    Bundle b = new Bundle();
                    b.putString("lang","amh");
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

    }

}
