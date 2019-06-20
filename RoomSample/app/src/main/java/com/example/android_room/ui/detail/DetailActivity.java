package com.example.android_room.ui.detail;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.android_room.R;
import com.example.android_room.ui.common.BaseActivity;
import com.example.android_room.ui.userupdater.UpdateActivity;
import com.example.android_room.data.model.DatabaseModel;
import com.example.android_room.ui.UserViewModel;

public class DetailActivity extends BaseActivity {
    private static final int ON_DELETE = 1;
    long id;
    private UserViewModel muserViewModel = new UserViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        setToolBarAndBackButton();

        Bundle bundle = getIntent().getExtras();
        id = bundle.getLong("id");
    }

    @Override
    protected void onStart() {
        super.onStart();

        DatabaseModel dbModel = muserViewModel.getDetailsById(id);

        TextView nameTextView = findViewById(R.id.textView);
        TextView numberTextView = (TextView) findViewById(R.id.number);
        TextView emailTextView = (TextView) findViewById(R.id.email);
        TextView addressTextView = (TextView) findViewById(R.id.address);

        if (dbModel == null) {
            nameTextView.setText("");
            numberTextView.setText("");
            emailTextView.setText("");
            addressTextView.setText("");
            return;
        }

        nameTextView.setText(dbModel.getName());
        numberTextView.setText(dbModel.getEmail());
        emailTextView.setText(dbModel.getNumber());
        addressTextView.setText(dbModel.getAddress());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuId = item.getItemId();

        if (menuId == R.id.edit) {
            Intent intent = new Intent(DetailActivity.this, UpdateActivity.class);
            Bundle bundle = new Bundle();

            bundle.putLong("id", id);
            intent.putExtras(bundle);
            startActivityForResult(intent, ON_DELETE);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == ON_DELETE && resultCode == 1000) {
            finish();
        }
    }
}
