package com.example.findag.agenda3pantallas;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Borrar extends ActionBarActivity
{
    // Definimos el TextView en el que insertaremos el contacto.
    TextView txvContacto;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar);

        // Recogemos el objeto txvContacto
        txvContacto = (TextView) findViewById(R.id.txvContacto);

        // Recogemos el Intent en un objeto de tipo Contactos.
        final Contactos contacto = (Contactos) getIntent().getSerializableExtra("borrando");

        // Insertamos el nombre del contacto en nuestro txvContacto
        txvContacto.setText(contacto.getNombre().toString());

        //Definimos e implementamos los dos botones
        Button btnSi = (Button) findViewById(R.id.btnSi);
        Button btnNo = (Button) findViewById(R.id.btnNo);

        //Definimos los onclick
        btnSi.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent borrado = new Intent();
                Contactos ContacBorra = new Contactos("","");

                borrado.putExtra("si", ContacBorra);
                setResult(RESULT_OK, borrado);

                showToast(0);
                finish();
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent borrado = new Intent();
                Contactos ContacBorra = new Contactos(contacto.getNombre(),contacto.getTelefono());

                borrado.putExtra("si", ContacBorra);
                setResult(RESULT_OK, borrado);

                showToast(1);
                finish();

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_borrar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    // Este metodo recibira un entero que dependiendo del valor que lleve conseguiremos que se nos muestre una Toast con uno u otro mensaje.
    public void showToast(int sino)
    {
        Context context = getApplicationContext();
        int duracion = Toast.LENGTH_SHORT;
        CharSequence texto = null;

        if(sino == 1)
        {
            texto = "Contacto sin borrar";
        }
        else
        {
            texto = "Contacto borrado";
        }

        Toast toast = Toast.makeText(context,texto,duracion);

        toast.show();
    }
}
