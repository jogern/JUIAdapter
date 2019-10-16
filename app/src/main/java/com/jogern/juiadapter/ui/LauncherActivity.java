package com.jogern.juiadapter.ui;

import android.content.Intent;
import android.os.Bundle;

import com.jogern.juiadapter.Constant;
import com.studyhelper.uiadapterlib.AutoSizeAdaptation;
import com.studyhelper.uiadapterlib.DesignInfo;

/**
 * Create on 2019-08-23.
 *
 * @author zujianliang
 */
public class LauncherActivity extends PermissionActivity implements AutoSizeAdaptation {


    @Override
    public DesignInfo designInfo() {
        return new DesignInfo.Builder().setDesignWidth(Constant.DESIGN_WIDTH)
                .setDesignHeight(Constant.DESIGN_HEIGHT).bild();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        checkPermission();
    }

    @Override
    protected void startLauncherActivity() {
        startActivity(new Intent(this, MainActivity.class));
    }
}
