package es.umh.dadm.mistickets74384229k.Categoria;

import java.util.ArrayList;
import es.umh.dadm.mistickets74384229k.R;

public class Categoria
{
    private static int contadorId = 0;
    private int id;
    private String nombreCat;
    private String descrCorta;
    private String descLarga;
    private int image;
    private static boolean inizializadas = false;
    private static ArrayList<Categoria> arrCat = new ArrayList<>();

    // Constructor
    public Categoria(String nombreCat, String descrCorta, String descLarga, int image)
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
    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getNombreCat() {
        return nombreCat;
    }

    public void setNombreCat(String nombreCat) {
        this.nombreCat = nombreCat;
    }

    public String getDescrCorta() {
        return descrCorta;
    }

    public void setDescrCorta(String descrCorta) {
        this.descrCorta = descrCorta;
    }

    public String getDescLarga() {
        return descLarga;
    }

    public void setDescLarga(String descLarga) {
        this.descLarga = descLarga;
    }

    public static ArrayList<Categoria> getArrCat() {
        return arrCat;
    }

    // Funciones para agregar y eliminar categorías
    public static void addCat(Categoria cat) {
        cat.id = contadorId++;
        arrCat.add(cat);
    }

    public static void deleteCat(Categoria cat) {
        arrCat.remove(cat);
    }

    // Método para inicializar 20 categorías por defecto
    public static void inicializarCategorias()
    {
        if(inizializadas == false)
        {
            for(int i = 0; i < 10; i++)
            {
                new Categoria("a " + i, "b " + i, "c " + i, R.mipmap.ic_launcher);
            }
            inizializadas = true;
        }
    }
}
