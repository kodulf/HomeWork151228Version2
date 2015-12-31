package com.kodulf.homework151228.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kodulf.homework151228.CircleTransformation;
import com.kodulf.homework151228.CommentsItem;
import com.kodulf.homework151228.Item;
import com.kodulf.homework151228.R;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by XUE on 2015/12/31.
 */
public class CommentsItemAdapter extends BaseAdapter {
    Context context;
    List<CommentsItem> items;

    public CommentsItemAdapter(Context context) {
        this.context = context;
        items=new ArrayList<CommentsItem>();

    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }
    public void addAll(List<CommentsItem> list){
        items.addAll(list);
        notifyDataSetChanged();
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.comments_item,parent,false);
            convertView.setTag(new ViewHolder(convertView));
        }
        CommentsItem item = items.get(position);
        ViewHolder holder = (ViewHolder)convertView.getTag();

        if(item.getUserName()!=null) {
            holder.name.setText(item.getUserName());
            Picasso.with(context).load(getIconURL(item.getUserId(),item.getUserIcon()))
                    .transform(new CircleTransformation())
                    .into(holder.icon);
        }else{
            holder.name.setText("匿名用户");
            holder.icon.setImageResource(R.mipmap.ic_launcher);
        }

        holder.content.setText(item.getContent());
        long time = System.currentTimeMillis()/1000-item.getCreatedTime();

        if(time/60<1) {
            holder.createdTime.setText(String.valueOf(time) + "秒之前");
        }else if(time/3600<1){
            holder.createdTime.setText(String.valueOf(time/60) + "分之前");
        }else if(time/(3600*24)<1){
            holder.createdTime.setText(String.valueOf(time/3600) + "小时之前");
        }else if(time/(3600*24*30)<1){
            holder.createdTime.setText(String.valueOf(time/(3600*24)) + "天之前");
        }

        holder.floor.setText(String.valueOf(item.getFloor()));

        return convertView;
    }

    public static String getIconURL(long id, String icon){
        String url = "http://pic.qiushibaike.com/system/avtnew/%s/%s/thumb/%s";
        return String.format(url, id / 10000, id, icon);

    }

    private static class ViewHolder{
        private ImageView icon;
        private TextView name;
        private TextView content;
        private TextView floor;
        private TextView createdTime;

        public ViewHolder(View itemView){
            icon =(ImageView)itemView.findViewById(R.id.comment_userIcon);
            name=(TextView)itemView.findViewById(R.id.comment_userName);
            content =(TextView)itemView.findViewById(R.id.comment_content);
            floor=(TextView)itemView.findViewById(R.id.comment_floor);
            createdTime=(TextView)itemView.findViewById(R.id.comment_createdtime);

            //funny=(ImageView)itemView.findViewById(R.id.funny);
        }
    }
}
