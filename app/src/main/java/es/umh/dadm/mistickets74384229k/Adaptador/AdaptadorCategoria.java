package es.umh.dadm.mistickets74384229k.Adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.List;

import es.umh.dadm.mistickets74384229k.Categoria.Categoria;
import es.umh.dadm.mistickets74384229k.R;

public class AdaptadorCategoria extends ArrayAdapter<Categoria> {

    private  Context context;
    private int resource;
    private List<Categoria> objects;
    public AdaptadorCategoria(@NonNull Context context, int resource, @NonNull List<Categoria> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if(view == null)
        {
            view = LayoutInflater.from(context).inflate(resource, null);
        }
        Categoria cat = objects.get(position);

        ImageView image = view.findViewById(R.id.img_cat);
        image.setImageResource(cat.getImage());

        TextView nombre = view.findViewById(R.id.nom_cat);
        nombre.setText(cat.getNombreCat());

        TextView descCorta = view.findViewById(R.id.desc_cort_cat);
        descCorta.setText(cat.getDescrCorta());

        return view;
    }
}
