package com.watchdroid;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.arx_era.watchdroid.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class LogView extends Fragment {

    private String FILENAME;
    File dir, file, outfile;
    StringBuilder text;
    String line;
    TextView log;
    Context mContext;



    public LogView() {
        // Required empty public constructor
    }

    public static LogView newInstance() {
        return new LogView();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.log_view, container, false);

        try {
            FILENAME = "keylogger.txt";
            dir = new File("/sdcard/Android/data/arx_era");
            file = new File(dir, FILENAME);
            text = new StringBuilder();
            BufferedReader br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
            br.close();
        } catch (IOException e) {
            text.append("File not found");
        }

        log = (TextView) v.findViewById(R.id.log);
        log.setMovementMethod(new ScrollingMovementMethod());
        log.setText(text);
        log.setGravity(Gravity.NO_GRAVITY);
        return v;
    }
    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

}
