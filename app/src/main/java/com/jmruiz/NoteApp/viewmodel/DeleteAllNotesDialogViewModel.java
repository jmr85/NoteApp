package com.jmruiz.NoteApp.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.jmruiz.NoteApp.db.entity.NoteEntity;
import com.jmruiz.DataRepository;

import java.util.List;

public class DeleteAllNotesDialogViewModel extends AndroidViewModel {

    private DataRepository DataRepository;

    public DeleteAllNotesDialogViewModel(Application application) {
        super(application);

        DataRepository = new DataRepository(application);
    }

    // El fragment que borre todas las notas, deberá comunicarlo a este ViewModel
    public void deleteAllNotes() { DataRepository.deleteAll(); }

}
