package com.example.glidesample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = (ImageView) findViewById(R.id.my_image_view);
        RequestOptions cropOptions = new RequestOptions().transform(new CircleCrop());

        Glide.with(this).

                load("https://www.telegraph.co.uk/content/dam/news/2019/05/16/TELEMMGLPICT000196666884_trans_NvBQzQNjv4BqpVlberWd9EgFPZtcLiMQfyf2A9a6I9YchsjMeADBa08.jpeg?imwidth=1400").apply(cropOptions).into(imageView);
    }


}
