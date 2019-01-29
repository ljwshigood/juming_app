package com.zzteck.zzview;


import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class WindowsToast {

    public static void toast(Context context, int textRes) {
        CharSequence text = context.getResources ().getText ( textRes );
        makeText ( context, text ).show ();
    }

    public static void toast(Context context, CharSequence sequence) {
        makeText ( context, sequence ).show ();
    }

    public static void toastWithCat(Context context, int textRes, boolean isHappy) {
        CharSequence text = context.getResources ().getText ( textRes );
        toastWithCat ( context, text, isHappy );
    }

    public static void toastWithCat(Context context, CharSequence text, boolean isHappy) {
        Toast result = new Toast ( context );

        LayoutInflater inflate = (LayoutInflater) context.getSystemService ( Context.LAYOUT_INFLATER_SERVICE );
        //View v = inflate.inflate(R.layout.ui_toast, null);
        View v = null;
        result.setView ( v );
        ImageView iv = (ImageView) v.findViewById ( android.R.id.icon );
        TextView tv = (TextView) v.findViewById ( android.R.id.message );
        tv.setText ( text );

        result.setGravity ( Gravity.CENTER, 0, 0 );
        result.setDuration ( Toast.LENGTH_SHORT );
        result.show ();
    }

    private static Toast mToast;

    public static Toast makeText(Context context, CharSequence text) {

        if (mToast == null) {
            mToast = new Toast ( context );
            LayoutInflater inflate = (LayoutInflater) context.getSystemService ( Context.LAYOUT_INFLATER_SERVICE );
            View v = inflate.inflate ( R.layout.ui_toast, null );
            mToast.setView ( v );
            TextView tv = (TextView) v.findViewById ( android.R.id.message );
            tv.setText ( text );
            mToast.setDuration(Toast.LENGTH_SHORT);
            mToast.setGravity ( Gravity.CENTER, 0, 0 );
        } else {
            mToast.setText ( text );
        }

        return mToast;
    }
}
