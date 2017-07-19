package br.com.ilhasoft.whatsmovie.model.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.ilhasoft.whatsmovie.R;
import br.com.ilhasoft.whatsmovie.model.bean.Filme;
import br.com.ilhasoft.whatsmovie.utils.Urls;
import cz.msebera.android.httpclient.Header;

/**
 * Created by pablo on 7/17/17.
 * Classe de request no webservice
 */

public class FilmeTask {

    /**
     * metodo que faz get sobre um filme passado por parametro
     *
     * @param filme
     */
    public void getFilme(final Context context, String filme) {

        final ProgressDialog progressDialog = ProgressDialog.show(context, context.getResources().getString(R.string.progress_aguarde),
                context.getResources().getString(R.string.progress_conectando), true, true);
        progressDialog.setCancelable(false);


        AsynsHttpClient.get(Urls.URL_LISTAR_FILME_INICIO + filme + Urls.URL_LISTAR_FILME_FIM, null, new JsonHttpResponseHandler() {

            @Override
            public void onStart() {
                progressDialog.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // called when response HTTP status is "200 OK"
                //Log.d("response", "" + response.toString());

                progressDialog.setMessage(context.getResources().getString(R.string.progress_recebendo));

                try {

                    if (response.get("Response").equals("False")) {
                        Toast.makeText(context, "Filme não encontrado", Toast.LENGTH_LONG).show();
                    } else if (!Filme.find(Filme.class, "title = ?", response.get("Title").toString()).isEmpty()) {
                        Toast.makeText(context, "Filme já estava cadastrado", Toast.LENGTH_LONG).show();
                    } else {

                        Filme filme = new Filme();
                        filme.setTitle(response.get("Title").toString());
                        filme.setYear(response.get("Year").toString());
                        filme.setReleased(response.get("Released").toString());
                        filme.setRuntime(response.get("Runtime").toString());
                        filme.setGenre(response.get("Genre").toString());
                        filme.setPlot(response.get("Plot").toString());
                        filme.setAwards(response.get("Awards").toString());
                        filme.setPoster(response.get("Poster").toString());
                        filme.setImdbRating(response.get("imdbRating").toString());
                        filme.setProduction(response.get("Production").toString());
                        filme.setWebsite(response.get("Website").toString());

                        Filme.save(filme);

                        context.sendBroadcast(new Intent("listarFilmes"));

                    }

                    progressDialog.dismiss();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });
    }

}
