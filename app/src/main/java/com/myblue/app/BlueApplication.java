package com.myblue.app;

import android.app.Application;

import com.bumptech.glide.util.Util;
import com.myblue.dao.BlueDaoHelper;
import com.myblue.util.BlueUtil;

/**
 * Desc:
 * Created by wangdexin on 2016/8/2.
 */
public class BlueApplication extends Application {
    private static final String TAG = "BlueApplication";
    public static BlueDaoHelper blueDaoHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        initBlueGao();
    }

    private void initBlueGao() {
        blueDaoHelper = new BlueDaoHelper(getApplicationContext());
    }
}
