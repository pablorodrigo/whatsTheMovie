package br.com.ilhasoft.whatsmovie.presenter;

import android.content.Context;

import br.com.ilhasoft.whatsmovie.model.dao.GenericDAO;

/**
 * Created by pablo on 7/17/17.
 */

public class FilmePresenter extends GenericPresenter {
    public FilmePresenter(Context context, Class classe, GenericDAO dao) {
        super(context, classe, dao);
    }
}
