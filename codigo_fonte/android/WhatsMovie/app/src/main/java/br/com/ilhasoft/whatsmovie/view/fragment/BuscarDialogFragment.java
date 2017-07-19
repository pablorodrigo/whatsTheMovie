package br.com.ilhasoft.whatsmovie.view.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.TextView;

import br.com.ilhasoft.whatsmovie.R;
import br.com.ilhasoft.whatsmovie.model.task.FilmeTask;

/**
 * Created by pablo on 7/19/17.
 * mostrar caixa de texto para buscar algum filme
 */

public class BuscarDialogFragment extends DialogFragment {

    EditText editText_buscar;

    // Método utilitário para mostrar o dialog
    public static void showDialog(android.support.v4.app.FragmentManager fm) {
        FragmentTransaction ft = fm.beginTransaction();
        Fragment prev = fm.findFragmentByTag("dialog_buscar");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        new BuscarDialogFragment().show(ft, "dialog_buscar");
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Infla o layout
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        editText_buscar = (EditText) inflater.inflate(R.layout.fragment_dialog_buscar, null);

        editText_buscar.setMovementMethod(new LinkMovementMethod());
        // Cria o dialog customizado
        return new AlertDialog.Builder(getActivity())
                .setTitle("Cadastrar Filme")
                .setView(editText_buscar)
                .setNeutralButton("Cancelar", null)
                .setPositiveButton("Buscar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                new FilmeTask().getFilme(getContext(), editText_buscar.getText().toString());
                                dialog.dismiss();
                            }
                        }
                )
                .create();
    }

}
