package com.example.android_room.data;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.android_room.data.local.UserRoomDatabase;
import com.example.android_room.data.local.dao.UserDao;
import com.example.android_room.data.model.DatabaseModel;

import java.util.List;

public class UserRepository {
    private UserDao mUserDao;
//    private LiveData<List<DatabaseModel>> mAllUsers;

    public UserRepository() {
        UserRoomDatabase db = UserRoomDatabase.getInstance();
        mUserDao = db.userDao();
//        mAllUsers = mUserDao.getAllDetails();
    }

    public LiveData<List<DatabaseModel>> getAllDetails() {
        return mUserDao.getAllDetails();
    }

    public void insert(DatabaseModel dbModel) {
        new insertAsyncTask(mUserDao).execute(dbModel);
    }

    public void deleteAll() {
        new deleteAllDataAsyncTask(mUserDao).execute();
    }

    public DatabaseModel getDetailsById(long id) {
        return mUserDao.getDetailstById(id);

    }

    public void updateDetails(DatabaseModel dbModel) {
        mUserDao.update(dbModel);
    }

    public void deleteById(DatabaseModel dbModel) {
        mUserDao.delete(dbModel);
    }

    public static class deleteAllDataAsyncTask extends AsyncTask<Void, Void, Void> {
        private UserDao mAsyncTaskDao;

        deleteAllDataAsyncTask(UserDao userDao) {
            mAsyncTaskDao = userDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

    public static class insertAsyncTask extends AsyncTask<DatabaseModel, Void, Void> {

        private UserDao mAsyncTaskDao;

        insertAsyncTask(UserDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final DatabaseModel... databaseModels) {
            mAsyncTaskDao.insert(databaseModels[0]);
            return null;
        }
    }
}