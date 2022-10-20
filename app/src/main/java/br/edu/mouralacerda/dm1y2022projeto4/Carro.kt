package br.edu.mouralacerda.dm1y2022projeto4

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Carro(
    val placa: String,
    val marca: String,
    val modelo: String,
    val ano: Int,
    val valor: Double,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
) {

    override fun toString(): String {
        return "$marca / $modelo / $ano / R$ $valor"
    }

}