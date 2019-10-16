package com.studyhelper.uiadapterlib.strategy;

import android.app.Activity;
import android.util.DisplayMetrics;
import com.studyhelper.uiadapterlib.AppConfig;
import com.studyhelper.uiadapterlib.AutoSizeAdaptation;
import com.studyhelper.uiadapterlib.DesignInfo;
import com.studyhelper.uiadapterlib.DisplayInfo;


import java.util.HashMap;
import java.util.Map;

/**
 * Create on 2019/6/10.
 * @author jogern
 */
public class DefaultStrategy implements AutoStrategy {

      private Map<Object, DisplayInfo> mDisplayInfoMap = new HashMap<>();

      @Override
      public void startAdaptation(Activity curAty, Object target) {
            if (!(target instanceof AutoSizeAdaptation)) {
                  return;
            }

            DisplayInfo displayInfo = mDisplayInfoMap.get(target);
            DesignInfo designInfo = ((AutoSizeAdaptation) target).designInfo();
            if (displayInfo == null) {
                  displayInfo = new DisplayInfo();
                  mDisplayInfoMap.put(target, displayInfo);
            }
            createDisplay(displayInfo, designInfo);
            setDensity(curAty, displayInfo);
      }

      private void createDisplay(DisplayInfo displayInfo, DesignInfo designInfo) {

            float targetDensity = AppConfig.getInstance().getScreenWidth() * 1.0f / designInfo.getDesignWidth();

            DisplayInfo displayInitInfo = AppConfig.getInstance().getInitDisplayInfo();

            float scale = displayInitInfo.getScaledDensity() * 1.0f / displayInitInfo.getDensity();
            float targetScaledDensity = targetDensity * scale;
            int targetDensityDpi = (int) (targetDensity * 160);

            float targetXdpi = AppConfig.getInstance().getScreenWidth() * 1.0f / designInfo.getDesignWidth();
            float targetYdpi = AppConfig.getInstance().getScreenHeight() * 1.0f / designInfo.getDesignHeight();

            displayInfo.setDensity(targetDensity);
            displayInfo.setScaledDensity(targetScaledDensity);
            displayInfo.setDensityDpi(targetDensityDpi);
            displayInfo.setXdpi(targetXdpi);
            displayInfo.setYdpi(targetYdpi);
      }


      private void setDensity(Activity curAty, DisplayInfo displayInfo) {
            DisplayMetrics activityDisplayMetrics = curAty.getResources().getDisplayMetrics();
            setDensity(activityDisplayMetrics, displayInfo);


            DisplayMetrics appDisplayMetrics = curAty.getApplication().getResources().getDisplayMetrics();
            setDensity(appDisplayMetrics, displayInfo);
      }

      private void setDensity(DisplayMetrics displayMetrics, DisplayInfo displayInfo) {
            displayMetrics.density = displayInfo.getDensity();
            displayMetrics.densityDpi = displayInfo.getDensityDpi();
            displayMetrics.scaledDensity = displayInfo.getScaledDensity();
            displayMetrics.xdpi = displayInfo.getXdpi();
            displayMetrics.ydpi = displayInfo.getYdpi();
      }

}
