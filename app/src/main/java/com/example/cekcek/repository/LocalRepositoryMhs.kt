package com.example.activity10.repository

import com.example.activity10.data.dao.MahasiswaDao
import com.example.activity10.data.entity.Mahasiswa
import kotlinx.coroutines.flow.Flow

class LocalRepositoryMhs (
    private val mahasiswaDao: MahasiswaDao
    ) : RepositoryMhs {
    override suspend fun insertMhs(mahasiswa: Mahasiswa) {
        mahasiswaDao.insertMahasiswa(mahasiswa)

        //getAllMhs
        fun getAllMhs(): Flow<List<Mahasiswa>>

        //getMhs
        fun getMhs(nim: String): Flow<Mahasiswa>

        //deleteMhs
        suspend fun deleteMhs(mahasiswa: Mahasiswa)

        //updateMhs
        suspend fun updateMhs(mahasiswa: Mahasiswa)
    }
}