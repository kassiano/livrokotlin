package br.com.livrokotlin.helloworld

import android.os.Bundle


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val c = Carro()

        //c.teste = "ola"

    }
}
