package br.com.ilhasoft.whatsmovie.view.activity;

import android.os.Bundle;

import br.com.ilhasoft.whatsmovie.R;
import br.com.ilhasoft.whatsmovie.model.task.FilmeTask;
import br.com.ilhasoft.whatsmovie.view.fragment.FilmesFragment;

public class MainActivity extends GenericActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpToolbar();
        setupNavDrawer();
        //new FilmeTask().getFilme("The+Avengers");
        replaceFragment(new FilmesFragment());
    }

}
