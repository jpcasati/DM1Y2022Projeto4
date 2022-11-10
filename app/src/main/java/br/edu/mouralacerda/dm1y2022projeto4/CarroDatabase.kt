package br.edu.mouralacerda.dm1y2022projeto4

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Carro::class], version = 1)
abstract class CarroDatabase : RoomDatabase() {

    abstract fun carroDao(): CarroDAO

    companion object {

        private var database: CarroDatabase? = null
        private val DATABASE = "CarroDB"

        fun getInstance(context: Context): CarroDatabase {

            if (database == null)
                database = criaBanco(context)

            return database!!
        }

        private fun criaBanco(context: Context): CarroDatabase {
            return Room.databaseBuilder(context, CarroDatabase::class.java, DATABASE)
//                .allowMainThreadQueries()
                .build()
        }
    }
}