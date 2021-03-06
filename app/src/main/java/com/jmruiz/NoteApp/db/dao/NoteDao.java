package com.jmruiz.NoteApp.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.jmruiz.NoteApp.db.entity.NoteEntity;

import java.util.List;

@Dao
public interface NoteDao {
    @Insert
    void insert(NoteEntity note);

    @Update
    void update(NoteEntity note);

    @Query("DELETE FROM notes")
    void deleteAll();

    @Delete
    void deleteById(NoteEntity note);

    @Query("SELECT * FROM notes WHERE id = :idNote")
    LiveData<NoteEntity> getById(int idNote);

    @Query("SELECT * FROM notes ORDER BY created_at DESC")
    LiveData<List<NoteEntity>> getAll();

    @Query("SELECT * FROM notes WHERE favorite LIKE 'true'")
    LiveData<List<NoteEntity>> getAllFavorites();
}
