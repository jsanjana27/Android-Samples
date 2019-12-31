package com.example.employeedetails.ui.organisation;

import android.os.Bundle;

import com.example.employeedetails.data.model.Organisation;
import com.example.employeedetails.databinding.ActivityAddOrgBinding;
import com.example.employeedetails.ui.common.BaseActivity;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import com.example.employeedetails.R;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputEditText;

public class AddOrgActivity extends BaseActivity {
    ActivityAddOrgBinding binding;
    private AddOrganisationViewModel addOrganisationViewModel = new AddOrganisationViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_org);
        binding.setViewModel(addOrganisationViewModel);

        setToolBarAndBackButton();

        addOrganisationViewModel.addResponse.observe(AddOrgActivity.this, new Observer<Organisation>() {
            @Override
            public void onChanged(Organisation organisation) {
                if (organisation == null) {
                    Toast.makeText(getApplicationContext(), R.string.add_failed, Toast.LENGTH_SHORT).show();
                    return;
                }
                finish();
            }
        });
    }

}
