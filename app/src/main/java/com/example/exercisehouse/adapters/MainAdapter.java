package com.example.exercisehouse.adapters;

import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.exercisehouse.R;

import java.util.List;

public class MainAdapter extends BaseQuickAdapter<MainBean, BaseViewHolder>{
    public MainAdapter(List<MainBean> data) {
        super(R.layout.main_item, data);
    }

    protected void convert(BaseViewHolder helper, MainBean item) {

        TextView tv_product_title;
        ImageView img_product_thumbnail;

        tv_product_title = (TextView) helper.getView(R.id.product_title_id);


        tv_product_title.setText(item.getTitle());

        img_product_thumbnail = (ImageView) helper.getView(R.id.product_img_id);

        img_product_thumbnail.setImageResource(item.getThumbnail());


    }


    public  void setMsg(String str) {
        Log.i("tab1", str);
    }
}