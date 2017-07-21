package br.com.ilhasoft.whatsmovie.view.activity;

import android.os.Bundle;

import org.parceler.Parcels;

import br.com.ilhasoft.whatsmovie.R;
import br.com.ilhasoft.whatsmovie.model.bean.Filme;
import br.com.ilhasoft.whatsmovie.view.fragment.FilmeDetalheFragment;

/**
 * Created by pablo on 7/20/17.
 */

public class FilmeDetalheActivity extends GenericActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_filme);
        setUpToolbar();
        Filme filme = Parcels.unwrap(getIntent().getParcelableExtra("filme"));
        getSupportActionBar().setTitle(filme.getTitle().toUpperCase());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Adiciona o fragment no layout
        if (savedInstanceState == null) {
            // Cria o fragment com o mesmo Bundle (args) da intent
            FilmeDetalheFragment frag = new FilmeDetalheFragment();
            frag.setArguments(getIntent().getExtras());
            // Adiciona o fragment no layout
            getSupportFragmentManager().beginTransaction().add(R.id.container, frag).commit();
        }
    }

}
