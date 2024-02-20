package com.hr190023_salihselimsekerci.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;

public class IntentUtils{
    private IntentUtils() { }

    public static void GoToActivity(@NonNull Context context, @NonNull Class ActivityName) //@NonNull ifadesi null geçilemez anlamında. Context ve class parametreleri alıp, bu parametrelere göre activity açılıyor.
    {
        Intent toQuestions = new Intent(context.getApplicationContext(), ActivityName);
        context.startActivity(toQuestions);
        ((Activity) context).finish();
    }

}
