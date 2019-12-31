package com.example.employeedetails.ui.organisation;

import android.text.TextUtils;
import android.util.Log;

import androidx.arch.core.util.Function;
import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.employeedetails.data.model.Employee;
import com.example.employeedetails.data.model.Organisation;
import com.example.employeedetails.data.model.Task;


public class UpdateOrgViewModel extends ViewModel {

    public ObservableField<String> orgId = new ObservableField<String>();
    public ObservableField<String> name = new ObservableField<String>();
    public ObservableField<String> nameError = new ObservableField<String>();
    public ObservableField<Boolean> isActive = new ObservableField<Boolean>();

    MutableLiveData<Organisation> organisation = new MutableLiveData<>();
    String id = null;
    private OrganisationRepository organisationRepository = new OrganisationRepository();
    LiveData<Organisation> updateResponse = Transformations.switchMap(organisation, new Function<Organisation, LiveData<Organisation>>() {
        @Override
        public LiveData<Organisation> apply(Organisation input) {
            return organisationRepository.updateOrgDetails(input);
        }
    });

    public LiveData<Organisation> getOrgById(String id) {
        this.id = id;
        return organisationRepository.getOrgById(id);
    }

    public LiveData<Organisation> updateOrgDetails(Organisation organisation) {
        return organisationRepository.updateOrgDetails(organisation);
    }

    public LiveData<Boolean> deleteOrgById(String id) {
        return organisationRepository.deleteOrgById(id);
    }

    public void onOrgUpdate() {
        if (TextUtils.isEmpty(name.get())) {
            nameError.set("Please enter org name");

            return;
        }
        nameError.set(null);


        Organisation organisation = new Organisation();

        organisation.setName(name.get());
        organisation.setPrimaryId(Long.parseLong(orgId.get()));
        organisation.setOrgId(id);
        organisation.setActive(isActive.get());

        this.organisation.setValue(organisation);
    }

    public void onUpdateOrg(Organisation organisation) {
        name.set(organisation.getName());
        orgId.set(organisation.getPrimaryId() + "");
        isActive.set(organisation.getActive());
    }
}
