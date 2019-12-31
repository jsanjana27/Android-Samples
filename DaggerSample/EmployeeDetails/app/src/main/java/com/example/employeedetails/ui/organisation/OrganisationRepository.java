package com.example.employeedetails.ui.organisation;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.employeedetails.data.common.ApiHandler;
import com.example.employeedetails.data.local.RoomDatabase;
import com.example.employeedetails.data.model.Employee;
import com.example.employeedetails.data.model.Organisation;
import com.example.employeedetails.ui.employee.EmployeeRepository;
import com.example.employeedetails.util.PreferenceUtil;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrganisationRepository {
    private static final String TAG = EmployeeRepository.class.getSimpleName();
    private LiveData<List<Organisation>> mOrganisation;

    public LiveData<Organisation> addOrg(Organisation organisation) {
        MutableLiveData<Organisation> result = new MutableLiveData<Organisation>();

        HashMap<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        header.put("Accept", "application/json");
        header.put("authorization", "Bearer " + PreferenceUtil.getInstance().getEncStringValue("token", ""));
        Call<Organisation> call = ApiHandler.getService().addOrganisation(header, organisation);

        call.enqueue(new Callback<Organisation>() {
            @Override
            public void onResponse(Call<Organisation> call, Response<Organisation> response) {
                if (response.isSuccessful()) {
                    Log.d("test", "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                    RoomDatabase.getInstance().organisationDao().addOrganisation(organisation);
                    Organisation organisation = response.body();
                    result.postValue(organisation);
                }
            }

            @Override
            public void onFailure(Call<Organisation> call, Throwable t) {
                Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
                result.postValue(organisation);
            }
        });
        return result;

    }

    public LiveData<List<Organisation>> getAllOrgs() {
        MutableLiveData<List<Organisation>> result = new MutableLiveData<List<Organisation>>();

        HashMap<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        header.put("Accept", "application/json");
        header.put("authorization", "Bearer " + PreferenceUtil.getInstance().getEncStringValue("token", ""));

        Call<List<Organisation>> call = ApiHandler.getService().getAllOrgs(header);

        call.enqueue(new Callback<List<Organisation>>() {
            @Override
            public void onResponse(Call<List<Organisation>> call, Response<List<Organisation>> response) {
                if (response.isSuccessful()) {
                    RoomDatabase.getInstance().organisationDao().deleteAll();

                    List<Organisation> organisations = response.body();
                    RoomDatabase.getInstance().organisationDao().addAllOrgs(organisations);
                    result.setValue(organisations);
                }
            }

            @Override
            public void onFailure(Call<List<Organisation>> call, Throwable t) {
                RoomDatabase.getInstance().organisationDao().getAllOrgs();

                result.postValue(null);
            }
        });
        return result;
    }

    public LiveData<Organisation> updateOrgDetails(Organisation organisation) {
        MutableLiveData<Organisation> result = new MutableLiveData<Organisation>();

        HashMap<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        header.put("Accept", "application/json");
        header.put("authorization", "Bearer " + PreferenceUtil.getInstance().getEncStringValue("token", ""));

        Call<Organisation> call = ApiHandler.getService().updateOrgDetails(header, organisation.getOrgId(), organisation);

        call.enqueue(new Callback<Organisation>() {
            @Override
            public void onResponse(Call<Organisation> call, Response<Organisation> response) {
                if (response.isSuccessful()) {
                    RoomDatabase.getInstance().organisationDao().update(organisation);
                    result.postValue(organisation);
                }
            }

            @Override
            public void onFailure(Call<Organisation> call, Throwable t) {
                result.postValue(null);
            }
        });
        return result;
    }

    public LiveData<Organisation> getOrgById(String id) {

        return RoomDatabase.getInstance().organisationDao().getOrgDetailsAsLiveData(id);

    }

    public LiveData<Boolean> deleteOrgById(String id) {
        MutableLiveData<Boolean> result = new MutableLiveData<Boolean>();

        HashMap<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        header.put("Accept", "application/json");
        header.put("authorization", "Bearer " + PreferenceUtil.getInstance().getEncStringValue("token", ""));


        Call<Organisation> call = ApiHandler.getService().deleteOrgById(header, id);

        call.enqueue(new Callback<Organisation>() {
            @Override
            public void onResponse(Call<Organisation> call, Response<Organisation> response) {
                if (response.isSuccessful()) {
                    Organisation organisation = RoomDatabase.getInstance().organisationDao().getOrgById(id);
                    RoomDatabase.getInstance().organisationDao().delete(organisation);
                    result.postValue(true);
                }
            }

            @Override
            public void onFailure(Call<Organisation> call, Throwable t) {
                result.postValue(false);
            }
        });
        return result;
    }
}
