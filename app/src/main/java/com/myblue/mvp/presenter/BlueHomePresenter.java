package com.myblue.mvp.presenter;

import com.myblue.bean.CityBean;
import com.myblue.dao.BlueFey;
import com.myblue.mvp.IBlueBasePresenter;
import com.myblue.mvp.model.BlueHomeModelImpl;
import com.myblue.mvp.view.IBlueHomeView;

import java.util.List;

/**
 * Desc 与首页view交互的Presenter，用于view层获取数据的管理者，view层就不会和model层有任何关系
 * Created by wangdexin
 * Date 2016/5/11.
 */
public class BlueHomePresenter extends IBlueBasePresenter<IBlueHomeView> {
    private static final String TAG = "BlueHomePresenter";
    private final BlueHomeModelImpl homeModel;

    public BlueHomePresenter(){
        homeModel = new BlueHomeModelImpl();
    }

    @Override
    public void onPresenterCreate() {
        List<BlueFey> shamBlueFey = homeModel.getShamBlueFey();
        baseView.onViewSuccess(shamBlueFey);
    }

    @Override
    public void onPresenterResume() {

    }

    @Override
    public void onPresenterDestory() {

        homeModel.deleteShamBlueFey();
    }


}
