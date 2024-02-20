package com.hr190023_salihselimsekerci;

import android.app.Application;

public class ApplicationClass extends Application {
    ApplicationClass instance = null;

    public ApplicationClass getApp()
    {
        if(instance == null)
        {
            instance = this;
        }
        return instance;
    }
}