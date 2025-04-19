package es.umh.dadm.mistickets74384229k.Ticket;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import es.umh.dadm.mistickets74384229k.Adaptador.AdaptadorTicket;
import es.umh.dadm.mistickets74384229k.Categoria.CategoriasFragment;
import es.umh.dadm.mistickets74384229k.Categoria.DialogBorroso;
import es.umh.dadm.mistickets74384229k.Interfaz.OnItemClickListener;
import es.umh.dadm.mistickets74384229k.R;
import es.umh.dadm.mistickets74384229k.SQLite.TicketsHelper;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TicketsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TicketsFragment extends Fragment implements OnItemClickListener{


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView lvTick;
    private View view;
    private String mParam1;
    private String mParam2;

    public TicketsFragment() {

    }

    public static TicketsFragment newInstance(String param1, String param2) {
        TicketsFragment fragment = new TicketsFragment();
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
        view = inflater.inflate(R.layout.fragment_tickets, container, false);
        cargarTickets();
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab_ticket);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogoBorrosoTicket dialog = new DialogoBorrosoTicket(TicketsFragment.this);
                dialog.show(getParentFragmentManager(), "DialogoBorrosoTicket");
            }
        });


        return view;
    }

    public void cargarTickets()
    {
        lvTick = view.findViewById(R.id.recyclerViewTicket);
        TicketsHelper usdbh = new TicketsHelper(requireContext(), null);

        ArrayList<Ticket> arr = usdbh.obtenerTickets();
        AdaptadorTicket adaptadorTicket = new AdaptadorTicket(requireContext(), arr, (OnItemClickListener) this);
        lvTick.setLayoutManager(new GridLayoutManager(getContext(), 2));
        lvTick.setAdapter(adaptadorTicket);
        adaptadorTicket.notifyDataSetChanged();

    }

    public void guardarTickets(Ticket ticket)
    {
        TicketsHelper usdbh = new TicketsHelper(getContext(), null);
        usdbh.AgregarTicket(ticket);
        cargarTickets();
        Log.d("DEBUG", "Tickets guardados: " + usdbh.obtenerTickets().size());
    }

    @Override
    public void OnItemClick(int position) {

    }

    @Override
    public void OnItemLongClick(int position) {

    }
}