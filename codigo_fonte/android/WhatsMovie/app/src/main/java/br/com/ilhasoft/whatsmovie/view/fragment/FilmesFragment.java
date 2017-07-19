package br.com.ilhasoft.whatsmovie.view.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.ilhasoft.whatsmovie.R;
import br.com.ilhasoft.whatsmovie.model.bean.Filme;
import br.com.ilhasoft.whatsmovie.model.dao.FilmeDAO;
import br.com.ilhasoft.whatsmovie.presenter.FilmePresenter;
import br.com.ilhasoft.whatsmovie.view.adapter.FilmesAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by pablo on 7/17/17.
 * tela que mostra a listagem de filmes
 */

public class FilmesFragment extends GenericFragment {

    private String categoria;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private FilmePresenter filmePresenter;
    private FilmesAdapter adapter;

    //metodo que executa quando receber os dados da api e atualiza a lista
    private BroadcastReceiver broadcastReceiver_listarFilmes = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            adapter = new FilmesAdapter(getContext(), filmePresenter.getDao().listAll(), null);
            recyclerView.setAdapter(adapter);

        }
    };

    // Método para instanciar esse fragment pela categoria se tiver.
    public static FilmesFragment newInstance(String categoria) {
        Bundle args = new Bundle();
        args.putString("categoria", categoria);
        FilmesFragment f = new FilmesFragment();
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("cicloVida", "onCreate");
        if (getArguments() != null) {
            // Lê o tipo dos argumentos.
            this.categoria = getArguments().getString("categoria");
        }
        getActivity().registerReceiver(broadcastReceiver_listarFilmes, new IntentFilter("listarFilmes"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("cicloVida", "onCreateView");
        View view = inflater.inflate(R.layout.fragment_filmes, container, false);
        ButterKnife.bind(this, view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("cicloVida", "onActivityCreated");
        taskFilmes();

    }


    private void taskFilmes() {

        filmePresenter = new FilmePresenter(getContext(), Filme.class, new FilmeDAO(getContext(), Filme.class));
        adapter = new FilmesAdapter(getContext(), filmePresenter.getDao().listAll(), null);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getContext().unregisterReceiver(broadcastReceiver_listarFilmes);
    }

}
