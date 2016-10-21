package com.example.sarthakjain.billsplitter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by sarthak jain on 11-08-2015.
 */
public class Splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        Thread timer=new Thread() {

            public void run() {
                try {
                sleep(5000);
                } catch (InterruptedException e) {
                   e.printStackTrace();

                } finally {
                    finish();
                    Intent ourintent=new Intent("android.intent.action.START");
                    startActivity(ourintent);
                }
            }
        };

    timer.start();
    }
}
