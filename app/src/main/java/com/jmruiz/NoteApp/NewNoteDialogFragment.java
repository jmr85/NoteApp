package com.jmruiz.NoteApp;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

public class NewNoteDialogFragment  extends DialogFragment {
    private NewNoteDialogViewModel mViewModel;

    public static NewNoteDialogFragment newInstance() {
        return new NewNoteDialogFragment();
    }
    private View view;
    private EditText editTextTitle, editTextContent;
    private RadioGroup radioGroupColor;
    private Switch switchNoteFavorite;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(NewNoteDialogViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("New note");
        builder.setMessage("Enter the details of the new note")
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String title = editTextTitle.getText().toString();
                        String content = editTextContent.getText().toString();
                        String color = "blue";
                        switch (radioGroupColor.getCheckedRadioButtonId()) {
                            case R.id.radioButtonColorRed:
                                color = "red"; break;
                            case R.id.radioButtonColorGreen:
                                color = "green"; break;
                        }

                        boolean isFavorite = switchNoteFavorite.isChecked();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });

        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.new_note_dialog_fragment, null);

        editTextTitle = view.findViewById(R.id.editTextTitle);
        editTextContent = view.findViewById(R.id.editTextContent);
        radioGroupColor = view.findViewById(R.id.radioGroupColor);
        switchNoteFavorite = view.findViewById(R.id.switchNoteFavorite);

        builder.setView(view);

        // Create the AlertDialog object and return it
        return builder.create();
    }
}
