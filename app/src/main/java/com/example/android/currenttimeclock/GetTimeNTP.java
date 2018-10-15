package com.example.android.currenttimeclock;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.instacart.library.truetime.TrueTime;

import java.util.Date;

public class GetTimeNTP {

    public GetTimeNTP(Context context) {
        initTrueTime(context);
    }

    public static long getTrueTime() {
        Date date = TrueTime.isInitialized() ? TrueTime.now() : null;
        if (date != null)
            return date.getTime();
        else {
            return 0;
        }
    }

    public static void initTrueTime(Context ctx) {
        if (isNetworkConnected(ctx)) {
            if (!TrueTime.isInitialized()) {
                InitTrueTimeAsyncTask trueTime = new InitTrueTimeAsyncTask(ctx);
                trueTime.execute();
            }
        } else
            Toast.makeText(ctx, "Please turn on your Internet Connection.", Toast.LENGTH_SHORT).show();
    }

    public static boolean isNetworkConnected(Context ctx) {
        ConnectivityManager cm = (ConnectivityManager) ctx
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();

        return ni != null && ni.isConnectedOrConnecting();
    }
}
