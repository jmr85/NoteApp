package com.jmruiz.NoteApp.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.jmruiz.NoteApp.R;
import com.jmruiz.NoteApp.db.entity.NoteEntity;
import com.jmruiz.NoteApp.viewmodel.DeleteAllNotesDialogViewModel;
import com.jmruiz.NoteApp.viewmodel.NewNoteDialogViewModel;

public class DeleteAllNotesDialogFragment extends DialogFragment {

    private DeleteAllNotesDialogViewModel mViewModel;
    private View view;

    public static DeleteAllNotesDialogFragment newInstance() {
        return new DeleteAllNotesDialogFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Delete All Notes");
        builder.setMessage("Are you sure you want to delete all notes?")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Comunicar al ViewModel la eliminacion de todas las notas.
                        mViewModel = new ViewModelProvider(getActivity())
                                .get(DeleteAllNotesDialogViewModel.class);
                        mViewModel.deleteAllNotes();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        dialog.dismiss();
                    }
                });

        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.delete_all_notes_dialog_fragment, null);


        builder.setView(view);

        // Create the AlertDialog object and return it
        return builder.create();
    }

}
