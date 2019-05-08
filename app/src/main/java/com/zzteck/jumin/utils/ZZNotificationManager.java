package com.zzteck.jumin.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.annotation.StringDef;


import com.zzteck.jumin.R;
import com.zzteck.jumin.ui.mainui.MainActivity;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static android.app.NotificationManager.IMPORTANCE_DEFAULT;

/**
 *
 *
 */

public class ZZNotificationManager {

    private static final String GROUP_TED_PARK = "tedPark";

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void createChannel(Context context) {

        NotificationChannelGroup group1 = new NotificationChannelGroup(GROUP_TED_PARK, GROUP_TED_PARK);
        getManager(context).createNotificationChannelGroup(group1);

        NotificationChannel channelComment = new NotificationChannel(Channel.COMMENT,
                context.getString(R.string.app_name), IMPORTANCE_DEFAULT);
        channelComment.setDescription(context.getString(R.string.app_name));
        channelComment.setGroup(GROUP_TED_PARK);
        channelComment.setLightColor(Color.BLUE);
        channelComment.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
        getManager(context).createNotificationChannel(channelComment);

    }

    private static android.app.NotificationManager getManager(Context context) {
        return (android.app.NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void deleteChannel(Context context, @Channel String channel) {
        getManager(context).deleteNotificationChannel(channel);

    }

    private static Notification.Builder mBuilder ;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void sendNotification(Context context, int id, @Channel String channel, String title, String body) {

        Intent notificationIntent = new Intent(context, MainActivity.class); //点击该通知后要跳转的Activity

        mBuilder = new Notification.Builder(context, channel)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(getSmallIcon(),10000)
                .setAutoCancel(true)
                .setContentIntent(PendingIntent.getActivity(context, 1, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT));
        mBuilder.setProgress(100, 0, false);
        getManager(context).notify(id, mBuilder.build());

    }

    public static void setProgress(Context context,int progress,int id){
        mBuilder.setProgress(100, progress, false);
        getManager(context).notify(id, mBuilder.build());
    }

    private static int getSmallIcon() {
        return R.mipmap.ic_launcher;
    }

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({
            Channel.MESSAGE,
            Channel.COMMENT,
            Channel.NOTICE
    })

    public @interface Channel {
        String MESSAGE = "message";
        String COMMENT = "comment";
        String NOTICE = "notice";
    }

}
