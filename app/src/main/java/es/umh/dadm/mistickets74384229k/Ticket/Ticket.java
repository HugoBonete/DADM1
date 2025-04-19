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

    public Categoria getCat()
    {
        return cat;
    }

    public byte[] getImg() {
        return img;
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

    public ArrayList<Ticket> getArrTicket() {
        return arrTicket;
    }
}