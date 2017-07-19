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
     * @param filme filme a ser cadastrado
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
                        Toast.makeText(context, R.string.filmeNaoEncontrado, Toast.LENGTH_LONG).show();
                    } else if (!Filme.find(Filme.class, "title = ?", response.get("Title").toString()).isEmpty()) {
                        Toast.makeText(context, R.string.filmeJaCadastrado, Toast.LENGTH_LONG).show();
                    } else {

                        Filme filme = new Filme();
                        filme.setTitle(response.has("Title") ? response.get("Title").toString() : "");
                        filme.setYear(response.has("Year") ? response.get("Year").toString() : "Não Disponível");
                        filme.setReleased(response.has("Released") ? response.get("Released").toString() : "Não Disponível");
                        filme.setRuntime(response.has("Runtime") ? response.get("Runtime").toString() : "Não Disponível");
                        filme.setGenre(response.has("Genre") ? response.get("Genre").toString() : "Não Disponível");
                        filme.setPlot(response.has("Plot") ? response.get("Plot").toString() : "Não Disponível");
                        filme.setAwards(response.has("Awards") ? response.get("Awards").toString() : "Não Disponível");
                        filme.setPoster(response.has("Poster") ? response.get("Poster").toString() : "");
                        filme.setImdbRating(response.has("imdbRating") ? response.get("imdbRating").toString() : "Não Disponível");
                        filme.setProduction(response.has("Production") ? response.get("Production").toString() : "Não Disponível");
                        filme.setWebsite(response.has("Website") ? response.get("Website").toString() : "Não Disponível");

                        Filme.save(filme);
                        Toast.makeText(context, R.string.filmeCadastroSucesso, Toast.LENGTH_LONG).show();
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
