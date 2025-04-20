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

import es.umh.dadm.mistickets74384229k.Categoria.Categoria;
import es.umh.dadm.mistickets74384229k.Interfaz.OnItemClickListener;
import es.umh.dadm.mistickets74384229k.R;
import es.umh.dadm.mistickets74384229k.Util.Miscelaneo;

//Adaptador para mostrar la categoria en su celda
public class AdaptadorCategoria extends RecyclerView.Adapter<AdaptadorCategoria.ViewHolder>{
    private final OnItemClickListener listener;
    private final Context context;
    private final ArrayList<Categoria> listaCategorias;
    public AdaptadorCategoria(@NonNull Context context, @NonNull ArrayList<Categoria> listaCategorias, OnItemClickListener listener)
    {
        this.listener = listener;
        this.context = context;
        this.listaCategorias = listaCategorias;
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.cat_cell, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        return new ViewHolder(view);
    }

    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        Categoria categoria = listaCategorias.get(position);
        holder.image.setImageBitmap(Miscelaneo.convertirBase64AImagen(categoria.getImage()));
        holder.nombre.setText(categoria.getNombreCat());
        holder.descCorta.setText(categoria.getDescrCorta());

        holder.itemView.setOnClickListener(v -> listener.OnItemClick(position));
        holder.itemView.setOnLongClickListener(v -> {listener.OnItemLongClick(position);
                return true;
        });
    }

    @Override
    public int getItemCount() {
        return listaCategorias.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView image;
        TextView nombre, descCorta;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            image = itemView.findViewById(R.id.img_cat);
            nombre = itemView.findViewById(R.id.nombre_cat);
            descCorta = itemView.findViewById(R.id.desc_corta_cat);

        }
    }
}
