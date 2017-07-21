package br.com.ilhasoft.whatsmovie.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by pablo on 7/20/17.
 * classe utilitaria
 */

public class IlhasoftUtil {

    /**
     * verificar se dispositivo esta conectado a internet
     *
     * @param context
     */
    public static boolean isDataConnectionAvailable(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

}
