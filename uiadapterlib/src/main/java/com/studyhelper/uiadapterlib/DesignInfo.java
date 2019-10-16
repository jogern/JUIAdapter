package com.studyhelper.uiadapterlib;

/**
 * Create on 2019/6/10.
 * @author jogern
 */
public class DesignInfo {

      private int mDesignWidth;
      private int mDesignHeight;

      private DesignInfo(int designWidth, int designHeight) {
            mDesignWidth = designWidth;
            mDesignHeight = designHeight;
      }


      public int getDesignWidth() {
            return mDesignWidth;
      }

      public int getDesignHeight() {
            return mDesignHeight;
      }


      public final static class Builder {

            private int mDesignWidth;
            private int mDesignHeight;

            public Builder setDesignWidth(int designWidth) {
                  mDesignWidth = designWidth;
                  return this;
            }

            public Builder setDesignHeight(int designHeight) {
                  mDesignHeight = designHeight;
                  return this;
            }

            public DesignInfo bild() {
                  return new DesignInfo(mDesignWidth, mDesignHeight);
            }
      }
}
