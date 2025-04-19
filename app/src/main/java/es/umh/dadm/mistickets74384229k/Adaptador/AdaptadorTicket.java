package es.umh.dadm.mistickets74384229k.Adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import es.umh.dadm.mistickets74384229k.Interfaz.OnItemClickListener;
import es.umh.dadm.mistickets74384229k.R;
import es.umh.dadm.mistickets74384229k.Ticket.Ticket;
import es.umh.dadm.mistickets74384229k.Util.Miscelaneo;

public class AdaptadorTicket extends RecyclerView.Adapter<AdaptadorTicket.ViewHolder>
{
    private final OnItemClickListener listener;
    private  Context context;
    private ArrayList<Ticket> listaTickets;

    public AdaptadorTicket(@NonNull Context context, @NonNull ArrayList<Ticket> listaTickets, OnItemClickListener listener)
    {
        this.listener = listener;
        this.context = context;
        this.listaTickets = listaTickets;
    }

    @NonNull
    @Override
    public AdaptadorTicket.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.tick_cell, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        return new AdaptadorTicket.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorTicket.ViewHolder holder, int position)
    {
        Ticket ticket = listaTickets.get(position);
        holder.img.setImageBitmap(Miscelaneo.convertirByteABitmap(ticket.getImg()));
        holder.descCorta.setText(ticket.getDescCorta());
        holder.precio.setText(String.format("%.2f €", ticket.getPrecio()));

        holder.itemView.setOnClickListener(v -> listener.OnItemClick(position));
        holder.itemView.setOnLongClickListener(v -> {listener.OnItemLongClick(position);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return listaTickets.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView img;
        TextView precio, descCorta;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            img = itemView.findViewById(R.id.img_ticket_cell);
            precio = itemView.findViewById(R.id.precio_tick);
            descCorta = itemView.findViewById(R.id.desc_corta_tick);

        }
    }
}
