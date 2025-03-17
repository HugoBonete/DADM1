package es.umh.dadm.mistickets74384229k.Categoria;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import es.umh.dadm.mistickets74384229k.Adaptador.AdaptadorCategoria;
import es.umh.dadm.mistickets74384229k.R;
import es.umh.dadm.mistickets74384229k.Util.DialogBorroso;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CategoriasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoriasFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private View view;
    private AdaptadorCategoria adapter;
    private RecyclerView lvCat;
    private String mParam1;
    private String mParam2;

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
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_categorias, container, false);
        cargarCategorias();

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab_cat);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogBorroso dialog = new DialogBorroso();
                dialog.show(getParentFragmentManager(), "BlurDialog");
            }
        });

        return view;
    }

    private void cargarCategorias()
    {
        lvCat = view.findViewById(R.id.recyclerViewCat);
        Categoria.inicializarCategorias();
        adapter = new AdaptadorCategoria(requireContext(), Categoria.getArrCat());
        lvCat.setLayoutManager(new GridLayoutManager(getContext(), 2));
        lvCat.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}