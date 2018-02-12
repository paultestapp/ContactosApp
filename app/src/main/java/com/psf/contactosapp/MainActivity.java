package com.psf.contactosapp;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Resources resources;

    private EditText tie_nombres;
    private EditText tie_telefono;
    private EditText tie_email;
    private EditText tie_descripcion;
    private DatePicker dp_nacimiento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resources = getResources();

        Toolbar mainToolBar = findViewById(R.id.main_actionbar);
        setSupportActionBar(mainToolBar);

        setViews();
    }

    public void setViews() {
        tie_nombres = findViewById(R.id.tie_nombres);
        tie_telefono = findViewById(R.id.tie_telefono);
        tie_email = findViewById(R.id.tie_email);
        tie_descripcion = findViewById(R.id.tie_descripcion);
        dp_nacimiento = findViewById(R.id.dp_nacimiento);

    }

    public void btnNextAction(View view) {
        Intent iConfirmacion = new Intent(this, ConfirmacionActivity.class);

        iConfirmacion.putExtra(resources.getString(R.string.tab_nombres), tie_nombres.getText().toString());
        iConfirmacion.putExtra(resources.getString(R.string.tab_telefono), tie_telefono.getText().toString());
        iConfirmacion.putExtra(resources.getString(R.string.tab_email), tie_email.getText().toString());
        iConfirmacion.putExtra(resources.getString(R.string.tab_descripcion), tie_descripcion.getText().toString());

        String fecha = String.valueOf(dp_nacimiento.getDayOfMonth()) + '/' +
                       String.valueOf(dp_nacimiento.getMonth()+1) + '/' +
                       String.valueOf(dp_nacimiento.getYear());

        iConfirmacion.putExtra(resources.getString(R.string.tab_nacimiento), fecha);

        startActivity(iConfirmacion);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        preloadData();
    }

    private void preloadData() {
        Bundle extra = getIntent().getExtras();
        if(extra != null){
            try {
                tie_nombres.setText(extra.getString(resources.getString(R.string.tab_nombres)));
                tie_telefono.setText(extra.getString(resources.getString(R.string.tab_telefono)));
                tie_email.setText(extra.getString(resources.getString(R.string.tab_email)));
                tie_descripcion.setText(extra.getString(resources.getString(R.string.tab_descripcion)));

                String[] fecha = extra.getString(resources.getString(R.string.tab_nacimiento)).split("/");
                int day = Integer.parseInt(fecha[0]);
                int mon = Integer.parseInt(fecha[1])-1;
                int year = Integer.parseInt(fecha[2]);
                dp_nacimiento.updateDate(year, mon, day);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
