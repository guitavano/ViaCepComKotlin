package com.example.provamobileguilhermetavano

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()
class Usuario(@PrimaryKey(autoGenerate = true) var id: Int = 0,
              @ColumnInfo(name = "user") var user: String?,
              @ColumnInfo(name = "senha") var senha: String?){
}