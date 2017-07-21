package br.com.ilhasoft.whatsmovie.view.fragment;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import br.com.ilhasoft.whatsmovie.R;
import br.com.ilhasoft.whatsmovie.model.bean.Filme;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by pablo on 7/20/17.
 * fragment de detalhe do filme
 */

public class FilmeDetalheFragment extends GenericFragment {

    private Filme filme;

    @BindView(R.id.imageView_poster_detalhe)
    ImageView imageView_poster_detalhe;

    @BindView((R.id.textView_descricao))
    TextView textView_descricao;

    @BindView((R.id.textView_dataLancamento))
    TextView textView_dataLancamento;

    @BindView((R.id.textView_genero))
    TextView textView_genero;

    @BindView((R.id.textView_duracao))
    TextView textView_duracao;

    @BindView((R.id.textView_nota))
    TextView textView_nota;

    @BindView((R.id.textView_producao))
    TextView textView_producao;

    @BindView((R.id.textView_premios))
    TextView textView_premios;

    @BindView((R.id.textView_paginaWeb))
    TextView textView_paginaWeb;

    ProgressBar progress;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filme_detalhe, container, false);
        ButterKnife.bind(this, view);
        progress = (ProgressBar) view.findViewById(R.id.progressImg);
        filme = Parcels.unwrap(getArguments().getParcelable("filme"));

        textView_descricao.setText(filme.getPlot());
        textView_dataLancamento.setText(filme.getReleased());
        textView_genero.setText(filme.getGenre());
        textView_duracao.setText(filme.getRuntime());
        textView_nota.setText(filme.getImdbRating());
        textView_producao.setText(filme.getProduction());
        textView_premios.setText(filme.getAwards());
        textView_paginaWeb.setText(filme.getWebsite());

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_frag_filme, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.menu_paginaWeb) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(filme.getWebsite()));
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);

        progress.setVisibility(View.VISIBLE);
        Picasso.with(getContext()).load(filme.getPoster()).fit().into(imageView_poster_detalhe,
                new com.squareup.picasso.Callback() {


                    @Override
                    public void onSuccess() {

                        progress.setVisibility(View.GONE);

                    }

                    @Override
                    public void onError() {
                        imageView_poster_detalhe.setImageResource(R.drawable.no_image);
                        progress.setVisibility(View.GONE);
                    }
                });

    }


}
