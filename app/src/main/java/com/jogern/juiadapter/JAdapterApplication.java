package com.jogern.juiadapter;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.studyhelper.uiadapterlib.AppConfig;

/**
 * Create on 2018/12/28.
 * @author jogern
 */
public class JAdapterApplication extends Application {

      @Override
      protected void attachBaseContext(Context base) {
            super.attachBaseContext(base);
            MultiDex.install(this);
      }


      @Override
      public void onCreate() {
            super.onCreate();
            AppConfig.getInstance().init(this);
      }



}
