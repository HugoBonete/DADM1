package es.umh.dadm.mistickets74384229k.Ticket;

import java.util.ArrayList;

import es.umh.dadm.mistickets74384229k.Categoria.Categoria;

public class Ticket
{
    private int id;
    private byte[] img;
    private double precio;
    private String fecha;
    private String descCorta;
    private String descLarga;
    private String localizacion;
    private Categoria cat;
    private static ArrayList<Ticket> arrTicket = new ArrayList<Ticket>();

    public Ticket(byte[] img, Categoria cat,double precio, String fecha, String descCorta, String descLarga, String localizacion)
    {
        this.cat = cat;
        this.img = img;
        this.precio = precio;
        this.fecha = fecha;
        this.descCorta = descCorta;
        this.descLarga = descLarga;
        this.localizacion = localizacion;
        arrTicket.add(this);
    }
    public Ticket(int id,byte[] img, Categoria cat,double precio, String fecha, String descCorta, String descLarga, String localizacion)
    {
        this.id = id;
        this.cat = cat;
        this.img = img;
        this.precio = precio;
        this.fecha = fecha;
        this.descCorta = descCorta;
        this.descLarga = descLarga;
        this.localizacion = localizacion;
        arrTicket.add(this);
    }

    public int getId() {
        return id;
    }

    public Categoria getCat()
    {
        return cat;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setDescCorta(String descCorta) {
        this.descCorta = descCorta;
    }

    public void setDescLarga(String descLarga) {
        this.descLarga = descLarga;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public void setCat(Categoria cat) {
        this.cat = cat;
    }

    public double getPrecio() {
        return precio;
    }

    public String getFecha() {
        return fecha;
    }

    public String getDescCorta() {
        return descCorta;
    }

    public String getDescLarga() {
        return descLarga;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public static ArrayList<Ticket> getArrTicket() {
        return arrTicket;
    }
}