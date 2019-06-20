package com.example.android_room.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.android_room.data.UserRepository;
import com.example.android_room.data.model.DatabaseModel;

import java.util.List;

public class UserViewModel extends ViewModel {
    private UserRepository mRepository;

    private LiveData<List<DatabaseModel>> mAllUsers;


    public UserViewModel() {

        mRepository = new UserRepository();
        mAllUsers = mRepository.getAllDetails();
    }

    public LiveData<List<DatabaseModel>> getAllUsers() {
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
