package es.umh.dadm.mistickets74384229k.Categoria;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import es.umh.dadm.mistickets74384229k.R;

public class Categoria
{
    private static int contadorId = 0;
    private int id;
    private String nombreCat;
    private String descrCorta;
    private String descLarga;
    private String image;
    private static final ArrayList<Categoria> arrCat = new ArrayList<>();

    public Categoria() {}

    public Categoria(String nombreCat, String descrCorta, String descLarga, String image)
    {
        this.id = contadorId++; // Se incrementa el ID estático en cada nueva instancia
        this.nombreCat = nombreCat;
        this.descLarga = descLarga;
        this.descrCorta = descrCorta;
        this.image = image;

        // Se añade la categoría a la lista directamente en el constructor
        arrCat.add(this);
    }

    // Getters y Setters
    public String getImage()
    {
        return image;
    }

    public void setImage(String image)
    {
        this.image = image;
    }

    public int getId()
    {
        return id;
    }

    public String getNombreCat() {
        return nombreCat;
    }

    public void setNombreCat(String nombreCat)
    {
        this.nombreCat = nombreCat;
    }

    public String getDescrCorta()
    {
        return descrCorta;
    }

    public void setDescrCorta(String descrCorta)
    {
        this.descrCorta = descrCorta;
    }

    public String getDescLarga() {
        return descLarga;
    }

    public void setDescLarga(String descLarga)
    {
        this.descLarga = descLarga;
    }

    public static ArrayList<Categoria> getArrCat()
    {
        return arrCat;
    }
    public static void cargarCategorias(Context context) {
        // Suponiendo que cargarTexto() es tu función para leer de archivo
        ArrayList<Categoria> categoriasGuardadas = cargarTexto(context);

        arrCat.clear();
        arrCat.addAll(categoriasGuardadas);
        for (int i = 0; i < arrCat.size(); i++)
        {
            if (arrCat.get(i).getId() >= contadorId) {
                contadorId = arrCat.get(i).getId() + 1;
            }
        }
    }

    public static ArrayList<Categoria> cargarTexto(Context context)
    {
        File raiz = context.getExternalFilesDir(null);
        if (raiz == null) return new ArrayList<>();

        File fichero = new File(raiz, "categorias.json");
        if (!fichero.exists()) return new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fichero))) {
            Type listType = new TypeToken<ArrayList<Categoria>>() {}.getType();
            return new Gson().fromJson(reader, listType);
        } catch (IOException e) {
            Log.e(TAG, "Error al leer el archivo JSON", e);
            return new ArrayList<>();
        }
    }

    public static Categoria encontrarId(int id)
    {
        ArrayList<Categoria> arr = getArrCat();
        for(int i = 0; i < arr.size(); i++)
        {
            if(arr.get(i).getId() == id)
            {
                return arr.get(i);
            }
        }
        return null;
    }

    @NonNull
    @Override
    public String toString() {
        return this.nombreCat;
    }
}
