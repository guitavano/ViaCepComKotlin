package com.example.provamobileguilhermetavano


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UsuarioDao {

    @Query("SELECT * FROM Usuario WHERE user = :paramNome")
    fun verifyUser(paramNome: String): Usuario

    @Query("SELECT * FROM Usuario WHERE user = :paramNome")
    fun verifyPassword(paramNome: String): Usuario

    @Insert
    fun insertCad(usuario: Usuario)

}
