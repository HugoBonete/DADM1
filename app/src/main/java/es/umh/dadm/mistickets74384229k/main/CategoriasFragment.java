package es.umh.dadm.mistickets74384229k.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.sax.Element;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import es.umh.dadm.mistickets74384229k.Adaptador.ElementoListaAdaptador;
import es.umh.dadm.mistickets74384229k.Categoria.Categoria;
import es.umh.dadm.mistickets74384229k.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CategoriasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoriasFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private View view;
    private ElementoListaAdaptador adapter;
    private ListView lvCat;
    private String mParam1;
    private String mParam2;

    public CategoriasFragment() {
        // Required empty public constructor
    }

    private void cargarCategorias()
    {
        lvCat = (ListView) view.findViewById(R.id.listViewCat);
        adapter = new ElementoListaAdaptador(requireContext(), Categoria.getArrCat());
        lvCat.setAdapter(adapter);
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
        return view;
    }


}