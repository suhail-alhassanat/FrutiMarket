package com.suhail.frutimarket.models;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.suhail.frutimarket.ui.activities.MainActivity;

public class SharedPreferencesClient extends Application {

    static SharedPreferencesClient client;
    Context context=this;

    public static SharedPreferencesClient getInstance(){
        if(client==null)
        client=new SharedPreferencesClient();
        return client;
    }
    private  final String EMAIL_KEY="email",PASSWORD_KEY="password",IS_FIRST_TIME_KEY="is_first_time";
    private   SharedPreferences preferences =context.getSharedPreferences("preference",Context.MODE_PRIVATE);
    private  SharedPreferences loginPreferences =context.getSharedPreferences("LoginDetails",Context.MODE_PRIVATE);
    public    boolean isLoggedIn(){
        if(loginPreferences.getString(EMAIL_KEY,"null").equals("null") || loginPreferences.getString(PASSWORD_KEY,"null").equals("null"))
            return false;
        return true;
    }
    public LoginRequest getLoginDetails(){
        if(isLoggedIn())
            return new LoginRequest(loginPreferences.getString(EMAIL_KEY,"null"), loginPreferences.getString(PASSWORD_KEY,"null"));
        return null;
    }
    public void saveLoginDetails(LoginRequest request){
        SharedPreferences.Editor editor= loginPreferences.edit();
        editor.putString(EMAIL_KEY,request.getEmail());
        editor.putString(PASSWORD_KEY,request.getPassword());
        editor.apply();
    }
    public boolean isFirstTime(){
        if(preferences.contains(IS_FIRST_TIME_KEY))
        return false;
        return true;
    }
    public void updateFirstTimeValue(){
        SharedPreferences.Editor editor=preferences.edit();
        editor.putInt(IS_FIRST_TIME_KEY,1);
        editor.apply();
    }
}
