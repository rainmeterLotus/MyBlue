package com.myblue.mvp;

/**
 * Desc: MVP模式中所有presenter层的抽象类
 * Created by wangdexin
 * Date: 2016/5/11.
 */
public abstract class IBlueBasePresenter<T extends IBlueBaseView>  {
    protected T baseView;
    /**
     * Desc: Presenter层的生命周期onCreate
     * Author: wangdexin
     * Time: 2016/5/11 11:56.
     */
    public abstract void onPresenterCreate();
    /**
     * Desc: Presenter层的生命周期onResume
     * Author: wangdexin
     * Time: 2016/5/11 11:56.
     */
    public abstract void onPresenterResume();
    /**
     * Desc: Presenter层的生命周期onDestory
     * Author: wangdexin
     * Time: 2016/5/11 11:57.
     */
    public abstract void onPresenterDestory();
    /**
     * Desc: 绑定view的一个实例
     * Author: wangdexin
     * Time: 2016/5/11 11:57.
     */
    public void bindView(T baseView){
        this.baseView = baseView;
    }

    /**
     * Desc：解除view的绑定
     * Author： wangdexin
     * Time：2016/5/11 18:05
     */
    public void unbindView(){
        onPresenterDestory();
        this.baseView = null;
    }

}
