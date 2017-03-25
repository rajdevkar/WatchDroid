package com.watchdroid;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Setpass extends AppCompatActivity {

    EditText firstp,firstn,secondp,secondn;
    String firstps,firsts,secondps,seconds,fsp,ssp,fsn,ssn;
    private Boolean keypassonoff,keyloggingcheck,keyloggingset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setpass);

        final SharedPreferences pref = getApplicationContext().getSharedPreferences("Pref", Context.MODE_PRIVATE);
            final SharedPreferences.Editor editor = pref.edit();

        keypassonoff = pref.getBoolean("keypassonoff", Boolean.parseBoolean(null));
        keyloggingcheck = pref.getBoolean("keyloggingcheck", Boolean.parseBoolean(null));
        keyloggingset = pref.getBoolean("keyloggingset", Boolean.parseBoolean(null));

        firstp = (EditText) findViewById(R.id.first_num_pos);
        firstn = (EditText) findViewById(R.id.first_num);
        secondp = (EditText) findViewById(R.id.second_num_pos);
        secondn = (EditText) findViewById(R.id.second_num);

        if(keyloggingcheck){
            fsp = pref.getString("firstpsn", null);
            fsn = pref.getString("firstsn", null);
            ssp = pref.getString("secondpsn", null);
            ssn = pref.getString("secondsn", null);

            firstp.setText(fsp);
            firstn.setText(fsn);
            secondp.setText(ssp);
            secondn.setText(ssn);
        }

        Button submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (keypassonoff) {
                        firstps = firstp.getText().toString();
                        firsts = firstn.getText().toString();
                        secondps = secondp.getText().toString();
                        seconds = secondn.getText().toString();

                        int num = Integer.parseInt(firstps);
                        int num2 = Integer.parseInt(firsts);
                        int num3 = Integer.parseInt(secondps);
                        int num4 = Integer.parseInt(seconds);

                        editor.putString("firstpsn", firstps);
                        editor.putString("firstsn", firsts);
                        editor.putString("secondpsn", secondps);
                        editor.putString("secondsn", seconds);
                        editor.putBoolean("keyloggingcheck", true);
                        editor.putBoolean("keyloggingset", true);
                        editor.commit();
                        Toast.makeText(Setpass.this, "Done !",
                                Toast.LENGTH_LONG).show();
                        finish();
                    } else {
                        Toast.makeText(Setpass.this, "Enable password and try again",
                                Toast.LENGTH_LONG).show();
                    }
                } catch (NumberFormatException e){
                    Toast.makeText(Setpass.this, "Enter proper values",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
