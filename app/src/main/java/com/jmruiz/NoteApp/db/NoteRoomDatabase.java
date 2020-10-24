package com.jmruiz.NoteApp.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.jmruiz.NoteApp.db.dao.NoteDao;
import com.jmruiz.NoteApp.db.entity.NoteEntity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {NoteEntity.class}, version = 1, exportSchema = false)
public abstract class NoteRoomDatabase  extends RoomDatabase {

    public abstract NoteDao noteDao();

    private static volatile NoteRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static NoteRoomDatabase getDatabase(final Context context) {
        if(INSTANCE == null) {
            synchronized (NoteRoomDatabase.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NoteRoomDatabase.class, "notes_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
