package br.com.ilhasoft.whatsmovie.model.dao;

import android.content.Context;
import android.util.Log;

import com.orm.SugarRecord;

import java.io.Serializable;
import java.util.List;

import br.com.ilhasoft.whatsmovie.R;
import br.com.ilhasoft.whatsmovie.model.bean.GenericBean;

/**
 * Created by pablo on 7/17/17.
 */

public class GenericDAO<T extends GenericBean> implements Serializable {

    private Class<T> classe;
    private Context context;

    public GenericDAO(Context context, Class<T> classe) {
        this.classe = classe;
        this.context = context;
    }

    /**
     * Metodo para cadastrar entidade no banco de dados do dispositivo
     */
    public void save(T t) {
        try {
            SugarRecord.save(t);
        } catch (Exception e) {
            Log.d("SaveError", context.getResources().getString(R.string.salvarDadoErrorMenssagem));
        }

    }


    /**
     * Metodo para deletar entidade no banco de dados do dispositivo
     */
    public void delete(T t) {
        try {
            SugarRecord.delete(t);
        } catch (Exception e) {
            Log.d("deleteError", context.getResources().getString(R.string.deletarDadoErrorMessage));
        }
    }

    /**
     * Metodo para buscar 1 entidade no banco de dados por ID
     */
    public T findById(Long id) {
        return SugarRecord.findById(classe, id);
    }

    /**
     * Metodo para listar todas as entidades do banco de dados
     */
    public List<T> listAll() {
        List<T> lista = SugarRecord.listAll(classe);
        return lista;
    }

    /**
     * Metodo para listar com filtro todas as entidades do banco de dados
     */
    public List<T> filtrar(String query, String queryFieldName, String orderByFieldName) {
        return SugarRecord.find(classe, "upper(" + queryFieldName + ") like upper(?)",
                new String[]{"%" + query + "%"}, null, orderByFieldName, null);
    }

}
