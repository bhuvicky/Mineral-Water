package com.bhuvanesh.mineralwater.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.bhuvanesh.mineralwater.MWApplicaiton;

/**
 * Created by bhuvanesh on 31-01-2017.
 */

public final class MWPreference {

    private static final String PREFERENCE_FILE = "Mineral_Water_Preference";
    private static MWPreference mInstance;
    private static SharedPreferences mSharedPreferences;

    private final String PREFERENCE_KEY_PROFILE_ID = "PREFERENCE_KEY_PROFILE_ID";

    private MWPreference  (){}

    public static synchronized MWPreference getInstance() {
        if (mInstance == null) {
            mInstance = new MWPreference();
            mSharedPreferences = MWApplicaiton.getInstance().getSharedPreferences(PREFERENCE_FILE, Context.MODE_PRIVATE);
        }
        return mInstance;
    }

    public void setProfileId(long profileId) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putLong(PREFERENCE_KEY_PROFILE_ID, profileId);
        editor.apply();
    }

    public long getProfileId() {
        return mSharedPreferences.getLong(PREFERENCE_KEY_PROFILE_ID, 0L);
    }
}
