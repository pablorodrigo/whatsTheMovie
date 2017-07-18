package br.com.ilhasoft.whatsmovie.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.ilhasoft.whatsmovie.R;

/**
 * Created by pablo on 7/17/17.
 */

public class FilmesFragment extends GenericFragment {

    private String categoria;

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
        if (getArguments() != null) {
            // Lê o tipo dos argumentos.
            this.categoria = getArguments().getString("categoria");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filmes, container, false);
        TextView text = (TextView) view.findViewById(R.id.text);
        text.setText("Filme " + categoria);
        return view;
    }

}
