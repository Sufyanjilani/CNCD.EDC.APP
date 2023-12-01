package com.example.cncdhumanedcandroid.Utils;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
public class InternetUtils {

    // Method to check internet connectivity
    public static boolean isInternetConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            if (activeNetwork != null) {
                // Connected to the internet
                return activeNetwork.isConnectedOrConnecting();
            }
        }
        // Not connected to the internet
        return false;
    }
}
