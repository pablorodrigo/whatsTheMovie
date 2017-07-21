package br.com.ilhasoft.whatsmovie.model.task;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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

                        //se um dos campos nao existir ou nao tiver nenhum dado relevante, é a setado Nao Disponivel no atributo.
                        filme.setTitle(!response.has("Title") || response.get("Title").toString().equals("N/A") ? "Não Disponível" : response.get("Title").toString());
                        filme.setYear(!response.has("Year") || response.get("Year").toString().equals("N/A") ? "Não Disponível" : response.get("Year").toString());
                        filme.setReleased(!response.has("Released") || response.get("Released").toString().equals("N/A") ? "Não Disponível" : response.get("Released").toString());
                        filme.setRuntime(!response.has("Runtime") || response.get("Runtime").toString().equals("N/A") ? "Não Disponível" : response.get("Runtime").toString());
                        filme.setGenre(!response.has("Genre") || response.get("Genre").toString().equals("N/A") ? "Não Disponível" : response.get("Genre").toString());
                        filme.setPlot(!response.has("Plot") || response.get("Plot").toString().equals("N/A") ? "Não Disponível" : response.get("Plot").toString());
                        filme.setAwards(!response.has("Awards") || response.get("Awards").toString().equals("N/A") ? "Não Disponível" : response.get("Awards").toString());
                        filme.setPoster(!response.has("Poster") || response.get("Poster").toString().equals("N/A") ? "Não Disponível" : response.get("Poster").toString());
                        filme.setImdbRating(!response.has("imdbRating") || response.get("imdbRating").toString().equals("N/A") ? "Não Disponível" : response.get("imdbRating").toString());
                        filme.setProduction(!response.has("Production") || response.get("Production").toString().equals("N/A") ? "Não Disponível" : response.get("Production").toString());
                        filme.setWebsite(!response.has("Website") || response.get("Website").toString().equals("N/A") ? "Não Disponível" : response.get("Website").toString());

                        Filme.save(filme);
                        Toast.makeText(context, R.string.filmeCadastroSucesso, Toast.LENGTH_LONG).show();
                        context.sendBroadcast(new Intent("listarFilmes"));

                    }

                    progressDialog.dismiss();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                progressDialog.dismiss();
                new AlertDialog.Builder(context).setTitle("Error")
                        .setMessage(context.getResources().getString(R.string.progress_falha))
                        .setNeutralButton(R.string.alert_sair, null).show();
                context.sendBroadcast(new Intent("listarFilmes"));
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }

            @Override
            public void onRetry(int retryNo) {
                progressDialog.setTitle(context.getResources().getString(R.string.progress_aguarde) +
                        retryNo +
                        "-5 " +
                        context.getResources().getString(R.string.progress_tentativas));
                // called when request is retried
            }
        });
    }

}
