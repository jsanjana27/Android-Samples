package com.example.employeedetails.ui.organisation;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.employeedetails.R;
import com.example.employeedetails.data.model.Organisation;
import com.example.employeedetails.databinding.ActivityOrgDetailBinding;
import com.example.employeedetails.ui.common.BaseActivity;

public class OrgDetailActivity extends BaseActivity {
    private static final int ON_DELETE = 1;
    String id;
    ActivityOrgDetailBinding binding;
    private AddOrganisationViewModel addOrganisationViewModel = new AddOrganisationViewModel();
    private GetOrgViewModel getOrgViewModel = new GetOrgViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_org_detail);

        setToolBarAndBackButton();

        Bundle bundle = getIntent().getExtras();
        id = bundle.getString("id");
    }

    @Override
    protected void onStart() {
        super.onStart();

        getOrgViewModel.getOrgById(id).observe(this, new Observer<Organisation>() {
            @Override
            public void onChanged(Organisation organisation) {
                if (organisation != null) {
                    binding.setOrg(organisation);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_icon, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuId = item.getItemId();

        if (menuId == R.id.edit) {
            Intent intent = new Intent(OrgDetailActivity.this, OrgUpdateActivity.class);
            Bundle bundle = new Bundle();

            bundle.putString("id", id);
            intent.putExtras(bundle);
            startActivityForResult(intent, ON_DELETE);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ON_DELETE && resultCode == 1000) {
            finish();
        }
    }
}
