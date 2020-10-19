package com.jmruiz.NoteApp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDAO {
    @Insert
    void insert(com.jmruiz.NoteApp.NoteEntity note);

    @Update
    void update(com.jmruiz.NoteApp.NoteEntity note);

    @Query("DELETE FROM notes")
    void deleteAll();

    @Query("DELETE FROM notes WHERE id = :idNote")
    void deleteById(int idNote);

    @Query("SELECT * FROM notes ORDER BY title ASC")
    LiveData<List<com.jmruiz.NoteApp.NoteEntity>> getAll();

    @Query("SELECT * FROM notes WHERE favorite LIKE 'true'")
    LiveData<List<com.jmruiz.NoteApp.NoteEntity>> getAllFavorites();
}
