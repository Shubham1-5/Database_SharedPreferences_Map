package com.example.kumsh.anew;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by kumsh on 25-12-2017.
 */

public class Session {
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Context cxt;

    public Session(Context cxt)
    {
        this.cxt=cxt;
        prefs=cxt.getSharedPreferences("Helping",Context.MODE_PRIVATE);
        editor=prefs.edit();
    }
    public void setLoggedIn(boolean loggedIn)
    {
        editor.putBoolean("loggedInmode",loggedIn);
        editor.commit();
    }
    public boolean loggedIn()
    {
        return prefs.getBoolean("loggedInmode",false);
    }
}
