package es.umh.dadm.mistickets74384229k.main;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import es.umh.dadm.mistickets74384229k.Categoria.CategoriasFragment;
import es.umh.dadm.mistickets74384229k.R;
import es.umh.dadm.mistickets74384229k.Ticket.TicketsFragment;
import es.umh.dadm.mistickets74384229k.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Usa ViewBinding correctamente
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Habilitar EdgeToEdge después de setContentView()
        EdgeToEdge.enable(this);

        // Cargar el fragmento inicial solo si no hay uno ya cargado
        if (savedInstanceState == null) {
            replaceFrag(new InicioFragment());
        }

        // Manejo de la navegación inferior
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if (R.id.home == item.getItemId()) {
                replaceFrag(new InicioFragment());
            } else if (R.id.categorias == item.getItemId()) {
                replaceFrag(new CategoriasFragment());
            } else if (R.id.tickets == item.getItemId()) {
                replaceFrag(new TicketsFragment());
            }
            return true;
        });
    }

    private void replaceFrag(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainerView, fragment);
        fragmentTransaction.commit();
    }
}
