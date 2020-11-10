package com.jmruiz.NoteApp.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.jmruiz.NoteApp.R;
import com.jmruiz.NoteApp.db.entity.NoteEntity;
import com.jmruiz.NoteApp.viewmodel.NewNoteDialogViewModel;

public class ShowNoteDialogFragment extends DialogFragment {

    private NewNoteDialogViewModel mViewModel;

    private String title;
    private String content;
    private boolean isFavorite;
    private View view;
    private EditText editTextTitle, editTextContent;
    private SwitchCompat switchNoteFavorite;

    public void setAdInfo(String title, String content, boolean isFavorite)
    {
        this.title = title;
        this.content = content;
        this.isFavorite = isFavorite;
    }

    public static ShowNoteDialogFragment newInstance() {
        return new ShowNoteDialogFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Update note");
        builder.setMessage("Enter the details of the update note")
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        title = editTextTitle.getText().toString();
                        content = editTextContent.getText().toString();

                        isFavorite = switchNoteFavorite.isChecked();

                        // Comunicar al ViewModel el nuevo dato.
                        mViewModel = new ViewModelProvider(getActivity())
                                .get(NewNoteDialogViewModel.class);
                        mViewModel.updateNote(new NoteEntity(title, content, isFavorite));
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
        view = inflater.inflate(R.layout.new_note_dialog_fragment, null);

        editTextTitle = view.findViewById(R.id.editTextTitle);
        editTextContent = view.findViewById(R.id.editTextContent);
        switchNoteFavorite = view.findViewById(R.id.switchNoteFavorite);

        editTextTitle.setText(this.getTitle());
        editTextContent.setText(this.getContent());
        switchNoteFavorite.setChecked(this.isFavorite());

        builder.setView(view);

        // Create the AlertDialog object and return it
        return builder.create();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
