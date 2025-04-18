package es.umh.dadm.mistickets74384229k.Ticket;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.DialogFragment;

import es.umh.dadm.mistickets74384229k.Categoria.Categoria;
import es.umh.dadm.mistickets74384229k.R;

public class DialogoBorrosoTicket extends DialogFragment
{

    TicketsFragment ticketsFragment;
    public DialogoBorrosoTicket (TicketsFragment fragment)
    {
        this.ticketsFragment = fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.layout_form_add_tick, container, false);

        EditText precio = view.findViewById(R.id.tick_precio);
        EditText desCort = view.findViewById(R.id.tick_desc_corta);
        EditText descLarg = view.findViewById(R.id.tick_desc_larga);
        EditText fecha = view.findViewById(R.id.tick_fecha);
        EditText loc = view.findViewById(R.id.tick_ruta_imagen);
        Spinner spinner = view.findViewById(R.id.spinner_categoria);
        ArrayAdapter<Categoria> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_dropdown_item, Categoria.getArrCat());
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });



        return view;
    }

    public void onStart()
    {
        super.onStart();
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

}
