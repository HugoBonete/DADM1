package es.umh.dadm.mistickets74384229k.Util;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import androidx.activity.result.ActivityResultLauncher;

import java.io.ByteArrayOutputStream;

//Clase de funciones extras
public class Miscelaneo
{
    //Convierte de base64 a imagen
    public static Bitmap convertirBase64AImagen(String base)
    {
        byte[] base64String;
        byte[] bytes = Base64.decode(base, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    //Convierte de imagen a base46
    public static String convertirImagenABase64(Bitmap bitmap)
    {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] bytes = stream.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    //Convierte de byte[] a bitmap
    public static Bitmap convertirByteABitmap(byte[] img)
    {
        return BitmapFactory.decodeByteArray(img, 0, img.length);
    }

    //Convierte de Bitmap a byte[]
    public static byte[] convertirBitmapAByte(Bitmap bp)
    {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    //Funcion para abrir el intent de la galeria
    public static void abrirGaleria(ActivityResultLauncher<Intent> galeriaIntent) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        galeriaIntent.launch(intent);
    }
}
