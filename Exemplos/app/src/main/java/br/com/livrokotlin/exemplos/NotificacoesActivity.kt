package br.com.livrokotlin.exemplos

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_notificacoes.*

class NotificacoesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notificacoes)

        btn_notificacao.setOnClickListener {

            notificacaoSimples("Título", "Olá, você está sendo notificado")
        }
    }




    fun notificacaoSimples(title:String, message:String){

        val nBuilder = NotificationCompat.Builder(this, "default")

        /*Definindo um icone pequeno, nesse caso estou definindo o proprio icone do App
        que está sendo acessado por `R.mipmap.ic_launcher`*/
        nBuilder.setSmallIcon(R.mipmap.ic_launcher)


        //Definindo o titulo da notificação
        nBuilder.setContentTitle(title)

        //Definindo o conteudo da notificação
        nBuilder.setContentText(message)


        //construindo o objeto Notification
        val notificacao = nBuilder.build()

        //Acessando o serviço de notificação do sistema
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            //criação do objeto
            val channel = NotificationChannel("default", "Canal de notificação teste", NotificationManager.IMPORTANCE_DEFAULT)

            //criar o canal de notificação
            notificationManager.createNotificationChannel(channel)
        }

        //enviando a notificação em si
        notificationManager.notify(1  , notificacao)

    }

}
