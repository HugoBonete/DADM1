package es.umh.dadm.mistickets74384229k.Categoria;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import es.umh.dadm.mistickets74384229k.R;
import es.umh.dadm.mistickets74384229k.Util.Miscelaneo;

public class DialogBorrosoEdit extends DialogFragment {
    private CategoriasFragment categoriasFragment; // Variable para guardar la referencia
    private static final int CAPTURAR_IMAGEN = 1;
    private ImageView img_cat;
    private Bitmap bp;
    private Categoria cat;


    public DialogBorrosoEdit(CategoriasFragment fragment, Categoria cat)
    {
        this.categoriasFragment = fragment;
        this.cat = cat;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.edit_categoria, container, false);

        EditText txt_nom = view.findViewById(R.id.edit_nom_cat);
        EditText txt_descort = view.findViewById(R.id.edit_desccort_cat);
        EditText txt_desclarg = view.findViewById(R.id.edit_desclarg_cat);

        txt_nom.setText(cat.getNombreCat());
        txt_descort.setText(cat.getDescrCorta());
        txt_desclarg.setText(cat.getDescLarga());


        FloatingActionButton btn_edit = view.findViewById(R.id.edit_fab);
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cat.setNombreCat(txt_nom.getText().toString());
                cat.setDescrCorta(txt_descort.getText().toString());
                cat.setDescLarga(txt_desclarg.getText().toString());
                if (categoriasFragment != null) {
                    categoriasFragment.guardarCategorias();
                }
                Bundle result = new Bundle();
                result.putBoolean("categoriaAgregada", true);
                getParentFragmentManager().setFragmentResult("nuevaCategoria", result);
                dismiss();
            }
        });


        /*btn_add_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent img_int = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(img_int, CAPTURAR_IMAGEN);
            }
        });

        btn_add_cat.setOnClickListener(v -> {
            String nom = txt_nom.getText().toString().trim();
            String descort = txt_descort.getText().toString().trim();
            String desclarg = txt_desclarg.getText().toString().trim();

            if (nom.isEmpty() || descort.isEmpty() || desclarg.isEmpty()) {
                Toast.makeText(getContext(), "Por favor, rellena todos los campos", Toast.LENGTH_LONG).show();
                return;
            }
            if (categoriasFragment != null) {
                Categoria cat = new Categoria(nom, descort, desclarg, Miscelaneo.convertirImagenABase64(bp));
                categoriasFragment.guardarCategorias();
            } else {
                Toast.makeText(getContext(), R.string.no_disponible, Toast.LENGTH_LONG).show();
            }

            Bundle result = new Bundle();
            result.putBoolean("categoriaAgregada", true);
            getParentFragmentManager().setFragmentResult("nuevaCategoria", result);

            dismiss();
        });*/

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() != null && getDialog().getWindow() != null)
        {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode == CAPTURAR_IMAGEN)
        {
            if (resultCode == RESULT_OK) {
                bp = (Bitmap) data.getExtras().get("data");
                img_cat.setImageBitmap(bp);
            } else if (resultCode == RESULT_CANCELED) {
            }
        }
    }
}
