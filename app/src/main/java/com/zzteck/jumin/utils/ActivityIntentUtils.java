package com.zzteck.jumin.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 *
 */
public class ActivityIntentUtils {


    public static void jumpActivitySourceExtra(Context context,Class<?> cls,String extra){
        Intent facebookIntent = new Intent(context,cls);
        facebookIntent.putExtra("extra",extra) ;
        facebookIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(facebookIntent);
    }

    public static void jumpActivity(Context context, Class<?> cls) {
        Intent facebookIntent = new Intent(context,cls);
        facebookIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(facebookIntent);
    }

    public static void jumpActivity2(Context context,Class<?> cls) {
        Intent facebookIntent = new Intent(context,cls);
        facebookIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(facebookIntent);
    }

    public static void jumpActivityExtra(Context context,Class<?> cls,String extra){
        Intent facebookIntent = new Intent(context,cls);
        facebookIntent.putExtra("extra",extra) ;
        facebookIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(facebookIntent);
    }
    public static void jumpActivityExtra(Context context , Class<?> cls,String extra,String extra1){
        Intent facebookIntent = new Intent(context,cls);
        facebookIntent.putExtra("extra",extra) ;
        facebookIntent.putExtra("extra1",extra1) ;
        facebookIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(facebookIntent);
    }

    public static void jumpActivityExtra(Context context ,Class<?> cls,Bundle extra){
        Intent facebookIntent = new Intent(context,cls);
        facebookIntent.putExtra("extra",extra) ;
        facebookIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(facebookIntent);
    }
}
