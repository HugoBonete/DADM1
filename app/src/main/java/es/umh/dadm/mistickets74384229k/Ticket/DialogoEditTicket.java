package es.umh.dadm.mistickets74384229k.Ticket;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

import static es.umh.dadm.mistickets74384229k.Util.Miscelaneo.abrirGaleria;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

import es.umh.dadm.mistickets74384229k.Categoria.Categoria;
import es.umh.dadm.mistickets74384229k.R;
import es.umh.dadm.mistickets74384229k.SQLite.TicketsHelper;
import es.umh.dadm.mistickets74384229k.Util.Miscelaneo;

public class DialogoEditTicket extends DialogFragment
{
    private static final int CAPTURAR_IMAGEN = 1;
    Bitmap bp;
    private ActivityResultLauncher<Intent> galeriaIntent;
    private Uri imgSelec;
    private ImageView img_tick;
    private Ticket tick;
    TicketsFragment ticketsFragment;
    public DialogoEditTicket (TicketsFragment fragment, Ticket tick) {
        this.ticketsFragment = fragment;
        this.tick = tick;
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
                                if (img_tick != null) {
                                    img_tick.setImageBitmap(bp);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        );
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.layout_form_add_tick, container, false);
        img_tick = view.findViewById(R.id.img_ticket_cell);
        img_tick.setImageBitmap(Miscelaneo.convertirByteABitmap(tick.getImg()));
        FloatingActionButton btn_add_img = view.findViewById(R.id.fab_add_img_ticket);
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


        EditText txt_precio = view.findViewById(R.id.tick_precio);
        txt_precio.setText(String.valueOf(tick.getPrecio()));
        EditText txt_desCort = view.findViewById(R.id.tick_desc_corta);
        txt_desCort.setText(tick.getDescCorta());
        EditText txt_descLarg = view.findViewById(R.id.tick_desc_larga);
        txt_descLarg.setText(tick.getDescLarga());
        EditText txt_fecha = view.findViewById(R.id.tick_fecha);
        txt_fecha.setText(tick.getFecha());
        EditText txt_loc = view.findViewById(R.id.tick_ruta_imagen);
        txt_loc.setText(tick.getLocalizacion());
        txt_fecha.setOnClickListener(v -> {
            MaterialDatePicker<Long> datePicker = MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Selecciona una fecha")
                    .build();

            datePicker.addOnPositiveButtonClickListener(selection -> {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(selection);
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH) + 1;
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                String fecha = String.format("%04d-%02d-%02d", year, month, day);
                txt_fecha.setText(fecha);
            });

            datePicker.show(getParentFragmentManager(), "MaterialDatePicker");
        });


        Spinner spinner = view.findViewById(R.id.spinner_categoria);
        ArrayAdapter<Categoria> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_dropdown_item, Categoria.getArrCat());
        spinner.setAdapter(adapter);
        spinner.setSelection(Categoria.posicionId(tick.getCat()));
        Button btn_crear = view.findViewById(R.id.btn_guardar_ticket);
        btn_crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double precio = Double.parseDouble(txt_precio.getText().toString().trim());
                String descCort = txt_desCort.getText().toString().trim();
                String descLarg = txt_descLarg.getText().toString().trim();
                String fecha = txt_fecha.getText().toString().trim();
                String loc = txt_loc.getText().toString().trim();
                if(descCort.isEmpty() || descLarg.isEmpty() || fecha.isEmpty() || loc.isEmpty())
                {
                    Toast.makeText(getContext(), "Por favor, rellena todos los campos", Toast.LENGTH_LONG).show();
                    return;
                }if(ticketsFragment != null)
                {
                    try {
                        if (bp != null) {
                            tick.setImg(Miscelaneo.convertirBitmapAByte(bp));
                        }
                        tick.setCat((Categoria)spinner.getSelectedItem());
                        tick.setFecha(fecha);
                        tick.setDescCorta(descCort);
                        tick.setPrecio(precio);
                        tick.setDescLarga(descLarg);
                        TicketsHelper usdbh = new TicketsHelper(getContext(), null);
                        usdbh.actualizarTicket(tick);
                        ticketsFragment.cargarTickets();
                    }catch(NumberFormatException  e)
                    {
                        Toast.makeText(getContext(), "El precio debe ser un numero", Toast.LENGTH_LONG).show();
                    }
                    catch(Exception e)
                    {
                        Toast.makeText(getContext(), "Error al modificar el ticket", Toast.LENGTH_LONG).show();
                    }

                }else {
                    Toast.makeText(getContext(), R.string.no_disponible, Toast.LENGTH_LONG).show();
                }

                dismiss();
            }
        });

        return view;
    }

    public void onStart()
    {
        super.onStart();
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
            getDialog().getWindow().setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    //Para obtener la foto realizada con la camara
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode == CAPTURAR_IMAGEN)
        {
            if (resultCode == RESULT_OK) {
                bp = (Bitmap) data.getExtras().get("data");
                img_tick.setImageBitmap(bp);
            } else if (resultCode == RESULT_CANCELED) {
            }
        }
    }

}
