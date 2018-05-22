package br.com.livrokotlin.exemplos

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_permissoes.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.okButton

class PermissoesActivity : AppCompatActivity() {


    val ID_REQUISICAO_READ_CONTACTS = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permissoes)


        btn_solicitar_permissao.setOnClickListener {


            if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                    == PackageManager.PERMISSION_GRANTED){

                //Permissão concedida
                alert {
                    message = "Permissão concedida"
                    okButton {  }
                }.show()

            }else{

                //Permissão não está concedida

                ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.READ_CONTACTS),
                        ID_REQUISICAO_READ_CONTACTS)

            }


        }



    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == ID_REQUISICAO_READ_CONTACTS){

            //Sei que aqui é a resposta da requisição de leitura de contatos feita anteriormente
            if(grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                //A permissão foi concedida, o aplciativo pode utilizar o recurso
                alert {
                    message = "Permissão concedida"
                    okButton {  }
                }.show()

            }else{

                //A permissão não foi concedida, aqui você deverá desabilitar a funcionalidade que utiliza tal recurso
                alert {
                    message = "Permissão não concedida"
                    okButton {  }
                }.show()
            }




        }

    }



}
