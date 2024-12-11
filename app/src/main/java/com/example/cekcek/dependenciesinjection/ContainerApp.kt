package com.example.activity10.dependenciesinjection

import android.content.Context
import com.example.activity10.data.database.KrsDatabase
import com.example.activity10.repository.LocalRepositoryMhs
import com.example.activity10.repository.RepositoryMhs

interface InterfaceContainerApp {
    val repositoryMhs: RepositoryMhs
}

class ContainerApp(private val context: Context) : InterfaceContainerApp {
    override val repositoryMhs: RepositoryMhs by lazy {
        LocalRepositoryMhs(KrsDatabase.getDatabase(context).mahaiswaDao())
    }
}