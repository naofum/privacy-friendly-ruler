package org.secuso.privacyfriendlyruler;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by yonjuni on 17.11.16.
 */


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
        SplashActivity.this.startActivity(mainIntent);
        SplashActivity.this.finish();

    }

}
