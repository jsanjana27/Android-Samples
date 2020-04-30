package com.example.qrcodescanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.rmkrishna.permissionX.MPermission;
import com.rmkrishna.permissionX.MPermissionListener;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    String[] PERMISSIONS = {
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.CAMERA
    };
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.scan);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openScanner();
            }
        });
    }

    private void openScanner() {
        MPermission.askPermissions(this, PERMISSIONS, new MPermissionListener() {

            @Override
            public void neverAskAgain(@NotNull List<String> permissions) {
                Toast.makeText(MainActivity.this, getText(R.string.camera_permission_denied), Toast.LENGTH_LONG).show();
            }

            @Override
            public void denied(@NotNull List<String> permissions) {
                Toast.makeText(MainActivity.this, getText(R.string.camera_permission_denied), Toast.LENGTH_LONG).show();
                // If the user denied the permission(or 's)
            }

            @Override
            public void granted() {
                Intent intent = new Intent(MainActivity.this, ScanActivity.class);
                startActivityForResult(intent, 300);
                // If permission(or 's) granted successfully
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 300 && resultCode == 1000) {
            TextView textView = findViewById(R.id.data);
            textView.setText(data.getStringExtra("value"));
        }

        super.onActivityResult(requestCode, resultCode, data);

    }
}
