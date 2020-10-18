package com.appjruiz.s05_responsive_design;

import android.content.Context;
import android.icu.lang.UCharacter;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;


/**
 * A fragment representing a list of Items.
 */
public class NoteFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 2;
    private List<Note> noteList;
    private MyNoteRecyclerViewAdapter adapterNotes;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NoteFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static NoteFragment newInstance(int columnCount) {
        NoteFragment fragment = new NoteFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (view.getId() == R.id.listPortrait) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
                float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
                int numberColumns = (int) (dpWidth / 180);
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(numberColumns, StaggeredGridLayoutManager.VERTICAL));
            }

            noteList = new ArrayList<>();
            noteList.add(new Note("Lista de la compra", "comprar pan tostado", true, android.R.color.holo_blue_light));
            noteList.add(new Note("Recordar", "He aparcado el coche en la calle República Argentina, no olvidarme de pagar en el parquímetro", false, android.R.color.holo_green_light));
            noteList.add(new Note("Cumpleaños (fiesta)", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.", true, android.R.color.holo_orange_light));
            noteList.add(new Note("Lista de la compra", "comprar pan tostado", true, android.R.color.holo_blue_light));
            noteList.add(new Note("Recordar", "He aparcado el coche en la calle República Argentina, no olvidarme de pagar en el parquímetro", false, android.R.color.holo_green_light));
            noteList.add(new Note("Cumpleaños (fiesta)", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.", true, android.R.color.holo_orange_light));
            noteList.add(new Note("Lista de la compra", "comprar pan tostado", true, android.R.color.holo_blue_light));
            noteList.add(new Note("Recordar", "He aparcado el coche en la calle República Argentina, no olvidarme de pagar en el parquímetro", false, android.R.color.holo_green_light));
            noteList.add(new Note("Cumpleaños (fiesta)", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.", true, android.R.color.holo_orange_light));
            noteList.add(new Note("Lista de la compra", "comprar pan tostado", true, android.R.color.holo_blue_light));
            noteList.add(new Note("Recordar", "He aparcado el coche en la calle República Argentina, no olvidarme de pagar en el parquímetro", false, android.R.color.holo_green_light));
            noteList.add(new Note("Cumpleaños (fiesta)", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.", true, android.R.color.holo_orange_light));

            adapterNotes = new MyNoteRecyclerViewAdapter(noteList, getActivity());
            recyclerView.setAdapter(adapterNotes);
        }
        return view;
    }
}