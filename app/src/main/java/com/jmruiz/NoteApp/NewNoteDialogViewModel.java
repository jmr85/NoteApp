package com.jmruiz.NoteApp;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.jmruiz.NoteApp.db.entity.NoteEntity;
import com.jmruiz.NoteRepository;

import java.util.List;

public class NewNoteDialogViewModel extends AndroidViewModel {
    private LiveData<List<NoteEntity>> allNotes;
    private NoteRepository noteRepository;

    public NewNoteDialogViewModel(Application application) {
        super(application);

        noteRepository = new NoteRepository(application);
        allNotes = noteRepository.getAll();
    }

    // El fragment que necesita recibir la nueva lista de datos
    public LiveData<List<NoteEntity>> getAllNotes() { return allNotes; }

    // El fragment que inserte una nueva nota, deberá comunicarlo a este ViewModel
    public void insertNote(NoteEntity nuevaNotaEntity) { noteRepository.insert(nuevaNotaEntity); }
}
