package br.edu.mouralacerda.dm1y2022projeto4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import br.edu.mouralacerda.dm1y2022projeto4.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    var i = 1

    private val b by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(b.root)

        b.btnNovoCarro.setOnClickListener {

            startActivity(Intent(this, NovoCarro::class.java))

        }

        b.lstCarro.setOnItemLongClickListener { adapterView, view, i, l ->

            val c: Carro = adapterView.adapter.getItem(i) as Carro

            CoroutineScope(Dispatchers.IO).launch {
                // executa paralelo à UI thread
                CarroDatabase.getInstance(this@MainActivity).carroDao().deletar(c)

                withContext(Dispatchers.Main) {
                    // executa na UI thread
                    atuaizarLista()
                }
            }

            true
        }
    }

    override fun onResume() {
        super.onResume()

        atuaizarLista()

    }

    fun atuaizarLista() {


        var carros: List<Carro>

        CoroutineScope(Dispatchers.IO).launch {
            // executa paralelo à UI thread
            carros = CarroDatabase.getInstance(this@MainActivity).carroDao().listar()

            withContext(Dispatchers.Main) {
                // executa na UI thread
                b.lstCarro.adapter = ArrayAdapter(
                    this@MainActivity,
                    android.R.layout.simple_list_item_1,
                    carros
                )
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_principal, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.menuOrdenarValor) {

            var lista: List<Carro>? = null

            if(Math.floorMod(i,2) == 0) {
                lista = CarroDatabase.getInstance(this).carroDao().listarPorPrecoDesc()

            } else {
                lista = CarroDatabase.getInstance(this).carroDao().listarPorPrecoAsc()
            }

            b.lstCarro.adapter = ArrayAdapter (this,
                android.R.layout.simple_list_item_1,
                lista
            )
            i++

        }

        return super.onOptionsItemSelected(item)
    }
}