package br.com.ilhasoft.whatsmovie.view.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;

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
        replaceFragment(new FilmesFragment());
    }

    @OnClick(R.id.fab)
    public void submit() {
        BuscarDialogFragment.showDialog(getSupportFragmentManager());
    }

    /**
     * verificar o click do button padrao back e perguntar se o usuario quer realmente sair do app
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            new AlertDialog.Builder(this)
                    .setIcon(R.drawable.ic_alert_black_24dp)
                    .setTitle(R.string.alert_sair)
                    .setMessage(R.string.alert_mensagem)
                    .setPositiveButton(R.string.alert_sim, new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MainActivity.this.finish();
                        }

                    })
                    .setNegativeButton(R.string.alert_nao, null)
                    .show();

            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

}
