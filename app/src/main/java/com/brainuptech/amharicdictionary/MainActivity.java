package com.brainuptech.amharicdictionary;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.ads.AdRequest;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    SharedPreferences settings;

    public static AdRequest adRequest;
/*
    public static NativeExpressAdView adView;
    public static View mainAdView;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

/*        mainAdView = getLayoutInflater().inflate(R.layout.adview,null);
        adView = (NativeExpressAdView) mainAdView.findViewById(R.id.adViewNativeSmall);*/
        adRequest = new AdRequest.Builder().build();
//        adView.loadAd(adRequest);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        toolbar.setTitle("Amharic Dictionary");

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Frag_Amharic.inputeSearch.setText("");
                Frag_English.inputeSearch.setText("");

                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        settings = getSharedPreferences(Splash.PREFS_NAME, 0);
        if(!settings.getBoolean("hasRate",false)) {
            int countVal = settings.getInt("count", 0);
            if (countVal > 9) {
                rateMyAppDialog();
            }
        }



    }

    private void rateMyAppDialog() {


        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences.Editor editor = settings.edit();
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        //Yes button clicked
                        rateMyApp();
                        editor.putBoolean("hasRate",true);
                        editor.commit();
                        break;
                    case DialogInterface.BUTTON_NEUTRAL:
                        //Neutral button clicked
                        editor.putBoolean("hasRate",false);
                        editor.putInt("count",1);
                        editor.commit();
                         break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        editor.putBoolean("hasRate",true);
                        editor.commit();
                        break;
                }
            }
        };


        android.app.AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Amharic Dictionray:").setMessage("Do you like this app? Please Rate it")
                .setPositiveButton("Yes, Rate it now", dialogClickListener)
                .setNegativeButton("No", dialogClickListener)
			   .setNeutralButton("Remind me later", dialogClickListener)
                .show();

    }

    public static View getAds(){
        return null;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }

        else if(Frag_Amharic.mCustomKeyboard != null && Frag_Amharic.mCustomKeyboard.isCustomKeyboardVisible()) {
            if (Frag_Amharic.mCustomKeyboard.isCustomKeyboardVisible())
                Frag_Amharic.mCustomKeyboard.hideCustomKeyboard();
        }
        else {
            super.onBackPressed();
            this.finish();
        }
    }

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this,About.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_english) {
            viewPager.setCurrentItem(0);
        } else if (id == R.id.nav_amharic) {
            viewPager.setCurrentItem(1);
        }  else if (id == R.id.nav_share) {
            shareApp();
        } else if (id == R.id.nav_about) {
            Intent intent = new Intent(this,About.class);
            startActivity(intent);
        } else if (id == R.id.nav_exit) {
            this.finish();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                this.finishAffinity();
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Frag_English(), "English");
        adapter.addFragment(new Frag_Amharic(), "Amharic");

        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


    public void shareApp(){
        String shareBody = "Get Free amharic dictionary  market://details?id=com.brainuptech.amharicdictionary";
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Amharic dictionary");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string.app_name)));
    }

    public void rateMyApp() {

        Uri uri = Uri.parse("market://details?id=com.brainuptech.amharicdictionary");
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=com.brainuptech.amharicdictionary")));
        }
    }

}
