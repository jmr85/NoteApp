package com.jmruiz.NoteApp.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.jmruiz.NoteApp.db.entity.NoteEntity;
import com.jmruiz.DataRepository;

import java.util.List;

public class NewNoteDialogViewModel extends AndroidViewModel {
    private LiveData<List<NoteEntity>> allNotes;
    private LiveData<List<NoteEntity>> allNotesFavorites;
    private LiveData<NoteEntity> note;
    protected  int id;
    private DataRepository DataRepository;

    public NewNoteDialogViewModel(Application application) {
        super(application);

        DataRepository = new DataRepository(application);
        allNotes = DataRepository.getAll();
        allNotesFavorites = DataRepository.getAllNotesFavorites();
        note = DataRepository.getNote(id);
    }

    // El fragment que necesita recibir la nueva lista de datos
    public LiveData<List<NoteEntity>> getAllNotes() { return allNotes; }

    public LiveData<List<NoteEntity>> getAllNotesFavorites() { return allNotesFavorites; }

    public LiveData<NoteEntity> getNote(int id) { return note; }

    // El fragment que inserte una nueva nota, deberá comunicarlo a este ViewModel
    public void insertNote(NoteEntity newNoteEntity) { DataRepository.insert(newNoteEntity); }

    // El fragment que actualice la nota, deberá comunicarlo a este ViewModel
    public void updateNote(NoteEntity noteEntity) { DataRepository.update(noteEntity); }

}
