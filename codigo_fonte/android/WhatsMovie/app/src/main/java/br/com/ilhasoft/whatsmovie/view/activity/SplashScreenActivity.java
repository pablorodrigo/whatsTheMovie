package br.com.ilhasoft.whatsmovie.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import br.com.ilhasoft.whatsmovie.R;

/**
 * Created by pablo on 7/20/17.
 */

public class SplashScreenActivity extends GenericActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen_logo);

        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        }, 1000);


    }


}
