package es.umh.dadm.mistickets74384229k.Categoria;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.google.gson.Gson;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import es.umh.dadm.mistickets74384229k.Adaptador.AdaptadorCategoria;
import es.umh.dadm.mistickets74384229k.Interfaz.OnItemClickListener;
import es.umh.dadm.mistickets74384229k.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CategoriasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoriasFragment extends Fragment implements OnItemClickListener {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private View view;
    private AdaptadorCategoria adapter;
    private RecyclerView lvCat;

    private static final String FICHERO = "categorias.json";
    private static final String TAG = "Categorias";


    public CategoriasFragment() {
        // Required empty public constructor
    }
    public static CategoriasFragment newInstance(String param1, String param2) {
        CategoriasFragment fragment = new CategoriasFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_categorias, container, false);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab_cat);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogBorroso dialog = new DialogBorroso(CategoriasFragment.this);
                dialog.show(getParentFragmentManager(), "DialogoBorros");
            }
        });
        obtenerCategorias();

        getParentFragmentManager().setFragmentResultListener("nuevaCategoria", this, (requestKey, result) -> {
            if (result.getBoolean("categoriaAgregada"))
            {
                obtenerCategorias();
            }
        });

        getParentFragmentManager().setFragmentResultListener("catEditada", this, (requestKey, result) -> {
            if (result.getBoolean("categoriaEditada"))
            {
                obtenerCategorias();
            }
        });
        return view;
    }

    private void obtenerCategorias()
    {
        // Cargar las categorías desde el archivo
        ArrayList<Categoria> categoriasGuardadas = cargarTexto();

        // Reemplazar la lista actual con la lista cargada
        Categoria.getArrCat().clear();
        Categoria.getArrCat().addAll(categoriasGuardadas);

        lvCat = view.findViewById(R.id.recyclerViewCat);
        adapter = new AdaptadorCategoria(requireContext(), Categoria.getArrCat(), this);
        lvCat.setLayoutManager(new GridLayoutManager(getContext(), 2));
        lvCat.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public ArrayList<Categoria> cargarTexto()
    {
        File raiz = requireContext().getExternalFilesDir(null);
        if (raiz == null) return new ArrayList<>();

        File fichero = new File(raiz, "categorias.json");
        if (!fichero.exists()) return new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fichero))) {
            Type listType = new TypeToken<ArrayList<Categoria>>() {}.getType();
            return new Gson().fromJson(reader, listType);
        } catch (IOException e) {
            Log.e(TAG, "Error al leer el archivo JSON", e);
            return new ArrayList<>();
        }
    }

    public void guardarCategorias() {
        if (!puedoEscribirMemoriaExterna()) {
            Toast.makeText(getContext(), R.string.no_disponible, Toast.LENGTH_LONG).show();
            return;
        }

        File raiz = requireContext().getExternalFilesDir(null);
        File fichero = new File(raiz, "categorias.json");

        Gson gson = new Gson();
        String jsonString = gson.toJson(Categoria.getArrCat()); // Convertir la lista actualizada a JSON

        // Guardar en el archivo
        try (BufferedWriter buf = new BufferedWriter(new FileWriter(fichero))) {
            buf.write(jsonString);
            buf.flush();
        } catch (IOException e) {
            Log.e(TAG, "Error al guardar el archivo JSON", e);
        }
    }

    private boolean puedoEscribirMemoriaExterna() {
        String estado = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(estado);
    }

    private boolean puedoLeerMemoriaExterna()
    {
        String state = Environment.getExternalStorageState();
        return (state.equals(Environment.MEDIA_MOUNTED));
    }

    @Override
    public void OnItemClick(int position)
    {
        DialogBorrosoEdit dialog = new DialogBorrosoEdit(CategoriasFragment.this, Categoria.getArrCat().get(position));
        dialog.show(getParentFragmentManager(), "DialogoBorrosoEdit");
    }

    @Override
    public void OnItemLongClick(int position)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setTitle("Eliminar categoría");
        builder.setMessage("Si eliminas esta categoria eliminaras todos los tickets asociados a ella. ¿Estas seguro?");
        builder.setPositiveButton("Eliminar", (dialog, which) -> {
            Categoria.getArrCat().remove(position);
            adapter.notifyItemRemoved(position);
            adapter.notifyItemRangeChanged(position, Categoria.getArrCat().size());
            guardarCategorias();
        });
        builder.setNegativeButton("No", (dialog, which) -> {
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}