package com.example.listview

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    var datos:MutableList<String> = mutableListOf()
    lateinit var adaptador:ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        var listado=findViewById<ListView>(R.id.listado)
        var texto=findViewById<TextView>(R.id.textoInvisible)
        if(datos.size==0){
            texto.visibility= View.VISIBLE
            listado.visibility=View.GONE
        }
        else{
            texto.visibility= View.GONE
            listado.visibility=View.VISIBLE
            adaptador=ArrayAdapter<String>(this, R.layout.item, datos)
            listado.setTextFilterEnabled(true)
            listado.adapter=adaptador

            listado.setOnItemClickListener({parent, view, position, id->
                Toast.makeText(this,"se ha eliminado ${datos[position]}",Toast.LENGTH_LONG).show()
                datos.removeAt(position)
                adaptador.notifyDataSetChanged()
            })
        }


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            R.id.action_nuevo ->{
                datos.add("número ${datos.size}")
                adaptador.notifyDataSetChanged()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}