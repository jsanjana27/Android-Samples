package com.example.employeedetails.ui.organisation;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.employeedetails.R;
import com.example.employeedetails.data.model.Organisation;
import com.example.employeedetails.databinding.ActivityOrgUpdateBinding;
import com.example.employeedetails.ui.common.BaseActivity;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class OrgUpdateActivity extends BaseActivity {
    String orgId;
    ActivityOrgUpdateBinding binding;
    private UpdateOrgViewModel updateOrgViewModel = new UpdateOrgViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_org_update);
        binding.setViewModel(updateOrgViewModel);
        setToolBarAndBackButton();

        Bundle bundle = getIntent().getExtras();
        orgId = bundle.getString("id");
        Log.d("ID", "onCreate: " + orgId);

        final EditText orgName = (EditText) findViewById(R.id.orgName);


        updateOrgViewModel.getOrgById(orgId).observe(this, new Observer<Organisation>() {
            @Override
            public void onChanged(Organisation organisation) {
                if (organisation != null) {
                    updateOrgViewModel.onUpdateOrg(organisation);
                }
            }
        });

        updateOrgViewModel.updateResponse.observe(OrgUpdateActivity.this, employee1 -> {
            Toast.makeText(OrgUpdateActivity.this, "Updated successfully!", Toast.LENGTH_SHORT).show();

            finish();

        });

        Button buttonDelete = findViewById(R.id.delete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(OrgUpdateActivity.this);
                builder.setMessage("Do you want to delete this record?")
                        .setTitle("Delete");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        String OrgName = orgName.getText().toString();

                        Organisation updateOrg = new Organisation();

                        updateOrg.setOrgId(orgId);

                        updateOrgViewModel.deleteOrgById(orgId);

                        Toast.makeText(OrgUpdateActivity.this, "Deleted successfully!", Toast.LENGTH_SHORT).show();
                        setResult(1000);
                        finish();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(OrgUpdateActivity.this, "Couldn't delete", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
}
