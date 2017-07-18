package br.com.ilhasoft.whatsmovie.model.task;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

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
    public void getFilme(String filme) {
        AsynsHttpClient.get(Urls.URL_LISTAR_FILME_INICIO + filme + Urls.URL_LISTAR_FILME_FIM, null, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // called when response HTTP status is "200 OK"
                //Log.d("response", "" + response.toString());

                Filme filme = new Filme();

                try {

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


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });
    }

}
