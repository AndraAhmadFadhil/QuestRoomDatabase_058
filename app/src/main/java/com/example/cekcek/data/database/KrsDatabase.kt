package com.example.activity10.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.activity10.data.dao.MahasiswaDao
import com.example.activity10.data.entity.Mahasiswa

//Mendefinisikan database dengan tabel mahasiswa
@Database(entities = [Mahasiswa::class], version = 1, exportSchema = false)
abstract class KrsDatabase : RoomDatabase() {

    //Mendefinisikan fungsi untuk mengakses data Mahasiswa
    abstract fun mahaiswaDao(): MahasiswaDao

    companion object{
        @Volatile //Memastikan bahwa nilai variabel Instance selalu sama di semua thread
        private var Instance: KrsDatabase? = null

        fun getDatabase(context: Context): KrsDatabase {
            return (Instance ?: synchronized(this){
                Room.databaseBuilder(
                    context,
                    KrsDatabase::class.java, //Class Database
                    "KrsDatabase" //Nama Database
                )
                    .build().also { Instance = it }
            })
        }
    }
}