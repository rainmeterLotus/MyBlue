package com.myblue.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.List;

import de.greenrobot.dao.query.Query;

/**
 * Desc:
 * Created by wangdexin on 2016/8/4.
 */
public class BlueDaoHelper {
    private static final String TAG = "BlueDaoHelper";

    private static final String DB_NAME = "blue_db";

    private final DaoSession blueDaoSession;

    public BlueDaoHelper(Context context){
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
        blueDaoSession = new DaoMaster(devOpenHelper.getWritableDatabase()).newSession();
    }


    private BlueFeyDao getNoteDao(){
        if (blueDaoSession != null){
            return blueDaoSession.getBlueFeyDao();
        }
        return null;
    }

    public void addAllBlue(List<BlueFey> allBlue){
        for (BlueFey blue:allBlue){
            addBlueFey(blue);
        }
    }


    public void addBlueFey(BlueFey blueFey){

        if (getNoteDao() != null){
            getNoteDao().insert(blueFey);
        }
    }


    public void deleteAll(){
        if (getNoteDao() != null){
            getNoteDao().deleteAll();
        }
    }

    public List<BlueFey> getBlueList(){
        Query<BlueFey> query = getNoteDao().queryBuilder().where(BlueFeyDao.Properties.BlueUrl.isNotNull()).build();
        if (query != null){
            return  query.list();
        }

        return  null;
    }
}
