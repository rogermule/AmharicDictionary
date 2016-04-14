package com.brainuptech.amharicdictionary;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.brainuptech.amharicdictionary.Entities.DictionaryEntitty;

import java.util.ArrayList;
import java.util.Locale;

public class English extends Fragment implements SearchView.OnQueryTextListener{

    ListView lv;
    SearchView search_view;

    ArrayList<String> countries;
    ArrayAdapter<String> adapter;

    public English() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_english_list, container, false);

        lv = (ListView) view.findViewById(R.id.lv_english);
        search_view = (SearchView) view.findViewById(R.id.search_view);

        String[] locales = Locale.getISOCountries();

        countries = new ArrayList<String>();

        for (String countryCode : locales) {
            Locale obj = new Locale("", countryCode);
            countries.add(obj.getDisplayCountry());
        }

        adapter = new ArrayAdapter<String>(getActivity(),
                R.layout.list_items, R.id.name, countries);

        lv.setAdapter(adapter);

        search_view.setOnQueryTextListener(this);
        return view;
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.getFilter().filter(newText);
        return true;
    }




    public class MyReportListAdapter extends BaseAdapter
    {
        Context context;
        ArrayList<DictionaryEntitty> wordlist;
        public int currentNum = 0;
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
            final int id = Integer.parseInt(wordlist.get(position).getId());

            currentNum = position;
            tv_title.setText(title);
            tv_definition.setText(definition);

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(getActivity(),"Report Number "+currentNum+" Touched",Toast.LENGTH_SHORT).show();
                    Snackbar.make(v, "Word Number " + currentNum + " has been touched", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                }
            });

            return convertView;
        }
    }



}
