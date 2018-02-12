package com.psf.contactosapp;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.TextureView;
import android.view.View;
import android.widget.TextView;

import java.io.Serializable;

public class ConfirmacionActivity extends AppCompatActivity {

    private Resources resources;

    private TextView tv_nombres;
    private TextView tv_nacimiento;
    private TextView tv_telefeno;
    private TextView tv_email;
    private TextView tv_descripcion;

    private String nombres;
    private String nacimiento;
    private String telefono;
    private String email;
    private String descripcion;

    Bundle extra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmacion);

        Toolbar mainToolBar = findViewById(R.id.main_actionbar);
        setSupportActionBar(mainToolBar);

        resources = getResources();

        setViews();
        setData();
    }

    /**
     * Asigna los controles respectivos a las variables
     */
    public void setViews() {
        tv_nombres = findViewById(R.id.tv_nombres);
        tv_nacimiento = findViewById(R.id.tv_nacimiento);
        tv_telefeno = findViewById(R.id.tv_telefono);
        tv_email = findViewById(R.id.tv_email);
        tv_descripcion = findViewById(R.id.tv_descripcion);
    }

    /**
     * Asigna los datos respectivos a los controles
     */
    public void setData() {
        extra = getIntent().getExtras();
        if(extra != null){
            try {
                nombres = extra.getString(resources.getString(R.string.tab_nombres));
                nacimiento = extra.getString(resources.getString(R.string.tab_nacimiento));
                telefono = extra.getString(resources.getString(R.string.tab_telefono));
                email = extra.getString(resources.getString(R.string.tab_email));
                descripcion = extra.getString(resources.getString(R.string.tab_descripcion));

                String lNacimiento = resources.getString(R.string.label_nacimiento) + " " + nacimiento;
                String lTelefono = resources.getString(R.string.label_telefono) + " " + telefono;
                String lEmail = resources.getString(R.string.label_email) + " " + email;
                String lDescripcion = resources.getString(R.string.label_descripcion) + " " + descripcion;

                tv_nombres.setText(nombres);
                tv_nacimiento.setText(lNacimiento);
                tv_telefeno.setText(lTelefono);
                tv_email.setText(lEmail);
                tv_descripcion.setText(lDescripcion);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Action del boton editar
     * @param view
     */
    public void btnEditAction(View view) {
        Intent iMain = new Intent(ConfirmacionActivity.this, MainActivity.class);
        if(extra != null) {
            iMain.putExtra(resources.getString(R.string.tab_nombres), nombres);
            iMain.putExtra(resources.getString(R.string.tab_nacimiento), nacimiento);
            iMain.putExtra(resources.getString(R.string.tab_telefono), telefono);
            iMain.putExtra(resources.getString(R.string.tab_email), email);
            iMain.putExtra(resources.getString(R.string.tab_descripcion), descripcion);
        }
        startActivity(iMain);
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent iMain = new Intent(ConfirmacionActivity.this, MainActivity.class);
            startActivity(iMain);
        }
        return super.onKeyDown(keyCode, event);
    }
}
