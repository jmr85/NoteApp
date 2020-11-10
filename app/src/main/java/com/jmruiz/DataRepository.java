package com.jmruiz;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.jmruiz.NoteApp.db.NoteDatabase;
import com.jmruiz.NoteApp.db.dao.NoteDao;
import com.jmruiz.NoteApp.db.entity.NoteEntity;

import java.util.List;

public class DataRepository {

    private NoteDao noteDao;
    private LiveData<NoteEntity> note;
    protected int id;
    private LiveData<List<NoteEntity>> allNotes;
    private LiveData<List<NoteEntity>> allNotesFavorites;

    public DataRepository(Application application) {
        NoteDatabase db = NoteDatabase.getDatabase(application);
        noteDao = db.noteDao();
        allNotes = noteDao.getAll();
        allNotesFavorites = noteDao.getAllFavorites();
        note = noteDao.getById(id);
    }

    public LiveData<List<NoteEntity>> getAll() { return allNotes; }

    public LiveData<List<NoteEntity>> getAllNotesFavorites() { return allNotesFavorites; }

    public LiveData<NoteEntity> getNote(int id) { return note; }


    public void insert(NoteEntity note) {
        NoteDatabase.databaseWriteExecutor.execute(() -> {
            noteDao.insert(note);
        });
    }

    public void update(NoteEntity note) {
        NoteDatabase.databaseWriteExecutor.execute(() -> {
            noteDao.update(note);
        });
    }

    public void deleteAll() {
        NoteDatabase.databaseWriteExecutor.execute(() -> {
            noteDao.deleteAll();
        });
    }

    public void deleteById(NoteEntity note) {
        NoteDatabase.databaseWriteExecutor.execute(() -> {
            noteDao.deleteById(note);
        });
    }

//    public void insert (NoteEntity note) {
//        new insertAsyncTask(noteDao).execute(note);
//    }
//
//    private static class insertAsyncTask extends AsyncTask<NoteEntity, Void, Void> {
//        private NoteDao noteDaoAsyncTask;
//
//        insertAsyncTask(NoteDao dao) {
//            noteDaoAsyncTask = dao;
//        }
//
//        @Override
//        protected Void doInBackground(NoteEntity... noteEntities) {
//            noteDaoAsyncTask.insert(noteEntities[0]);
//            return null;
//        }
//    }
}
