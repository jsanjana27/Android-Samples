package com.example.background.workers;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.background.Constants;
import com.example.background.R;

public class BlurWorker extends Worker {

    private static final String TAG = BlurWorker.class.getSimpleName();

    public BlurWorker(Context context, WorkerParameters workerParameters) {
        super(context, workerParameters);
    }

    @NonNull
    @Override
    public Result doWork() {
        Context applicationContext = getApplicationContext();

        String resourceUri = getInputData().getString(Constants.KEY_IMAGE_URI);
        Log.d(TAG, "doWork: " + resourceUri);

        try {
//            Bitmap picture = BitmapFactory.decodeResource(
//                    applicationContext.getResources(),
//                    R.drawable.test);

            if (TextUtils.isEmpty(resourceUri)) {
                Log.e(TAG, "doWork: Invalid input URI");
                throw new IllegalArgumentException("Invalid input URI");
            }

            ContentResolver resolver = applicationContext.getContentResolver();
            Bitmap picture = BitmapFactory.decodeStream(
                    resolver.openInputStream(Uri.parse(resourceUri))
            );

            Bitmap output = WorkerUtils.blurBitmap(picture, applicationContext);

            Uri outputUri = WorkerUtils.writeBitmapToFile(applicationContext, output);
            Data outputData = new Data.Builder()
                    .putString(Constants.KEY_IMAGE_URI, outputUri.toString())
                    .build();

            WorkerUtils.makeStatusNotification("Output is "
                    + outputUri.toString(), applicationContext);

            return Result.success(outputData);
        } catch (Throwable throwable) {

            Log.e(TAG, "Error applying blur", throwable);
            return Result.failure();
        }
    }

}
