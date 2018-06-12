package br.com.livrokotlin.exemplos

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_geo_localizacao.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.locationManager
import org.jetbrains.anko.okButton

class GeoLocalizacaoActivity : AppCompatActivity() {

    val ID_REQUISICAO_FINE_LOCATION = 101;

    lateinit var locationListener:LocationListener

    class MyLocationListener : LocationListener{

        override fun onLocationChanged(location: Location?) {
        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

        }

        override fun onProviderDisabled(provider: String?) {

        }

        override fun onProviderEnabled(provider: String?) {

        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_geo_localizacao)

        /*
          Utilizando a biblioteca anko não é necessário instanciar a variável locationManager
          porque ela já vem adicionada como extensão da classe Context
          Então não é necessário a instancia dessa variável, ela pode ser usada diretamente

          val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        */

        locationListener = MyLocationListener()

        btn_localizacao.setOnClickListener {

           requisitarLocalizacao()
        }

        btn_parar.setOnClickListener {

            locationManager.removeUpdates(locationListener);
        }

    }


    fun requisitarLocalizacao(){

        val permissao = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)

        if(permissao == PackageManager.PERMISSION_GRANTED){

            Log.d("requisitarLocalizacao","IF requisitarLocalizacao")

            val tempoAtualizacao:Long = 0
            val distanciaAtualizacao:Float = 0f

            //Obtendo dados de localização da rede
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                    tempoAtualizacao ,
                    distanciaAtualizacao,
                    locationListener
            )

            //Obtendo dados de localização do GPS
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    tempoAtualizacao ,
                    distanciaAtualizacao,
                    locationListener
            )

            /*
            //Obtendo dados do provedor passivo
            locationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER,
                    tempoAtualizacao ,
                    distanciaAtualizacao,
                    locationListener
            )*/



        }else{
            Log.d("requisitarLocalizacao","ELSE requisitarLocalizacao")


            //Permissão não está concedida
            ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    ID_REQUISICAO_FINE_LOCATION)

        }



    }



    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == ID_REQUISICAO_FINE_LOCATION){

            if(grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

               requisitarLocalizacao()

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
