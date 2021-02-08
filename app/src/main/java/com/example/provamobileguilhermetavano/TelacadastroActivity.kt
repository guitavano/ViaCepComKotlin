package com.example.provamobileguilhermetavano

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_telacadastro.*


class TelacadastroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_telacadastro)


        cadastrar.setOnClickListener(){

            if(senhaCad1.text.toString() == senhaCad2.text.toString()){
                if(senhaCad1.text.toString() != "" && senhaCad2.text.toString() != "" && usuarioCad.text.toString() != "" ){
                    Thread(Runnable {
                        var usuario = Usuario(0, usuarioCad.text.toString(), senhaCad1.text.toString())
                        var db : DataBase
                        db = Room.databaseBuilder(applicationContext, DataBase::class.java, "banco").build()
                        var usuarios = db.usuarioDao().verifyUser(usuarioCad.text.toString())
                        if(usuarios == null){
                            db.usuarioDao().insertCad(usuario)
                            runOnUiThread{
                                cadastra()
                            }
                        }
                        else{
                            runOnUiThread{
                            Toast.makeText(applicationContext, "Nome indisponível", Toast.LENGTH_SHORT).show()
                            }
                        }

                    }).start()
                }
                else{
                    Toast.makeText(applicationContext, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(applicationContext, "Senhas diferentes", Toast.LENGTH_SHORT).show()
            }

        }


    }

    fun cadastra(){
        Toast.makeText(applicationContext, "Cadastrado com sucesso!", Toast.LENGTH_LONG).show()
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
    }

}