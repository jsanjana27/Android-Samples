package com.example.notificationsample;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    public static final int notificationId = 1;
    String CHANNEL_ID = "channel";
    String emails[] = {"carey@gmail.com", "jon@gmail.com", "mary@gmail.com", "justin@gmail.com", "ali@gmail.com"};
    String descriptions[] = {"It's Friday!", "Game tomorrow?", "Friday sale", "How did your week go?", "Vacation at Bali"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Intent intent = new Intent(this, AlertDetails.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        final PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);


        final Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                for (int i = 0; i < emails.length; i++) {
                NotificationCompat.MessagingStyle.Message message1 = new NotificationCompat.MessagingStyle.Message(emails[0], System.currentTimeMillis(), "");

                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.email_icon)
                        .setContentTitle("Mails")
                        .setContentText("5 mails")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

                NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();

                for (int i = 0; i < emails.length; i++) {
                    inboxStyle.addLine(emails[i] + " : Message " + i);
                }

                builder.setStyle(inboxStyle);
//                        .setContentIntent(pendingIntent);
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MainActivity.this);
                notificationManager.notify(notificationId, builder.build());
            }
//            }
        });
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            android.app.NotificationChannel channel = new android.app.NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
