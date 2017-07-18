package br.com.ilhasoft.whatsmovie.presenter;

import android.content.Context;
import android.util.Log;

import java.io.Serializable;
import java.util.List;

import br.com.ilhasoft.whatsmovie.R;
import br.com.ilhasoft.whatsmovie.model.bean.GenericBean;
import br.com.ilhasoft.whatsmovie.model.dao.GenericDAO;

/**
 * Created by pablo on 7/17/17.
 */

public class GenericPresenter<T extends GenericBean, E extends GenericDAO<T>> implements Serializable {

    private Class<T> classe;
    private Context context;
    private E dao;

    public GenericPresenter(Context context, Class<T> classe, E dao) {
        this.classe = classe;
        this.context = context;
        this.dao = dao;
    }


    /**
     * Metodo para cadastrar e alterar os dados de entidades
     *
     * @param t Objeto a ser persistido
     */
    public void salvar(T t) {
        try {
            dao.save(t);
        } catch (Exception e) {
            Log.d("SaveError", context.getResources().getString(R.string.salvarDadoErrorMenssagem));
        }
    }

    /**
     * Exclusao de registros
     *
     * @param t Objeto a ser excluido
     */
    public void deletar(T t) {
        try {
            dao.delete(t);
        } catch (Exception e) {
            Log.d("deleteError", context.getResources().getString(R.string.deletarDadoErrorMessage));
        }
    }

    public GenericDAO<T> getDao() {
        return dao;
    }

    public List<T> filtrar(String query, String queryFieldName, String orderByFieldName) {
        return dao.filtrar(query, queryFieldName, orderByFieldName);
    }

}
