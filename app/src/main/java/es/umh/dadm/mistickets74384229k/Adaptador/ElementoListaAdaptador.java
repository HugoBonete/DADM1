package es.umh.dadm.mistickets74384229k.Adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import es.umh.dadm.mistickets74384229k.Categoria.Categoria;
import es.umh.dadm.mistickets74384229k.R;

public class ElementoListaAdaptador extends BaseAdapter {
    private Context mContext;
    private ArrayList<Categoria> arrCat;
    private LayoutInflater inflater;

    // Constructor para asignar el Contexto y el origen de datos
    public ElementoListaAdaptador(Context context, ArrayList<Categoria> array) {
        this.mContext = context; // Asignamos el contexto correcto
        this.arrCat = array;
        this.inflater = LayoutInflater.from(context); // Optimizamos el inflado
    }

    @Override
    public int getCount() {
        return arrCat.size();
    }

    @Override
    public Object getItem(int position) {
        return arrCat.get(position); // Devuelve el objeto correcto
    }

    @Override
    public long getItemId(int position) {
        return arrCat.get(position).getId(); // Devuelve el ID real
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            // Inflamos la vista si es la primera vez que se usa
            convertView = inflater.inflate(R.layout.fragment_categorias, parent, false);
            holder = new ViewHolder();
            holder.textViewId = convertView.findViewById(R.id.textViewId);
            holder.textViewTitulo = convertView.findViewById(R.id.textViewTitulo);
            convertView.setTag(holder); // Guardamos el ViewHolder en la vista
        } else {
            // Reutilizamos la vista existente
            holder = (ViewHolder) convertView.getTag();
        }

        // Asignamos los valores correspondientes
        Categoria categoria = arrCat.get(position);
        holder.textViewId.setText(String.valueOf(categoria.getId()));
        holder.textViewTitulo.setText(categoria.getNombreCat());

        return convertView;
    }

    // Clase interna para mejorar el rendimiento con ViewHolder
    private static class ViewHolder {
        TextView textViewId;
        TextView textViewTitulo;
    }
}
