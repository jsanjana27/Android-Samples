package com.example.employeedetails.ui.organisation;

import com.example.employeedetails.data.model.Organisation;

public class OrganisationResponse {
    private Organisation org;
    private String token;

    public Organisation getOrg() {
        return org;
    }

    public void setOrg(Organisation org) {
        this.org = org;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
