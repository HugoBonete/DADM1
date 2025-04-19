package es.umh.dadm.mistickets74384229k.Categoria;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

import static es.umh.dadm.mistickets74384229k.Util.Miscelaneo.abrirGaleria;

import android.app.AlertDialog;
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

import es.umh.dadm.mistickets74384229k.R;
import es.umh.dadm.mistickets74384229k.Util.Miscelaneo;

public class DialogBorroso extends DialogFragment {
    private CategoriasFragment categoriasFragment; // Variable para guardar la referencia
    private static final int CAPTURAR_IMAGEN = 1;
    private ImageView img_cat;
    private Bitmap bp;
    private ActivityResultLauncher<Intent> galeriaIntent;
    private Uri imgSelec;


    public DialogBorroso(CategoriasFragment fragment) {
        this.categoriasFragment = fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
                                e.printStackTrace();
                            }
                        }
                    }
                }
        );
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_form_add_cat, container, false);

        EditText txt_nom = view.findViewById(R.id.txt_cat_nom);
        EditText txt_descort = view.findViewById(R.id.txt_cat_descort);
        EditText txt_desclarg = view.findViewById(R.id.txt_cat_desclarg);
        Button btn_add_cat = view.findViewById(R.id.crear_cat_btn);
        img_cat = view.findViewById(R.id.img_cat_cell);

        FloatingActionButton btn_add_img = view.findViewById(R.id.fab_add_img_cat);

        btn_add_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Escoger Foto");
                builder.setMessage("¿Desea escoger la foto desde la camara o la galeria?");
                builder.setPositiveButton("Galeria", ((dialog, which) -> {
                    abrirGaleria(galeriaIntent);
                }));
                builder.setNegativeButton("Camara", (dialog, which) -> {
                    Intent img_int = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(img_int, CAPTURAR_IMAGEN);
                });
                AlertDialog dialog = builder.create();
                dialog.show();
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
                try
                {
                    Categoria cat = new Categoria(nom, descort, desclarg, Miscelaneo.convertirImagenABase64(bp));
                    categoriasFragment.guardarCategorias();
                }catch(Exception e){
                    Toast.makeText(getContext(), "Error al guardar la categoria", Toast.LENGTH_LONG).show();
                }

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
                bp = (Bitmap) data.getExtras().get("data");
                img_cat.setImageBitmap(bp);
            } else if (resultCode == RESULT_CANCELED) {
            }
        }
    }
}
