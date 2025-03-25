package es.umh.dadm.mistickets74384229k.Adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import es.umh.dadm.mistickets74384229k.Categoria.Categoria;
import es.umh.dadm.mistickets74384229k.R;
import es.umh.dadm.mistickets74384229k.Util.Miscelaneo;

public class AdaptadorCategoria extends RecyclerView.Adapter<AdaptadorCategoria.ViewHolder> {

    private  Context context;
    private ArrayList<Categoria> listaCategorias;
    public AdaptadorCategoria(@NonNull Context context, @NonNull ArrayList<Categoria> listaCategorias)
    {
        this.context = context;
        this.listaCategorias = listaCategorias;
    }

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
            nombre = itemView.findViewById(R.id.nom_cat);
            descCorta = itemView.findViewById(R.id.desc_cort_cat);

        }
    }
}
