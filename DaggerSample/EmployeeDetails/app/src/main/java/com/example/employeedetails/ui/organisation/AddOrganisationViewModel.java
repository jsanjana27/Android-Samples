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

import java.util.List;

public class AddOrganisationViewModel extends ViewModel {

    public ObservableField<String> orgId = new ObservableField<String>();
    public ObservableField<String> orgIdError = new ObservableField<String>();
    public ObservableField<String> name = new ObservableField<String>();
    public ObservableField<String> nameError = new ObservableField<String>();
    public ObservableField<Boolean> isActive = new ObservableField<Boolean>();


    MutableLiveData<Organisation> organisation = new MutableLiveData<>();
    private OrganisationRepository organisationRepository = new OrganisationRepository();

    LiveData<Organisation> addResponse = Transformations.switchMap(organisation, new Function<Organisation, LiveData<Organisation>>() {
        @Override
        public LiveData<Organisation> apply(Organisation input) {
            return organisationRepository.addOrg(input);
        }
    });


    public void onAddOrgClicked() {
        if (TextUtils.isEmpty(orgId.get())) {
            orgIdError.set("Please enter org id");
            return;
        }
        orgIdError.set(null);
        if (TextUtils.isEmpty(name.get())) {
            nameError.set("Please enter org name");

            return;
        }
        nameError.set(null);


        Organisation organisation = new Organisation();

        organisation.setName(name.get());
        organisation.setPrimaryId(Long.parseLong(orgId.get()));

        organisation.setActive(isActive.get());

        this.organisation.setValue(organisation);

    }

}
