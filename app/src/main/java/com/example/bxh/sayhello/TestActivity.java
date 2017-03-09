package com.example.bxh.sayhello;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by bxh on 2/22/17.
 */

public class TestActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }
}
