package com.example.android_room;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class UserRepository {
    private UserDao mUserDao;
    private LiveData<List<DatabaseModel>> mAllUsers;

    UserRepository() {
        UserRoomDatabase db = UserRoomDatabase.getInstance();
        mUserDao = db.userDao();
        mAllUsers = mUserDao.getAllDetails();
    }

    LiveData<List<DatabaseModel>> getAllDetails() {
        return mAllUsers;
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

//    public static class getDetailsById extends AsyncTask<Long, Void, DatabaseModel> {
//        private UserDao mAsyncTaskDao;
//
//        getDetailsById(UserDao userDao) {
//            mAsyncTaskDao = userDao;
//        }
//
//        @Override
//        protected DatabaseModel doInBackground(Long... longs) {
//            return  mAsyncTaskDao.getDetailstById(longs[0]);
//        }
//    }

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

    public static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        public final UserDao mDao;

        PopulateDbAsync(UserRoomDatabase db) {
            mDao = db.userDao();
        }

        @Override
        protected Void doInBackground(final Void... voids) {
            mDao.deleteAll();

            DatabaseModel dbModel = new DatabaseModel();

            dbModel.setName("Sanjana");
            dbModel.setNumber("9972405373");
            dbModel.setEmail("jsanjana96@gmail.com");
            dbModel.setAddress("Bangalore");

            mDao.insert(dbModel);
            return null;
        }
    }
}