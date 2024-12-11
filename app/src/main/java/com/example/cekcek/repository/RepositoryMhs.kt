package com.example.activity10.repository

import com.example.activity10.data.entity.Mahasiswa

interface RepositoryMhs {
    suspend fun insertMhs(mahasiswa: Mahasiswa)
}