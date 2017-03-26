package com.watchdroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by Tronix on 28-02-2017.
 */

public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("Pref", Context.MODE_PRIVATE);
        Boolean keypassonoff = pref.getBoolean("keypassonoff", Boolean.parseBoolean(null));
        Boolean keyloggingset = pref.getBoolean("keyloggingset", Boolean.parseBoolean(null));
        if (keypassonoff) {
            if (keyloggingset) {
                Intent i = new Intent(SplashScreen.this, Lock.class);
                startActivity(i);
                finish();
            } else {
                openapp();
                Toast.makeText(SplashScreen.this, "If you enabled password then set pins to enable lockscreen",
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            openapp();
        }
    }

    private void openapp() {
        Intent j = new Intent(SplashScreen.this, MainActivity.class);
        startActivity(j);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}