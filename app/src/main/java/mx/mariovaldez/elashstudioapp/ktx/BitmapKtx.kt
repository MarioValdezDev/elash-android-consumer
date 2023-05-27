package mx.mariovaldez.elashstudioapp.ktx

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Matrix
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun bitmapToFile(bitmap: Bitmap, fileName: String, context: Context): File {
    lateinit var file: File
    return try {
        file = File(context.cacheDir, fileName)
        if (file.exists()) {
            file.delete()
        }
        file.createNewFile()
        // Convert bitmap to byte array
        val bos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos) // YOU can also save it in JPEG
        val bitmapData = bos.toByteArray()

        // write the bytes in file
        val fos = FileOutputStream(file)
        fos.write(bitmapData)
        fos.flush()
        fos.close()
        file
    } catch (e: Exception) {
        e.printStackTrace()
        file // it will return null
    }
}

fun Bitmap.flip(): Bitmap {
    val matrix = Matrix().apply { postScale(-1f, 1f, width / 2f, width / 2f) }
    return Bitmap.createBitmap(this, 0, 0, width, height, matrix, true)
}

@Throws(IOException::class)
fun createImageFile(context: Context): File {

    val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
    val imagesFolder = File(context.cacheDir, "images")
    imagesFolder.mkdirs()
    return File.createTempFile(
        "address_proof_${timeStamp}_",
        ".jpg",
        imagesFolder
    )
}

@Throws(IOException::class)
fun createVideoFile(context: Context): File {

    val timeStamp: String = SimpleDateFormat("yyMMdd_HHmmss", Locale.getDefault()).format(Date())
    val videosFolder = File(context.cacheDir, "videos")
    videosFolder.mkdirs()
    return File.createTempFile(
        "personal_interview_${timeStamp}_",
        ".mp4",
        videosFolder
    )
}
