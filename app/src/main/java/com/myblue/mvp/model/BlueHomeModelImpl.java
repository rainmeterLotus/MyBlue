package com.myblue.mvp.model;

import android.util.Log;

import com.myblue.app.BlueApplication;
import com.myblue.bean.CityBean;
import com.myblue.dao.BlueDaoHelper;
import com.myblue.dao.BlueFey;
import com.myblue.mvp.IBlueBaseModel;
import com.myblue.util.GeneratorData;

import java.util.List;

/**
 * Desc: MVP模式中，与首页Presenter交互的Model,用于获取数据
 * Created by wangdexin
 * Date: 2016/5/11
 */
public class BlueHomeModelImpl implements IBlueBaseModel {
    private static final String TAG = "BlueHomeModelImpl";

    @Override
    public void loadRequest() {
        //模拟网络请求
        BlueApplication.blueDaoHelper.addAllBlue(GeneratorData.getShamBlueFey());
    }


    public List<BlueFey> getShamBlueFey(){
        loadRequest();

        List<BlueFey> blueList = null;
        if (BlueApplication.blueDaoHelper != null){
            blueList = BlueApplication.blueDaoHelper.getBlueList();
        }
        return blueList;
    }

    public void deleteShamBlueFey() {
        //模拟删除无用的数据
        if (BlueApplication.blueDaoHelper != null){
            BlueApplication.blueDaoHelper.deleteAll();
        }

    }
}
