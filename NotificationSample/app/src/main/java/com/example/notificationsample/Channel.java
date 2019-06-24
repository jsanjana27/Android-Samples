package com.example.notificationsample;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class Channel {
    private String channelName;
    private String channelDescription;

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelDescription() {
        return channelDescription;
    }

    public void setChannelDescription(String channelDescription) {
        this.channelDescription = channelDescription;
    }
}
