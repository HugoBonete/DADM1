package es.umh.dadm.mistickets74384229k.Util;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import es.umh.dadm.mistickets74384229k.Adaptador.AdaptadorCategoria;
import es.umh.dadm.mistickets74384229k.Categoria.Categoria;
import es.umh.dadm.mistickets74384229k.Categoria.CategoriasFragment;
import es.umh.dadm.mistickets74384229k.R;

public class DialogBorroso extends DialogFragment {
    private CategoriasFragment categoriasFragment; // Variable para guardar la referencia

    public DialogBorroso(CategoriasFragment fragment) {
        this.categoriasFragment = fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_form_add_cat, container, false);

        EditText txt_nom = view.findViewById(R.id.txt_cat_nom);
        EditText txt_descort = view.findViewById(R.id.txt_cat_descort);
        EditText txt_desclarg = view.findViewById(R.id.txt_cat_desclarg);
        Button btn_add = view.findViewById(R.id.crear_cat_btn);

        btn_add.setOnClickListener(v -> {
            String nom = txt_nom.getText().toString().trim();
            String descort = txt_descort.getText().toString().trim();
            String desclarg = txt_desclarg.getText().toString().trim();

            if (nom.isEmpty() || descort.isEmpty() || desclarg.isEmpty()) {
                Toast.makeText(getContext(), "Por favor, rellena todos los campos", Toast.LENGTH_LONG).show();
                return;
            }

            Categoria cat = new Categoria(nom, descort, desclarg, R.mipmap.ic_launcher);

            if (categoriasFragment != null) {
                categoriasFragment.guardarCategoria(cat); // Llamar directamente al método
            } else {
                Toast.makeText(getContext(), R.string.no_disponible, Toast.LENGTH_LONG).show();
            }

            Bundle result = new Bundle();
            result.putBoolean("categoriaAgregada", true);
            getParentFragmentManager().setFragmentResult("nuevaCategoria", result);

            dismiss();
        });

        return view;
    }




    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }
}
