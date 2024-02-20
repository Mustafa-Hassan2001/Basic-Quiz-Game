package com.hr190023_salihselimsekerci.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;

public class DialogUtils {

    public static void ShowDialog(Context context, String title, String message, String sure,
                           String cancel, Class GoToActivity, Class BackToActivity) {
        AlertDialog.Builder builder = new Builder(context);
        if (message != null) {
            builder.setMessage(message);
        }
        if (title != null) {
            builder.setTitle(title);
        }
        if(sure != null) {

            builder.setCancelable(false);
            builder.setPositiveButton(sure, new android.content.DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    if(GoToActivity != null)
                        IntentUtils.GoToActivity(context, GoToActivity);
                }
            });
        }
        if(cancel != null)
        {
            builder.setNegativeButton(cancel, new android.content.DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    if(BackToActivity != null)
                        IntentUtils.GoToActivity(context, BackToActivity);
                    ((Activity) context).finish();
                }
            });
        }

        builder.create().show();
    }
}
