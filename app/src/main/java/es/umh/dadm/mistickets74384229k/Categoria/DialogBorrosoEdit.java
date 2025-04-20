package es.umh.dadm.mistickets74384229k.Categoria;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

import static es.umh.dadm.mistickets74384229k.Util.Miscelaneo.abrirGaleria;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

import es.umh.dadm.mistickets74384229k.R;
import es.umh.dadm.mistickets74384229k.Util.Miscelaneo;

public class DialogBorrosoEdit extends DialogFragment {
    private final CategoriasFragment categoriasFragment; // Variable para guardar la referencia
    private static final int CAPTURAR_IMAGEN = 1;
    private ImageView img_cat;
    private Uri imgSelec;

    private Bitmap bp;
    private final Categoria cat;
    private ActivityResultLauncher<Intent> galeriaIntent;

    public DialogBorrosoEdit(CategoriasFragment fragment, Categoria cat)
    {
        this.categoriasFragment = fragment;
        this.cat = cat;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Para poder acceder a la galeria
        galeriaIntent = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null && data.getData() != null) {
                        imgSelec = data.getData();
                        try {
                            bp = MediaStore.Images.Media.getBitmap(
                                    requireActivity().getContentResolver(),
                                    imgSelec);
                            if (img_cat != null) {
                                img_cat.setImageBitmap(bp);
                            }
                        } catch (Exception e) {
                            Toast.makeText(getContext(), "Error al intentar acceder a la galeria",Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        );
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.layout_form_add_cat, container, false);

        EditText txt_nom = view.findViewById(R.id.txt_cat_nom);
        EditText txt_descort = view.findViewById(R.id.txt_cat_descort);
        EditText txt_desclarg = view.findViewById(R.id.txt_cat_desclarg);
        img_cat = view.findViewById(R.id.img_cat_cell);

        img_cat.setImageBitmap(Miscelaneo.convertirBase64AImagen(cat.getImage()));
        txt_nom.setText(cat.getNombreCat());
        txt_descort.setText(cat.getDescrCorta());
        txt_desclarg.setText(cat.getDescLarga());

        FloatingActionButton btn_add_img = view.findViewById(R.id.fab_add_img_cat);

        btn_add_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new com.google.android.material.dialog.MaterialAlertDialogBuilder(requireContext())
                        .setTitle("Escoger Foto")
                        .setMessage("¿Desea escoger la foto desde la cámara o la galería?")
                        .setPositiveButton("Galería", (dialog, which) -> {
                            abrirGaleria(galeriaIntent);
                        })
                        .setNegativeButton("Cámara", (dialog, which) -> {
                            Intent img_int = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(img_int, CAPTURAR_IMAGEN);
                        })
                        .show();
            }
        });


        Button btn_edit = view.findViewById(R.id.crear_cat_btn);
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cat.setNombreCat(txt_nom.getText().toString());
                cat.setDescrCorta(txt_descort.getText().toString());
                cat.setDescLarga(txt_desclarg.getText().toString());
                if (categoriasFragment != null) {
                    try{
                        if (bp != null) {
                            cat.setImage(Miscelaneo.convertirImagenABase64(bp));
                        }
                        categoriasFragment.guardarCategorias();
                    } catch (Exception e) {
                        Toast.makeText(getContext(), "Error al modificar la categoria pruebe de nuevo",Toast.LENGTH_LONG).show();
                    }

                }
                Bundle result = new Bundle();
                result.putBoolean("categoriaAgregada", true);
                getParentFragmentManager().setFragmentResult("nuevaCategoria", result);
                dismiss();
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() != null && getDialog().getWindow() != null)
        {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
            getDialog().getWindow().setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode == CAPTURAR_IMAGEN)
        {
            if (resultCode == RESULT_OK) {
                bp = (Bitmap) Objects.requireNonNull(data.getExtras()).get("data");
                img_cat.setImageBitmap(bp);
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(getContext(), "Error al hacer la foto con la camara",Toast.LENGTH_LONG).show();
            }
        }
    }
}
