package br.edu.mouralacerda.dm1y2022projeto4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import br.edu.mouralacerda.dm1y2022projeto4.databinding.ActivityMainBinding

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
    }

    override fun onResume() {
        super.onResume()

        b.lstCarro.adapter = ArrayAdapter (this,
            android.R.layout.simple_list_item_1,
            CarroDatabase.getInstance(this).carroDao().listar()
        )

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