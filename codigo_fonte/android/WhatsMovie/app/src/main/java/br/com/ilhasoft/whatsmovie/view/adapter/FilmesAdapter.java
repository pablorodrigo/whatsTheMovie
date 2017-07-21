package br.com.ilhasoft.whatsmovie.view.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.ilhasoft.whatsmovie.R;
import br.com.ilhasoft.whatsmovie.model.bean.Filme;

/**
 * Created by pablo on 7/18/17.
 * Configuração do recylerview de filmes
 */

public class FilmesAdapter extends RecyclerView.Adapter<FilmesAdapter.FilmesViewHolder> {

    private Context context;
    private List<Filme> listaFilmes;
    private FilmeOnClickListener eventoOnClickListener;

    public FilmesAdapter(Context context, List<Filme> listaFilmes, FilmeOnClickListener eventoOnClickListener) {
        this.context = context;
        this.listaFilmes = listaFilmes;
        this.eventoOnClickListener = eventoOnClickListener;
    }

    @Override
    public FilmesViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_filme, viewGroup, false);
        return new FilmesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final FilmesViewHolder holder, final int position) {
        // Atualiza a view
        Filme filme = listaFilmes.get(position);
        holder.tv_tituloFilme.setText(filme.getTitle().toUpperCase());
        holder.progress.setVisibility(View.VISIBLE);
        // Faz o download do poster e mostra o ProgressBar
        Picasso.with(context).load(filme.getPoster()).fit().into(holder.imageView_poster,
                new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        holder.progress.setVisibility(View.GONE); // download ok
                    }

                    @Override
                    public void onError() {
                        holder.imageView_poster.setImageResource(R.drawable.no_image);
                        holder.progress.setVisibility(View.GONE);
                    }
                });
        // Click
        if (eventoOnClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    eventoOnClickListener.onClickFilme(holder.itemView, position, listaFilmes);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return this.listaFilmes != null ? this.listaFilmes.size() : 0;
    }

    public interface FilmeOnClickListener {
        void onClickFilme(View view, int idx, List<Filme> listaFilmes);
    }

    // ViewHolder com as views
    static class FilmesViewHolder extends RecyclerView.ViewHolder {
        TextView tv_tituloFilme;
        ImageView imageView_poster;
        ProgressBar progress;
        CardView cardView;

        FilmesViewHolder(View view) {
            super(view);
            // Cria as views para salvar no ViewHolder
            tv_tituloFilme = (TextView) view.findViewById(R.id.tv_tituloFilme);
            imageView_poster = (ImageView) view.findViewById(R.id.imageView_poster);
            progress = (ProgressBar) view.findViewById(R.id.progressImg);
            cardView = (CardView) view.findViewById(R.id.cardView_filme);
        }
    }

}
