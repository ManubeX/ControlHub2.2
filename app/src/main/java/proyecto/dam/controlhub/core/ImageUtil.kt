package proyecto.dam.controlhub.core

import android.content.ContentResolver
import android.content.res.AssetManager
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.provider.MediaStore
import android.provider.MediaStore.Images.Media.getBitmap
import android.util.Log
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import proyecto.dam.controlhub.model.data.UserData
import proyecto.dam.controlhub.ui.login.LoginActivity
import java.io.ByteArrayOutputStream


class ImageUtil (user: UserData){

    private val refText = user.company.replace(" ","_") +
            "/" +
            "workers_profile"+
            "/"+
            user.id
    private val uriImageUSer = user.imageUrl

    private val storage = Firebase.storage


/*    fun uploadImage(){
        var storageRef = storage.reference
        val baos = ByteArrayOutputStream()
        val bitmap = roundImage(uriToBitmap())
        if (bitmap != null) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        }
        val data = baos.toByteArray()
        var uploadTask = storageRef.putBytes(data)
        uploadTask.addOnFailureListener{
            Log.v("imagen", "Error")
        }

    }*/

  /*  fun uriToBitmap(): Bitmap {
        //var bitmap = BitmapFactory.decodeFile(uriImageUSer)
        var source = ImageDecoder.createSource(, uriImageUSer)
        var bitmap =
        return bitmap
    }*/

    fun roundImage(bitmap: Bitmap): Bitmap? {
        val resources = Resources.getSystem()
        var roundedDrawable = RoundedBitmapDrawableFactory.create(resources,bitmap)
        roundedDrawable.cornerRadius = bitmap.width.coerceAtLeast(bitmap.height) / 2.0f

        val bitmapRound = roundedDrawable.bitmap

        return bitmapRound
    }




}