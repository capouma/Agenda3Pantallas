package com.example.findag.agenda3pantallas;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity3 extends Activity {

    // Definimos los dos EditText que usaremos(nombre, y telefono)
    EditText edtNombre;
    EditText edtTelefono;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity3);

        //Recogemos el objeto tanto del edtNombre como de edtTelefono
        edtNombre = (EditText) findViewById(R.id.edtNombreP3);
        edtTelefono = (EditText) findViewById(R.id.edtTelefonoP3);

        // Recogemos el Intent en un objeto de tipo Contactos.
        Contactos contacto = (Contactos) getIntent().getSerializableExtra("contactoEdit");

        // Agregamos el contenido de getNombre en edtNombre y el de getTelefono en edtTelfono
        edtNombre.setText(contacto.getNombre());
        edtTelefono.setText(contacto.getTelefono());

        // Recogemos el objeto del boton editar
        Button btnEditar = (Button) findViewById(R.id.btnEditarP3);

        // Definimos el onClick de nuestro btnEditar
        btnEditar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent modificado = new Intent();

                Contactos editado = new Contactos(edtNombre.getText().toString(), edtTelefono.getText().toString());

                modificado.putExtra("contactoEditado", editado);
                setResult(RESULT_OK, modificado);

                showToast();

                finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity3, menu);
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
    public void showToast()
    {
        Context context = getApplicationContext();
        int duracion = Toast.LENGTH_SHORT;
        CharSequence texto;

        texto = getResources().getString(R.string.modificado);
        Toast toast = Toast.makeText(context, texto, duracion);

        toast.show();
    }
}
