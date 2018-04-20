package br.com.livrokotlin.listadecompras

import android.graphics.Bitmap


/**
 * Created by kassianoresende on 05/02/2018.
 */

data class Produto(val id:Int, val nomeProduto:String, val quantidade:Int, val valor:Double , val foto: Bitmap? = null )

