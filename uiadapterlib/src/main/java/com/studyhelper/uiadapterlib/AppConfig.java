package com.studyhelper.uiadapterlib;

import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import com.studyhelper.uiadapterlib.lifecycle.ActivityLifecycleImpl;


/**
 * Create on 2019/6/10.
 * @author jogern
 */
public class AppConfig {

      private static final class Holder {

            private static final AppConfig SIZE_CONFIG = new AppConfig();
      }

      public static AppConfig getInstance() {
            return Holder.SIZE_CONFIG;
      }

      /** 最初的 {@link DisplayMetrics} 的数据 */
      private DisplayInfo mInitDisplayInfo;
      /** 设备的屏幕总宽度, 单位 px */
      private int         mScreenWidth;
      /** 设备的屏幕总高度, 单位 px */
      private int         mScreenHeight;


      private AppConfig() {
            mActivityLifecycle = new ActivityLifecycleImpl();
      }

      private ActivityLifecycleImpl mActivityLifecycle;

      public void init(final Application application) {
            application.registerActivityLifecycleCallbacks(mActivityLifecycle);
            final DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
            mInitDisplayInfo = new DisplayInfo();
            mInitDisplayInfo.setDensity(displayMetrics.density);
            mInitDisplayInfo.setDensityDpi(displayMetrics.densityDpi);
            mInitDisplayInfo.setScaledDensity(displayMetrics.scaledDensity);
            mInitDisplayInfo.setXdpi(displayMetrics.xdpi);
            mInitDisplayInfo.setYdpi(displayMetrics.ydpi);

            initScreenSize(application);
            application.registerComponentCallbacks(new ComponentCallbacks() {
                  @Override
                  public void onConfigurationChanged(Configuration newConfig) {
                      initScreenSize(application);
                        //说明字体大小改变了
                        if (newConfig != null && newConfig.fontScale > 0) {
                              //重新赋值appScaledDensity
                           float  appScaledDensity = application.getResources().getDisplayMetrics().scaledDensity;
                           mInitDisplayInfo.setScaledDensity(appScaledDensity);
                        }
                  }

                  @Override
                  public void onLowMemory() { }
            });

      }

      private void initScreenSize(Context application){
            int[] screenSize = CommonUtil.getScreenSize(application);
            mScreenWidth = screenSize[0];
            mScreenHeight = screenSize[1];// - getNavigationBarHeight(application);
      }

      public DisplayInfo getInitDisplayInfo() {
            return mInitDisplayInfo;
      }

      public int getScreenWidth() {
            return mScreenWidth;
      }

      public int getScreenHeight() {
            return mScreenHeight;
      }



}
