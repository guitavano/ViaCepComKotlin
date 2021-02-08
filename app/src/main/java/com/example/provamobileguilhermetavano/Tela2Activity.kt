package com.example.provamobileguilhermetavano

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_tela2.*

class Tela2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela2)

        val nomeRecuperado = intent.getStringExtra("usuarioWelcome")
        usuarioRecebido.text = "Olá $nomeRecuperado"

        botaoIntTela3.setOnClickListener(){
            val intent = Intent(applicationContext, Tela3Activity::class.java)
            startActivity(intent)
        }
    }
}