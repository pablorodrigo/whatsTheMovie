package br.com.ilhasoft.whatsmovie.view.activity;

import android.os.Bundle;

import br.com.ilhasoft.whatsmovie.R;
import br.com.ilhasoft.whatsmovie.view.fragment.BuscarDialogFragment;
import br.com.ilhasoft.whatsmovie.view.fragment.FilmesFragment;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends GenericActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setUpToolbar();
        setupNavDrawer();
        //new FilmeTask().getFilme("The+Avengers");
        replaceFragment(new FilmesFragment());
    }

    @OnClick(R.id.fab)
    public void submit() {
        BuscarDialogFragment.showDialog(getSupportFragmentManager());
    }

}
