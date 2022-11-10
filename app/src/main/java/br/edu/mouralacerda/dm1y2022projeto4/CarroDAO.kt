package br.edu.mouralacerda.dm1y2022projeto4

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CarroDAO {

    @Insert
    fun salvar(carro: Carro)

    @Delete
    fun deletar(carro: Carro)

    @Query("SELECT * FROM Carro")
    fun listar(): List<Carro>

    @Query("SELECT * FROM Carro ORDER BY Carro.valor")
    fun listarPorPrecoAsc(): List<Carro>

    @Query("SELECT * FROM Carro ORDER BY Carro.valor DESC")
    fun listarPorPrecoDesc(): List<Carro>

}