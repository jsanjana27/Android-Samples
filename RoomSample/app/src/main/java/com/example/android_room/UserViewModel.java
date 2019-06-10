package com.example.android_room;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class UserViewModel extends ViewModel {
    private UserRepository mRepository;

    private LiveData<List<DatabaseModel>> mAllUsers;


    public UserViewModel() {

        mRepository = new UserRepository();
        mAllUsers = mRepository.getAllDetails();
    }

    LiveData<List<DatabaseModel>> getAllUsers() {
        return mAllUsers;
    }

    public void insert(DatabaseModel dbModel) {
        mRepository.insert(dbModel);
    }

    public void deleteAll() {
        mRepository.deleteAll();
    }

    public DatabaseModel getDetailsById(long id) {
        return mRepository.getDetailsById(id);
    }

    public void updateById(DatabaseModel dbModel) {
         mRepository.updateDetails(dbModel);
    }

    public void deleteById(DatabaseModel dbModel) {
         mRepository.deleteById(dbModel);
    }
}
