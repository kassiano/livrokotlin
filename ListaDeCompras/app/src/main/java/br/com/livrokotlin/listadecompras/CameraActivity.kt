package br.com.livrokotlin.listadecompras

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import java.io.File


open class CameraActivity : AppCompatActivity()  {

    val COD_REQUISICAO_FOTO = 200
    val COD_PERMISSAO_ESCRITA_ARQUIVO =100
    private var mCurrentPhotoPath =  ""

    var image:ImageView? = null
    var imageBitmap :Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    fun takePicture(imageView : ImageView ){

        image = imageView

        //Ckeck permission
        val permissao = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)

        if(permissao == PackageManager.PERMISSION_GRANTED){
            openCamera()
        }else{

            //Request Permission to Write storage
            ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    COD_PERMISSAO_ESCRITA_ARQUIVO);

        }

    }



    internal fun openCamera(){

        val values = ContentValues(1)
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpg")
        val fileUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)


        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        if(intent.resolveActivity(packageManager) != null) {

            mCurrentPhotoPath = fileUri.toString()
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri)
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION
                    or Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
            startActivityForResult(intent, COD_REQUISICAO_FOTO)
        }
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {


        if (requestCode == COD_PERMISSAO_ESCRITA_ARQUIVO ){

            if(grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                //Permissão concedida, abrir camera
                openCamera()
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == COD_REQUISICAO_FOTO) {
            processarFotoCapturada()
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }


    private fun processarFotoCapturada() {

        val cursor = contentResolver.query(Uri.parse(mCurrentPhotoPath),
                Array(1) {android.provider.MediaStore.Images.ImageColumns.DATA},
                null, null, null)
        cursor.moveToFirst()
        val photoPath = cursor.getString(0)
        cursor.close()
        val file = File(photoPath)
        val uri = Uri.fromFile(file)

        imageBitmap = BitmapFactory.decodeFile(photoPath)

        if( image != null){

            image!!.setImageBitmap(imageBitmap)
        }

    }

}



