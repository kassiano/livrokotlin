package br.com.livrokotlin.listadecompras

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream
import java.text.NumberFormat
import java.util.*

/**
 * Created by kassianoresende on 15/02/2018.
 */


val produtosGlobal = mutableListOf<Produto>()


//funcão de extensão para formatar números Double no formato moeda
fun Double.moeda(): String{

    val f = NumberFormat.getCurrencyInstance(Locale("pt", "br"))

    return f.format(this)

}

fun Bitmap.toByteArray(): ByteArray {

    val stream = ByteArrayOutputStream()

    this.compress(android.graphics.Bitmap.CompressFormat.PNG, 0, stream)

    return stream.toByteArray()

}


fun ByteArray.toBitmap() :Bitmap{

    return BitmapFactory.decodeByteArray(this, 0, this.size);
}

