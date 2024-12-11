package com.example.cekcek

import android.app.Application
import com.example.activity10.dependenciesinjection.ContainerApp

class KrsApp : Application() {
    // Fungsinya untuk menyimpan instance ContainerApp
    lateinit var containerApp: ContainerApp

    override fun onCreate() {
        super.onCreate()
        //Membuat instance ContainerApp
        containerApp =  ContainerApp(this)
        // instance adalah object yang dibuat dari class
    }
}