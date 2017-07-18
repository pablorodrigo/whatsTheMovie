package br.com.ilhasoft;

import android.app.Application;
import android.util.Log;

/**
 * Created by pablo on 7/17/17.
 */

public class WhatsMovieApplication extends Application {
    private static final String TAG = "WhatsMovieApplication";
    private static WhatsMovieApplication instance = null;

    public static WhatsMovieApplication getInstance() {
        return instance; // Singleton
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "WhatsMovieApplication.onCreate()");
        // Salva a instância para termos acesso como Singleton
        instance = this;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.d(TAG, "WhatsMovieApplication.onTerminate()");
    }
}
