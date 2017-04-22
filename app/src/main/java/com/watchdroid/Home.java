package com.watchdroid;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arx_era.watchdroid.R;

/**
 * Created by Tronix on 2/8/2017.
 */
public class Home extends Fragment {
    Context mContext;

    public Home() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Home newInstance() {
        return new Home();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.home, container, false);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences pref = getActivity().getSharedPreferences(
                "Pref",
                Context.MODE_PRIVATE);
        Boolean keylogging = pref.getBoolean("keylogging", Boolean.parseBoolean(null));
        Boolean passonoff = pref.getBoolean("keypassonoff", Boolean.parseBoolean(null));
        TextView keylogstatus = (TextView) getView().findViewById(R.id.keyloggerstatus);
        TextView passonoffstatus = (TextView) getView().findViewById(R.id.passonoffstatus);
        if (keylogging) {
            keylogstatus.setText(" Active");
        } else {
            keylogstatus.setText(" Deactivate");
        }
        if (passonoff) {
            passonoffstatus.setText(" ON");
        } else {
            passonoffstatus.setText(" OFF");
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

}
