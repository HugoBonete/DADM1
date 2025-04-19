package es.umh.dadm.mistickets74384229k.Ticket;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.fragment.app.DialogFragment;

public class DialogoEditTicket extends DialogFragment {
    private static final int CAPTURAR_IMAGEN = 1;
    private ImageView img_tick;
    private Bitmap bp;
    private ActivityResultLauncher<Intent> galeriaIntent;
    private Uri imgSelec;
    TicketsFragment ticketsFragment;


}