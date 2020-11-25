package com.jmruiz.NoteApp.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.jmruiz.NoteApp.db.entity.NoteEntity;
import com.jmruiz.NoteApp.db.entity.UserEntity;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insert(UserEntity user);

    @Update
    void update(UserEntity user);

    @Delete
    void deleteById(UserEntity user);

    @Query("SELECT * FROM notes WHERE id = :idUser")
    LiveData<UserEntity> getById(int idUser);
}
