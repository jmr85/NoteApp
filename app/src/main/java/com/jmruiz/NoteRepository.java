package com.jmruiz;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.jmruiz.NoteApp.db.NoteRoomDatabase;
import com.jmruiz.NoteApp.db.dao.NoteDao;
import com.jmruiz.NoteApp.db.entity.NoteEntity;

import java.util.List;

public class NoteRepository {

    private NoteDao noteDao;
    private LiveData<List<NoteEntity>> allNotes;
    private LiveData<List<NoteEntity>> allNotesFavorites;

    public NoteRepository(Application application) {
        NoteRoomDatabase db = NoteRoomDatabase.getDatabase(application);
        noteDao = db.noteDao();
        allNotes = noteDao.getAll();
        allNotesFavorites = noteDao.getAllFavorites();
    }

    public LiveData<List<NoteEntity>> getAll() { return allNotes; }

    public LiveData<List<NoteEntity>> getAllNotesFavorites() { return allNotesFavorites; }

    public void insert(NoteEntity note) {
        NoteRoomDatabase.databaseWriteExecutor.execute(() -> {
            noteDao.insert(note);
        });
    }

    public void update(NoteEntity note) {
        NoteRoomDatabase.databaseWriteExecutor.execute(() -> {
            noteDao.update(note);
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
