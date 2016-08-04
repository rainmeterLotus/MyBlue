package com.myblue.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.myblue.adapter.BlueListViewAdapter;
import com.myblue.base.BaseMvpActivity;
import com.myblue.bean.CityBean;
import com.myblue.dao.BlueFey;
import com.myblue.dao.R;
import com.myblue.mvp.presenter.BlueHomePresenter;
import com.myblue.mvp.view.IBlueHomeView;
import com.myblue.util.BlueUtil;
import com.myblue.util.GeneratorData;

import java.util.List;

public class BlueHomeActivity extends BaseMvpActivity<IBlueHomeView,BlueHomePresenter> implements IBlueHomeView {

    private ListView mListView;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        mListView = (ListView) findViewById(R.id.listview);

        BlueUtil.initializationDisplayMetrics(this);
        mPresenter.onPresenterCreate();
    }

    @Override
    protected BlueHomePresenter createPresenter() {
        return new BlueHomePresenter();
    }

    @Override
    public void onViewSuccess(Object data) {
        List<BlueFey> shamData = (List<BlueFey>) data;
        BlueListViewAdapter blueListViewAdapter = new BlueListViewAdapter(getApplicationContext(), shamData);
        mListView.setAdapter(blueListViewAdapter);
    }

    @Override
    public void onViewError(String message) {

    }
}
