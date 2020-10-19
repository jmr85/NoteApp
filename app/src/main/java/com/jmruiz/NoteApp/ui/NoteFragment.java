package com.jmruiz.NoteApp.ui;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.jmruiz.NoteApp.NewNoteDialogViewModel;
import com.jmruiz.NoteApp.R;
import com.jmruiz.NoteApp.db.entity.NoteEntity;

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
    private List<NoteEntity> noteEntityList;
    private MyNoteRecyclerViewAdapter adapterNotes;
    private NewNoteDialogViewModel noteViewModel;

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

            noteEntityList = new ArrayList<>();

            adapterNotes = new MyNoteRecyclerViewAdapter(noteEntityList, getActivity());
            recyclerView.setAdapter(adapterNotes);
        }
        return view;
    }
    private void launchViewModel() {
        noteViewModel = new ViewModelProvider(getActivity()).get(NewNoteDialogViewModel.class);
        noteViewModel.getAllNotes().observe(getActivity(), new Observer<List<NoteEntity>>() {
            @Override
            public void onChanged(@Nullable List<NoteEntity> noteEntities) {
                adapterNotes.setNewNotes(noteEntities);
            }
        });
    }
}