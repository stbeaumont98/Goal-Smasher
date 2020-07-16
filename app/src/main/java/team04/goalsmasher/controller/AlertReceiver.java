package team04.goalsmasher.controller;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import team04.goalsmasher.R;

/* This code allows our app to send notifications in the
 * background while our app isn't necessarily running */
public class AlertReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        createNotification(context, "Check Goal", "Time to Review Goal Progress",
                "Alert");
    }

    public void createNotification(Context context, String msg, String msgText, String msgAlert)
    {
        PendingIntent notificationIntent = PendingIntent.getActivity(context, 0,
                new Intent(context, ShowGoalProgress.class), 0);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, "Goal-Smasher")
                .setContentTitle(msg)
                .setTicker(msgAlert)
                .setContentText(msgText)
                .setSmallIcon(R.drawable.ic_baseline_alarm_24)
                .setContentIntent(notificationIntent)
                .setDefaults(NotificationCompat.DEFAULT_SOUND | NotificationCompat.DEFAULT_VIBRATE)
                .setAutoCancel(true);

        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(1, mBuilder.build());
    }

}
