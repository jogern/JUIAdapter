package com.studyhelper.uiadapterlib;

/**
 * Create on 2019/6/10.
 * @author jogern
 */
public class DisplayInfo {

      private float mDensity;
      private int   mDensityDpi;
      private float mScaledDensity;
      private float mXdpi;
      private float mYdpi;

      public float getDensity() {
            return mDensity;
      }

      public void setDensity(float density) {
            mDensity = density;
      }

      public int getDensityDpi() {
            return mDensityDpi;
      }

      public void setDensityDpi(int densityDpi) {
            mDensityDpi = densityDpi;
      }

      public float getScaledDensity() {
            return mScaledDensity;
      }

      public void setScaledDensity(float scaledDensity) {
            mScaledDensity = scaledDensity;
      }

      public float getXdpi() {
            return mXdpi;
      }

      public void setXdpi(float xdpi) {
            mXdpi = xdpi;
      }

      public float getYdpi() {
            return mYdpi;
      }

      public void setYdpi(float ydpi) {
            mYdpi = ydpi;
      }


      @Override
      public String toString() {
            return "DisplayInfo{" + "mDensity=" + mDensity + ", mDensityDpi=" + mDensityDpi + ", mScaledDensity=" + mScaledDensity + ", mXdpi=" +
                    mXdpi + ", mYdpi=" + mYdpi + '}';
      }
}
