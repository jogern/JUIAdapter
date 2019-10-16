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
                  }

                  @Override
                  public void onLowMemory() { }
            });

      }

      private void initScreenSize(Context application){
            int[] screenSize = getScreenSize(application);
            mScreenWidth = screenSize[0];
            mScreenHeight = screenSize[1] - getNavigationBarHeight(application);
            Log.e("","mScreenWidth: " + mScreenWidth + " mScreenHeight: " + mScreenHeight);
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


      /**
       * 获取当前的屏幕尺寸
       * @param context {@link Context}
       * @return 屏幕尺寸
       */
      private int[] getScreenSize(Context context) {
            int[] size = new int[2];

            WindowManager w = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            if (w != null) {
                  Display d = w.getDefaultDisplay();
                  DisplayMetrics metrics = new DisplayMetrics();
                  d.getMetrics(metrics);

                  size[0] = metrics.widthPixels;
                  size[1] = metrics.heightPixels;
            }
            return size;
      }

      /**
       * 非全面屏下 虚拟键高度(无论是否隐藏)
       * @param context
       * @return
       */
      private int getNavigationBarHeight(Context context){
            int result = 0;
            int resourceId = context.getResources().getIdentifier("navigation_bar_height","dimen", "android");
            if (resourceId > 0) {
                  result = context.getResources().getDimensionPixelSize(resourceId);
            }
            return result;
      }
}
