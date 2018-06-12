package br.com.livrokotlin.calculadoradebitcoin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.bloco_cotacao.*
import kotlinx.android.synthetic.main.bloco_entrada.*
import kotlinx.android.synthetic.main.bloco_saida.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import java.net.URL
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity() {


    val API_URL = "https://www.mercadobitcoin.net/api/BTC/ticker/"

    var cotacaoBitcoin:Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buscarCotacao()

        //listenner do botão calcular
        btn_calcular.setOnClickListener {
            calcular()
        }
    }



    fun buscarCotacao(){


        //iniciar uma tarefa assincrona
        doAsync {


            //Acessar a API e buscar seu resultado
            val resposta = URL(API_URL).readText()

            //Acessando a cotação da String em Json
            cotacaoBitcoin = JSONObject(resposta).getJSONObject("ticker").getDouble("last")

            //Formatação em moeda
            val f= NumberFormat.getCurrencyInstance(Locale("pt", "br"))
            val cotacaoFormatada = f.format(cotacaoBitcoin)


            uiThread {
                //Atualizando a tela com a cotação atual
                txt_cotacao.setText("$cotacaoFormatada")

            }

        }

    }



    fun calcular(){


        if(txt_valor.text.isEmpty()) {
            txt_valor.error = "Preencha um valor"
            return
        }


        //valor digitado pelo usuario
        val valor_digitado = txt_valor.text.toString()
                .replace(",", ".")
                .toDouble()


        //calculando o resultado
        //caso o valor cotacão seja maior que zero, efetuamos o calculo
        //caso contrario devolvemos 0
        val resultado =   if(cotacaoBitcoin > 0) valor_digitado / cotacaoBitcoin else 0.0


        //atualizando a TextView com o resultado formatado com 8 casas decimais
        txt_qtd_bitcoins.text = "%.8f".format(resultado)


    }


}
