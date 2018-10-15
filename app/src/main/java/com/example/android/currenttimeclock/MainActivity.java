package com.example.android.currenttimeclock;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.instacart.library.truetime.TrueTime;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    String TAG = "DBG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new GetTimeNTP(this);
    }

    public void onButtonClick(View v) {
//        Log.d(TAG, getTrueTime().toString());
        long millis = GetTimeNTP.getTrueTime();
        TextView timeTV = findViewById(R.id.time);
        if (!TrueTime.isInitialized()) {
            new GetTimeNTP(this);
            millis = GetTimeNTP.getTrueTime();
        }
        if (!GetTimeNTP.isNetworkConnected(this) && millis == 0)
            Toast.makeText(this, "Check your internet connection.", Toast.LENGTH_SHORT).show();
        else if (millis == 0)
            Toast.makeText(this, "Press BUTTON again.", Toast.LENGTH_SHORT).show();
        if (millis > 0)
            timeTV.setText("" + millis + "\n" + new Date(millis));
    }

//    public static Date getTrueTime() {
//        Date date = TrueTime.isInitialized() ? TrueTime.now() : null;
//        return date;
//    }
//
//    public static void initTrueTime(Context ctx) {
//        if (isNetworkConnected(ctx)) {
//            if (!TrueTime.isInitialized()) {
//                InitTrueTimeAsyncTask trueTime = new InitTrueTimeAsyncTask(ctx);
//                trueTime.execute();
//            }
//        }
//    }
//
//    public static boolean isNetworkConnected(Context ctx) {
//        ConnectivityManager cm = (ConnectivityManager) ctx
//                .getSystemService (Context.CONNECTIVITY_SERVICE);
//        NetworkInfo ni = cm.getActiveNetworkInfo();
//
//        return ni != null && ni.isConnectedOrConnecting();
//    }
}
