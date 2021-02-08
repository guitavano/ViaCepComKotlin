package com.example.provamobileguilhermetavano

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Usuario::class), version = 1)

abstract class DataBase :  RoomDatabase() {
    abstract fun usuarioDao() : UsuarioDao;
}