package com.example.codeblue;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class NotificationBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, "Medicine Notification Channel");
        notificationBuilder.setContentTitle("Its Time!").setVisibility(NotificationCompat.VISIBILITY_PRIVATE)
                .setContentText("Take Your Medicines")
                .setSmallIcon(R.drawable.baseline_alarm_24);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(10,notificationBuilder.build());
    }
}
