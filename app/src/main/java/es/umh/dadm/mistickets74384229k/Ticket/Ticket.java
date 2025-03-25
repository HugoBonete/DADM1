package es.umh.dadm.mistickets74384229k.Ticket;

public class Ticket
{
    private int idContador = 0;
    private int id;
    private int img;
    private int precio;
    private String fecha;
    private String descCorta;
    private String descLarga;
    private String localizacion;

    public Ticket(int img, int precio, String fecha, String descCorta, String descLarga, String localizacion)
    {
        id = idContador++;
        this.img = img;
        this.precio = precio;
        this.fecha = fecha;
        this.descCorta = descCorta;
        this.descLarga = descLarga;
        this.localizacion = localizacion;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescCorta() {
        return descCorta;
    }

    public void setDescCorta(String descCorta) {
        this.descCorta = descCorta;
    }

    public String getDescLarga() {
        return descLarga;
    }

    public void setDescLarga(String descLarga) {
        this.descLarga = descLarga;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }
}
