package com.example.findag.agenda3pantallas;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Activity2 extends ListActivity
{
    //Definimos un ArrayList que usaremos para recoger el intent.
    ArrayList<Contactos> lista;

    // Variable que contendra la posicion de nuestro contacto a modificar.
    int reEscribe;

    //Definimos nuestra variable static y final que sera nuestro requestCode(esta variable es la que identifica el intent que enviamos).
    private final static int EDITANDO = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity2);

        // Recogemos el intent que mandamos de la Activity1 que contiene nuestro ArrayList en un ArrayList del mismo tipo que creamos en esta activity
        lista = (ArrayList<Contactos>) getIntent().getSerializableExtra("lista");

        //Definimos nuestro adaptador
        setListAdapter(new ArrayAdapter<Contactos>(this, android.R.layout.simple_list_item_1,lista));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity2, menu);
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

    // Creamos el metodo onClickListener de nuestra lista
    public  void onListItemClick(ListView parent, View v, int posicion, long id)
    {
        Intent editando = new Intent(Activity2.this, Activity3.class);
        editando.putExtra("contactoEdit", lista.get(posicion));
        reEscribe = posicion;
        startActivityForResult(editando, EDITANDO);

    }
    // Con el metodo onActivityResult recogeremos el valor del intent de la Activity3
    protected void onActivityResult (int requestCode, int resultCode, Intent modificado)
    {
        super.onActivityResult(requestCode, resultCode, modificado);

        Contactos editado = (Contactos) modificado.getSerializableExtra("contactoEditado");
        lista.remove(reEscribe);
        lista.add(reEscribe,editado);

        Intent listaFinal = new Intent();
        listaFinal.putExtra("nuevaLista", lista);
        setResult(RESULT_OK, listaFinal);
        finish();
    }

}
