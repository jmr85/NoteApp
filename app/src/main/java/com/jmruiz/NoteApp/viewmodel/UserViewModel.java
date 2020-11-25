package com.jmruiz.NoteApp.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.jmruiz.NoteApp.db.entity.UserEntity;
import com.jmruiz.UserRepository;

public class UserViewModel extends AndroidViewModel {

    private LiveData<UserEntity> user;
    protected  int id;
    private UserRepository UserRepository;

    public UserViewModel(Application application) {
        super(application);
        UserRepository = new UserRepository(application);
        user = UserRepository.getUser(id);
    }

    public LiveData<UserEntity> getUser(int id) { return user; }

    // El fragment que inserte una nueva nota, deberá comunicarlo a este ViewModel
    public void insertUser(UserEntity newUserEntity) { UserRepository.insert(newUserEntity); }

    // El fragment que actualice la nota, deberá comunicarlo a este ViewModel
    public void updateUser(UserEntity userEntity) { UserRepository.update(userEntity); }

    public void deleteUserById(UserEntity newUserEntity) { UserRepository.deleteById(newUserEntity); }

}