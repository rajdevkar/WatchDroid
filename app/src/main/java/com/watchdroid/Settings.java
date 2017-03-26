package com.watchdroid;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;

import com.arx_era.watchdroid.R;
import com.github.machinarius.preferencefragment.PreferenceFragment;

public class Settings extends PreferenceFragment {
    Context mContext;

    public static Settings newInstance() {
        return new Settings();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.settings);

        SharedPreferences pref = getActivity().getSharedPreferences(
                "Pref",
                Context.MODE_PRIVATE);

        final SharedPreferences.Editor editor = pref.edit();
        final Boolean keylogging = pref.getBoolean("keylogging", Boolean.parseBoolean(null));
        final Boolean keyhideicon = pref.getBoolean("keyhideicon", Boolean.parseBoolean(null));
        final Boolean keypassonoff = pref.getBoolean("keypassonoff", Boolean.parseBoolean(null));

        CheckBoxPreference boot = (CheckBoxPreference) getPreferenceManager().findPreference("boot");
        boot.setChecked(keylogging);
        boot.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                Boolean cbv = (Boolean) newValue;
                if (cbv) {
                    editor.putBoolean("keylogging", true);
                    editor.commit();
                } else {
                    editor.putBoolean("keylogging", false);
                    editor.commit();
                }
                return true;
            }
        });

        CheckBoxPreference passonoff = (CheckBoxPreference) getPreferenceManager().findPreference("passonoff");
        passonoff.setChecked(keypassonoff);
        passonoff.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                Boolean cbv = (Boolean) newValue;
                if (cbv) {
                    editor.putBoolean("keypassonoff", true);
                    editor.commit();
                } else {
                    editor.putBoolean("keypassonoff", false);
                    editor.commit();
                }
                return true;
            }
        });
        CheckBoxPreference iconhide = (CheckBoxPreference) getPreferenceManager().findPreference("hideicon");
        iconhide.setChecked(keyhideicon);
        iconhide.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                Boolean cbv = (Boolean) newValue;
                if (cbv) {
                    editor.putBoolean("keyhideicon", true);
                    editor.commit();
                    //hide
                    PackageManager p = getActivity().getPackageManager();
                    ComponentName componentName = new ComponentName(getActivity(), com.watchdroid.SplashScreen.class);
                    p.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);

                } else {
                    editor.putBoolean("keyhideicon", false);
                    editor.commit();
                    PackageManager p = getActivity().getPackageManager();
                    ComponentName componentName = new ComponentName(getActivity(), com.watchdroid.SplashScreen.class);
                    p.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
                }
                return true;
            }
        });

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
