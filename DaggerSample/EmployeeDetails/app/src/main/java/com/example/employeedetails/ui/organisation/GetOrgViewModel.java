package com.example.employeedetails.ui.organisation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.employeedetails.data.model.Organisation;

import java.util.List;

public class GetOrgViewModel extends ViewModel {

    private OrganisationRepository organisationRepository = new OrganisationRepository();
    public LiveData<List<Organisation>> getAllOrgs() {
        return organisationRepository.getAllOrgs();
    }

    public LiveData<Organisation> getOrgById(String id) {
        return organisationRepository.getOrgById(id);
    }
}
