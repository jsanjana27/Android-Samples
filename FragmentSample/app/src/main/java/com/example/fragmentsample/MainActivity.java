package com.example.fragmentsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArticleReaderFragment articleReaderFragment =  new ArticleReaderFragment();

        Bundle bundle = new Bundle();
        articleReaderFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, articleReaderFragment).commit();
    }
}
