package com.jsonapp.parkingmob.ui.preferences;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jsonapp.parkingmob.R;

public class MyAccountConfig extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        setPreferencesFromResource(R.xml.my_account_preference_activity, s);
    }
}
