package Entities;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by mrwah on 3/20/2018.
 */

public class Session {

    private SharedPreferences prefs;

    public Session(Context cntx) {
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void setAccount(String account,String type) {
        prefs.edit().putString("account", account).apply();
        prefs.edit().putString("type", type).apply();
    }

    public String getAccount() {
        String account = prefs.getString("account", "");
        return account;
    }
    public String getType() {
        String type = prefs.getString("type", "");
        return type;
    }

    public void deleteAccount() {
        prefs.edit().clear().apply();
    }

}