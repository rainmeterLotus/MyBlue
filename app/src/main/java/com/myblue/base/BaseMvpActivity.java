package com.myblue.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.myblue.mvp.IBlueBasePresenter;
import com.myblue.mvp.IBlueBaseView;

/**
 * Desc:
 * BaseMvpActivity 需要2个泛型，第一个是继承自IBaseView接口(即MVP模式中的view,与presenter交互的接口)
 * 第二个继承自IBasePresenter接口(即MVP模式中的presenter)，注意该Presenter里的泛型也是V类型，即继承自IBaseView的V
 * 设计BaseMvpActivity目的是为了约束view和presenter的类型，可以强制开发者使用MVP模式
 *
 * BaseMvpActivity为子类做了如下逻辑，子类不用再做：
 * 1.设置布局id
 * 2.findviewById或者使用butterknife
 * 3.声明及初始化一个Presenter实例
 * 4.将Presenter与View绑定
 * 5.将Presenter与View解除绑定;butterKnife与view解除绑定
 *
 * 子类需要实现的抽象方法：
 * 1.getLayoutResID()
 * 2.initCreate()
 * 3.createPresenter()
 *
 *
 * Created by wangdexin on 2016/8/4.
 */
public abstract class BaseMvpActivity<V extends IBlueBaseView,T extends IBlueBasePresenter<V>> extends FragmentActivity {
    private static final String TAG = "BaseMvpActivity";

    public Context mContext;
    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResID());

        mContext = this;
        mPresenter = createPresenter();
        if(mPresenter != null){
            mPresenter.bindView((V)this);//将Presenter与View绑定
        }
        initCreate(savedInstanceState);
    }
    /**
     * Desc：指定Activity需加载的布局ID
     * Author： wangdexin
     * Time：2016/5/11 17:44
     */
    protected abstract int getLayoutResID();
    /**
     * Desc：初始化方法, 类似OnCreate, 仅在此方法中做初始化操作, findView与事件绑定
     * Author： wangdexin
     * Time：2016/5/11 17:44
     */
    protected abstract void initCreate(Bundle savedInstanceState);

    /**
     * Desc：创建Presenter
     * Author： wangdexin
     * Time：2016/5/11 18:00
     */
    protected abstract T createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPresenter != null){
            mPresenter.unbindView();//将Presenter与View绑定
        }
    }
}
