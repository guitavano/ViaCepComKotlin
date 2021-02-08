package com.example.provamobileguilhermetavano

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_tela3.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import java.io.BufferedReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL


class Tela3Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela3)

        pesquisar.setOnClickListener {

                if (cep.text.toString().length == 8){

                    if(cep.text.toString() != "18010001" && cep.text.toString() != "18010082" && cep.text.toString() != "18013001" && cep.text.toString() != "18055131"){
                        verCep(cep.text.toString())

                    }
                    else{
                        Toast.makeText(applicationContext, "Este CEP está na lista negra", Toast.LENGTH_SHORT).show()
                    }

                }
                else{
                    Toast.makeText(applicationContext, "Digite 8 números", Toast.LENGTH_SHORT).show()
                }
        }
    }

    private fun verCep(cep: String){

        val url = "https://viacep.com.br/ws/$cep/json/"

        doAsync{
            val url = URL(url)
            val urlConnection : HttpURLConnection = url.openConnection() as HttpURLConnection
            urlConnection.connectTimeout = 10000
            val resultado = urlConnection.inputStream.bufferedReader().use(BufferedReader::readText)
            var resultadoJson = JSONObject(resultado)

            uiThread {
                val ruaExibir = resultadoJson.getString("logradouro")
                val bairroExibir = resultadoJson.getString("bairro")
                val cidadeExibir = resultadoJson.getString("localidade")
                val estadoExibir = resultadoJson.getString("uf")

                when {
                    estadoExibir == "MG" -> throw Exception("CEP de MG.")
                    bairroExibir[0].toString().toLowerCase() == "s" -> throw Exception("Bairro começa com S")
                    else -> {
                        rua.setText(ruaExibir)
                        bairro.setText(bairroExibir)
                        cidade.setText(cidadeExibir)
                        estado.setText(estadoExibir)
                    }
                }

            }

        }
    }

}