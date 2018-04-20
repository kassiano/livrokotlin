package br.com.livrokotlin.helloworld

/**
 * Created by kassianoresende on 16/01/2018.
 */
open class Carro {

    var cor: String = ""
    var modelo:String = ""

    fun acelerar(){
        println("Acelerando")
    }

    fun frear(){
        println("freando")
    }

}

class CarroEspecial : Carro(){

    fun fazerDrift(){
        //implementação
    }
}

data class Usuario(var nome:String, var email:String, var senha:String)