package br.com.livrokotlin.exemplos

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        //Criando um objeto de texto
        val texto = TextView(this)
        texto.text = "Hello Kotlin"


        //definindo o conteudo da tela
        setContentView( R.layout.activity_main )



        findViewById<Button>(R.id.btn_login)

    }
}
