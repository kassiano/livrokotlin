package br.com.livrokotlin.calculoaposentadoria

/**
 * Created by kassianoresende on 16/01/2018.
 */

import android.app.Activity
import android.os.Bundle
import android.widget.*


class MainActivity : Activity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //definindo o arquivo de layout
        setContentView(R.layout.activity_main)

        //acessando o spinner
        val spn_sexo = findViewById<Spinner>(R.id.spn_sexo)

        //acessando a caixa de idade
        val txt_idade = findViewById<EditText>(R.id.txt_idade)

        //acessando o botão de calcular
        val btn_calcular = findViewById<Button>(R.id.btn_calcular)

        //acesando o texto de resultado
        val txt_resultado = findViewById<TextView>(R.id.txt_resultado)

        //preenchendo os valores no spinner
        spn_sexo.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,
                listOf("masculino", "feminino"))

        btn_calcular.setOnClickListener {

            //capturando o sexo selecionado
            val sexo = spn_sexo.selectedItem as String

            //capturando a idade digitada
            val idade = txt_idade.text.toString().toInt()

            //variavel para guardar o resultado do calculo
            var resultado = 0

            //verificando o sexo da pessoa
            if(sexo == "masculino"){
                resultado = 65 - idade
            }else{
                resultado = 60 - idade
            }

            //Atualizando a tela de acordo com o resultado do calculo
            txt_resultado.text = "Faltam $resultado anos para você se aposentar."

        }

    }

}