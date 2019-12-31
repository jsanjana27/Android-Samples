package com.example.background;

public final class Constants {


    public static final CharSequence VERBOSE_NOTIFICATION_CHANNEL_NAME =
            "Verbose WorkManager Notifications";
    public static final CharSequence NOTIFICATION_TITLE = "WorkRequest Starting";
    public static final String CHANNEL_ID = "VERBOSE_NOTIFICATION";
    public static final int NOTIFICATION_ID = 1;
    public static final String OUTPUT_PATH = "blur_filter_outputs";
    public static final String KEY_IMAGE_URI = "KEY_IMAGE_URI";
    public static final long DELAY_TIME_MILLIS = 3000;
    static final String IMAGE_MANIPULATION_WORK_NAME = "image_manipulation_work";
    static final String TAG_OUTPUT = "OUTPUT";
    public static String VERBOSE_NOTIFICATION_CHANNEL_DESCRIPTION =
            "Shows notifications whenever work starts";

    private Constants() {
    }
}