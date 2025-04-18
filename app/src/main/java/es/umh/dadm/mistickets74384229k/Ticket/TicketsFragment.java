package es.umh.dadm.mistickets74384229k.Ticket;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import es.umh.dadm.mistickets74384229k.Categoria.CategoriasFragment;
import es.umh.dadm.mistickets74384229k.Categoria.DialogBorroso;
import es.umh.dadm.mistickets74384229k.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TicketsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TicketsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
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

    }
}