package com.example.cncdhumanedcandroid.Session;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    public SharedPreferences sharedPreferences;

    private Context context;
    public SharedPreferences preferences;
    public SharedPreferences.Editor editor;
    int PRIVATE_MODE = 0;

    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("SessionData", context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveBarearToken(String baerertoken) {
        editor.putString("bearer_token", baerertoken);
        editor.apply();
        editor.commit();
    }

    public String getBearer() {
        return sharedPreferences.getString("bearer_token", "null");
    }

    public void SaveUserData(

            String name, String email, String type, String userid

    ) {

        editor.putString("name", name);
        editor.putString("email", email);
        editor.putString("type", type);
        editor.putString("userId", userid);
        editor.commit();

    }

    public String getName() {

        return sharedPreferences.getString("name", "null");
    }

    public String getEmail() {

        return sharedPreferences.getString("email", "null");
    }

    public String gettype() {

        return sharedPreferences.getString("type", "null");
    }

    public Boolean checkIsApplicationFirstTime() {
        return sharedPreferences.getBoolean("installedFirstTime", true);
    }

    public void ApplicationFirstTime() {

        editor.putBoolean("installedFirstTime", false);
        editor.apply();
        editor.commit();
    }

    public void saveStartCoordinatesAndTime(double startcoordinates_lat, double endcoordinates_lon, String startTime) {

        editor.putString("start_coordinates_lat", String.valueOf(startcoordinates_lat));
        editor.putString("start_coordinates_lon", String.valueOf(endcoordinates_lon));
        editor.putString("start_time", startTime);
        editor.apply();
        editor.commit();
    }

    public String getStartTimestamp() {
        return sharedPreferences.getString("start_time", "00/00/0000 00:00:00");
    }

    public String getLatitudeStart() {

        return sharedPreferences.getString("start_coordinates_lat", "0.0");
    }

    public String getLongitudeStart() {

        return sharedPreferences.getString("start_coordinates_lon", "0.0");
    }


}
