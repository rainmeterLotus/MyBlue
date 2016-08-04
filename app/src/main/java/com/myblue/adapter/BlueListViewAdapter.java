package com.myblue.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.myblue.bean.CityBean;
import com.myblue.dao.BlueFey;
import com.myblue.dao.R;
import com.myblue.util.BlueUtil;

import java.util.List;

/**
 * Desc:
 * Created by wangdexin on 2016/8/3.
 */
public class BlueListViewAdapter extends BaseAdapter {
    private static final String TAG = "BlueListViewAdapter";
    private List<BlueFey> cityList;
    private Context context;

    public BlueListViewAdapter(Context context, List<BlueFey> cityList){
        this.context = context;
        this.cityList = cityList;
    }
    @Override
    public int getCount() {
        return cityList.size();
    }

    @Override
    public Object getItem(int position) {
        return cityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_home_item, null);
            holder.mImageLogo =  (ImageView) convertView.findViewById(R.id.image_home_url);
            holder.mTextName =  (TextView) convertView.findViewById(R.id.text_item_name);

            BlueUtil.resizeImageViewOnScreenSize(holder.mImageLogo,1,0,1,2);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }


        BlueFey cityBean = cityList.get(position);
        Glide.with(context).load(cityBean.getBlueUrl()).placeholder(holder.mImageLogo.getDrawable()).into(holder.mImageLogo);
        holder.mTextName.setText(cityBean.getBlueName());

        return convertView;
    }

    class ViewHolder{
        public ImageView mImageLogo;
        public TextView mTextName;

    }
}
