package org.metabrainz.mobile.presentation;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import org.metabrainz.mobile.App;
import org.metabrainz.mobile.data.sources.api.entities.AccessToken;

public class LoginSharedPreferences {

    public static final String ACCESS_TOKEN = "access_token";
    public static final String REFRESH_TOKEN = "refresh_token";
    public static final int STATUS_LOGGED_IN = 1;
    public static final int STATUS_LOGGED_OUT = 0;

    public static SharedPreferences getPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(App.getContext());
    }

    public static void saveOAuthToken(SharedPreferences preferences, AccessToken token) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(ACCESS_TOKEN, token.getAccessToken());
        editor.putString(REFRESH_TOKEN, token.getRefreshToken());
        editor.apply();
    }

    public static int getLoginStatus(SharedPreferences preferences) {
        String accessToken = preferences.getString(ACCESS_TOKEN, "");
        if (accessToken != null && !accessToken.isEmpty()) return STATUS_LOGGED_IN;
        else return STATUS_LOGGED_OUT;
    }

    public static String getAccessToken(SharedPreferences preferences) {
        return preferences.getString(ACCESS_TOKEN, "");
    }

    public static String getRefreshToken(SharedPreferences preferences) {
        return preferences.getString(REFRESH_TOKEN, "");
    }
}
