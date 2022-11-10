package br.edu.mouralacerda.dm1y2022projeto4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.edu.mouralacerda.dm1y2022projeto4.databinding.ActivityNovoCarroBinding
import kotlinx.coroutines.*

class NovoCarro : AppCompatActivity() {

    private val b by lazy {
        ActivityNovoCarroBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(b.root)

        b.btnSalvarCarro.setOnClickListener {

            val carro = Carro(
                b.edtPlaca.text.toString(),
                b.edtMarca.text.toString(),
                b.edtModelo.text.toString(),
                b.edtAno.text.toString().toInt(),
                b.edtValor.text.toString().toDouble()
            )


            CoroutineScope(Dispatchers.IO).launch {
                // executa paralelo à UI thread
                CarroDatabase.getInstance(this@NovoCarro).carroDao().salvar(carro)

                withContext(Dispatchers.Main) {
                    // executa na UI thread
                    Toast.makeText(this@NovoCarro, "Salvo com sucesso!", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }

        }


    }
}