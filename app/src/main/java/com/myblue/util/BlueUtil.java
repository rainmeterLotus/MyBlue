package com.myblue.util;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

/**
 * Desc:
 * Created by wangdexin on 2016/8/3.
 */
public class BlueUtil {
    private static final String TAG = "BlueUtil";


    private static DisplayMetrics mDisplayMetrics = new DisplayMetrics();

    public static void initializationDisplayMetrics(Activity context) {
        if (context == null) {
            return;
        }
        WindowManager manager = context.getWindowManager();
        manager.getDefaultDisplay().getMetrics(mDisplayMetrics);
    }

    public static int getDisplayMectricsWith() {
        return mDisplayMetrics.widthPixels;
    }

    public static int getDisplayMectricsHeight() {
        return mDisplayMetrics.heightPixels;
    }


    public static void resizeImageViewOnScreenSize(View view, int numColumns, int horizontalSpacing, int zoomX, int zoomY) {
        if (view == null) {
            return;
        }
        android.view.ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = mDisplayMetrics.widthPixels / numColumns - horizontalSpacing * (numColumns - 1);
        layoutParams.height = layoutParams.width * zoomX / zoomY;
    }
}
