package br.com.livrokotlin.calculadoradebitcoin

/**
 * Created by kassianoresende on 23/03/2018.
 */

class Calc{

    companion object {
        //variavel de instancia
        private var instance: Calc? = null

        //método que verifica se a instancia é nula
        fun obterInstancia(): Calc {
            if (instance == null) {
                instance = Calc()
            }
            return instance!!
        }
    }

    fun soma(a: Int, b:Int) = a + b
}

