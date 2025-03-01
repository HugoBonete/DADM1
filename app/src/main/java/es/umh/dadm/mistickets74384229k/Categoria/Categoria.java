package es.umh.dadm.mistickets74384229k.Categoria;

import java.util.ArrayList;

public class Categoria
{
    private int id;
    private String nombreCat;
    private String descrCorta;
    private String descLarga;
    private static ArrayList<Categoria> arrCat = crearCat();

    //Constructores
    public Categoria (String nombreCat, String descrCorta, String descLarga)
    {
        this.nombreCat = nombreCat;
        this.descLarga = descLarga;
        this.descrCorta = descrCorta;
        if(arrCat.isEmpty())
        {
            this.id = 0;
        }else{
            this.id = getArrCat().get(arrCat.size() - 1).getId() + 1;
        }
    }
    //Getters y Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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
    //Funciones
    public static void addCat(Categoria cat)
    {
        arrCat.add(cat);
    }
    public static void deleteCat(Categoria cat)
    {
        arrCat.remove(cat);
    }

    public static ArrayList<Categoria> crearCat()
    {
        ArrayList<Categoria> arr = new ArrayList<Categoria>();
        for(int i = 0; i < 20; i++)
        {
            Categoria cat = new Categoria("a " + i, "b " + i, "c " + i);
            arr.add(cat);
        }
        return arr;
    }
}
