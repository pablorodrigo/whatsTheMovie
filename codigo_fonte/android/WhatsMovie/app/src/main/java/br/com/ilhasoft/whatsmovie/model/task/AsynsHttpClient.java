package br.com.ilhasoft.whatsmovie.model.task;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by pablo on 7/18/17.
 * classe de configuracao do framework
 */

class AsynsHttpClient {

    private static AsyncHttpClient client = new AsyncHttpClient();

    static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(url, params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(url, params, responseHandler);
    }

}
