package br.com.livrokotlin.exemplos

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {


    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( R.layout.activity_main )

        val exemplos = listOf<String>("Notificações", "Permissões", "Geo Localização")

        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, exemplos)

        list_view.adapter = adapter


        list_view.setOnItemClickListener { adapterView, view, i, l ->


            when(i){
                0 -> startActivity<NotificacoesActivity>()
                1 -> startActivity<PermissoesActivity>()
                2 -> startActivity<GeoLocalizacaoActivity>()
            }

        }


    }


}
