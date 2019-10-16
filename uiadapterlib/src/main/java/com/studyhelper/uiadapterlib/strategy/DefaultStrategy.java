package com.studyhelper.uiadapterlib.strategy;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.util.Log;

import com.studyhelper.uiadapterlib.AppConfig;
import com.studyhelper.uiadapterlib.AutoSizeAdaptation;
import com.studyhelper.uiadapterlib.CommonUtil;
import com.studyhelper.uiadapterlib.DesignInfo;
import com.studyhelper.uiadapterlib.DisplayInfo;


import java.util.HashMap;
import java.util.Map;

/**
 * Create on 2019/6/10.
 *
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
        createDisplay(curAty, displayInfo, designInfo);
        setDensity(curAty, displayInfo);
    }

    private void createDisplay(Activity curAty, DisplayInfo displayInfo, DesignInfo designInfo) {

//        Configuration configuration = curAty.getResources().getConfiguration();
//        boolean isPortrait = configuration.orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
//        int width = isPortrait ? designInfo.getDesignWidth() : designInfo.getDesignHeight();
//        int height = isPortrait ? designInfo.getDesignHeight() : designInfo.getDesignWidth();

        int width = designInfo.getDesignWidth() ;
        int height = designInfo.getDesignHeight() ;

//        if (CommonUtil.isNavigationBarShown(curAty)) {
        height = height - CommonUtil.getNavigationBarHeight(curAty);
//        }
        Log.e("uiadapter", "width: " + width + " height: " + height);

        float targetDensity = AppConfig.getInstance().getScreenWidth() * 1.0f / width;
        Log.e("uiadapter", "targetDensity: " + targetDensity);
        DisplayInfo displayInitInfo = AppConfig.getInstance().getInitDisplayInfo();

        float scale = displayInitInfo.getDensity() * 1.0f / displayInitInfo.getScaledDensity();
        Log.e("uiadapter", "scale: " + scale);
        float targetScaledDensity = targetDensity * scale;
        Log.e("uiadapter", "targetScaledDensity: " + targetScaledDensity);
        int targetDensityDpi = (int) (targetDensity * 160);
        Log.e("uiadapter", "targetDensityDpi: " + targetDensityDpi);

        float targetXdpi = targetDensity;
        Log.e("uiadapter", "targetXdpi: " + targetXdpi);
        float targetYdpi = AppConfig.getInstance().getScreenHeight() * 1.0f / height;

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
