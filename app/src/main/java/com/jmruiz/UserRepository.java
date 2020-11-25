package com.jmruiz;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;

import com.jmruiz.NoteApp.db.NoteDatabase;
import com.jmruiz.NoteApp.db.dao.UserDao;
import com.jmruiz.NoteApp.db.entity.UserEntity;

import java.util.List;

public class UserRepository {

    private UserDao userDao;
    private LiveData<UserEntity> user;
    protected int id;

    public UserRepository(Application application) {
        NoteDatabase db = NoteDatabase.getDatabase(application);
        userDao = db.userDao();
        user = userDao.getById(id);
    }

    public LiveData<UserEntity> getUser(int id) { return user; }

    public void insert(UserEntity user) {
        NoteDatabase.databaseWriteExecutor.execute(() -> {
            userDao.insert(user);
        });
    }

    public void update(UserEntity user) {
        NoteDatabase.databaseWriteExecutor.execute(() -> {
            userDao.update(user);
        });
    }

    public void deleteById(UserEntity user) {
        NoteDatabase.databaseWriteExecutor.execute(() -> {
            userDao.deleteById(user);
        });
    }
}
