package es.umh.dadm.mistickets74384229k.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import es.umh.dadm.mistickets74384229k.Categoria.Categoria;
import es.umh.dadm.mistickets74384229k.Ticket.Ticket;

//Clase de SQLiteHelper
public class TicketsHelper extends SQLiteOpenHelper
{

    Cursor cursor;
    SQLiteDatabase db;
    String[] campos = new String[]{"id", "img", "id_categoria", "precio", "fecha", "descCorta"
    , "descLarga", "localizacion"};
    private static final String sqlCreate = "CREATE TABLE Tickets (id integer primary key autoincrement, img BLOB not null, id_categoria integer not null, " +
            "precio double not null,fecha text not null, descCorta text not null, descLarga text not null, " +
            "localizacion text not null) ";
    private static final String nombreBD = "DBTickets";
    public TicketsHelper(Context contexto, SQLiteDatabase.CursorFactory factory)
    {
        super(contexto, nombreBD, factory, 7);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS Tickets");
        db.execSQL(sqlCreate);
    }

    public ArrayList<Ticket> obtenerTickets()
    {
        //Abre la base de datos en modo escritura
        db = getWritableDatabase();

        cursor = db.query("Tickets", campos, "", null, null, null, null);
        ArrayList<Ticket> arr = new ArrayList<Ticket>();
        Ticket ticket;
        if(cursor.moveToFirst())
        {
            do{
                ticket = obtenerValores();
                arr.add(ticket);
            }while(cursor.moveToNext());
        }
        return arr;
    }

    public void AgregarTicket(Ticket ticket)
    {
        ContentValues nuevoRegistro = asignarValores(ticket);
        db = getWritableDatabase();
        db.insert("Tickets", null, nuevoRegistro);
    }

    public ContentValues asignarValores(Ticket ticket)
    {
        ContentValues nuevoRegistro = new ContentValues();
        nuevoRegistro.put("img", ticket.getImg());
        nuevoRegistro.put("id_categoria", ticket.getCat().getId());
        nuevoRegistro.put("precio", ticket.getPrecio());
        nuevoRegistro.put("fecha", ticket.getFecha());
        nuevoRegistro.put("descCorta", ticket.getDescCorta());
        nuevoRegistro.put("descLarga", ticket.getDescLarga());
        nuevoRegistro.put("localizacion", ticket.getLocalizacion());
        return nuevoRegistro;
    }

    //ObtieneValores del cursor y devuelve ticket
    public Ticket obtenerValores()
    {
        int id = cursor.getInt(0);
        byte[] img = cursor.getBlob(1);
        int idCat = cursor.getInt(2);
        double precio = cursor.getDouble(3);
        String fecha = cursor.getString(4);
        String descCorta = cursor.getString(5);
        String descLarga = cursor.getString(6);
        String localizacion = cursor.getString(7);

        Categoria cat = Categoria.encontrarId(idCat);

        return new Ticket(id, img, cat, precio, fecha, descCorta, descLarga, localizacion);
    }

    //Elimina todos los tickets asociados a la categoria que se esta eliminando
    public void eliminarTicketAsociados(int id)
    {
        db = getWritableDatabase();
        db.delete("Tickets", "id_categoria = ?", new String[]{String.valueOf(id)});
    }

    //Elimina un tcket en especifico
    public void eliminarTicket(int id)
    {
        db = getWritableDatabase();
        db.delete("Tickets", "id = ?", new String[]{String.valueOf(id)});
    }
    //Actualiza los valores de un ticket
    public void actualizarTicket(Ticket ticket) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("id_categoria", ticket.getCat().getId());
        values.put("descCorta", ticket.getDescCorta());
        values.put("descLarga", ticket.getDescLarga());
        values.put("precio", ticket.getPrecio());
        values.put("fecha", ticket.getFecha());
        values.put("img", ticket.getImg());
        values.put("localizacion", ticket.getLocalizacion());

        db.update("Tickets", values, "id = ?", new String[]{String.valueOf(ticket.getId())});
        db.close();
    }
}
