package com.example.provamobileguilhermetavano

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadData()

        login.setOnClickListener(){
            consulta()
        }

        cadastro.setOnClickListener(){
            val intent = Intent(applicationContext, TelacadastroActivity::class.java)
            startActivity(intent)
        }

    }

    fun consulta(){
        if(usuario.text.toString() != "" && senha.text.toString() != ""){
            Thread(Runnable {
                    var db : DataBase
                    db = Room.databaseBuilder(applicationContext, DataBase::class.java, "banco").build()
                    var verificacao = db.usuarioDao().verifyPassword(usuario.text.toString())
                    db.close()
                runOnUiThread {
                    if (verificacao != null) {
                        if (verificacao.user == usuario.text.toString() && verificacao.senha == senha.text.toString()) {
                            saveData()
                            telaLogin()
                        } else {
                            Toast.makeText(
                                applicationContext,
                                "Dados incorretos",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        Toast.makeText(applicationContext, "Não encontrado", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }).start()
        }
        else{
            Toast.makeText(applicationContext, "Preencha todos os campos", Toast.LENGTH_SHORT).show()

        }
    }

    private fun saveData(){

        val insertedUser = usuario.text.toString()
        val insertedSenha = senha.text.toString()

        val sharedPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply{
            putString("CHAVE 1", insertedUser)
            putString("CHAVE 2", insertedSenha)
        }.apply()

    }

    private fun loadData(){
        val sharedPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val savedUser = sharedPreferences.getString("CHAVE 1", null)
        val savedSenha = sharedPreferences.getString("CHAVE 2", null)

        usuario.setText(savedUser)
        senha.setText(savedSenha)
    }

    private fun telaLogin() {
        val intent = Intent(applicationContext, Tela2Activity::class.java)
        val params = Bundle()
        val textoDigitado = usuario.text.toString()
        params.putString("usuarioWelcome", textoDigitado)
        intent.putExtras(params)
        startActivity(intent)
    }
}