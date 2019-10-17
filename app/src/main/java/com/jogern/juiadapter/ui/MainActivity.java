package com.jogern.juiadapter.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.jogern.juiadapter.Constant;
import com.jogern.juiadapter.R;
import com.studyhelper.uiadapterlib.AutoSizeAdaptation;
import com.studyhelper.uiadapterlib.DesignInfo;


/**
 * Create on 2018/5/4.
 * @author jogern
 */
public class MainActivity extends AppCompatActivity  implements AutoSizeAdaptation {

      @Override
      public DesignInfo designInfo() {
            return new DesignInfo.Builder().setDesignWidth(Constant.DESIGN_WIDTH)
                    .setDesignHeight(Constant.DESIGN_HEIGHT).bild();
      }

      @Override
      protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            Log.e("uiadapter","MainActivity onCreate ---------------------------");
      }


      public void onClickTest(View view) {

      }
}
