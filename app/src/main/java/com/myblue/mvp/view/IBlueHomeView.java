package com.myblue.mvp.view;


import com.myblue.mvp.IBlueBaseView;

/**
 * Desc: 首页view需要和presenter交互(MVP模式中view与presenter交互的接口)
 * Created by wangdexin
 * Date: 2016/5/11.
 */
public interface IBlueHomeView<T> extends IBlueBaseView<T> {
    /**
     * Desc: 更新view的ui
     * Author: wangdexin
     * Time: 2016/5/11 14:21.
     */
    void onViewSuccess(T data);

    /**
     * Desc：请求数据失败，view处理ui
     * Author： wangdexin
     * Time：2016/5/13 15:32
     */
    void onViewError(String message);

}
