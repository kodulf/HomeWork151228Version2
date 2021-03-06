package com.kodulf.homework151228;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 15-12-29.
 */
public class ItemAdapter extends BaseAdapter implements View.OnClickListener {
    private Context context;
    private List<Item> items;
    private Item currentItem;

    public ItemAdapter(Context context) {
        this.context = context;
        items=new ArrayList<Item>();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addAll(List<Item> list){
        items.addAll(list);
        notifyDataSetChanged();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
     if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.item,parent,false);
            convertView.setTag(new ViewHolder(convertView));
        }
        Item item = items.get(position);
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

        currentItem=items.get(position);
        holder.content.setOnClickListener(this);

        if(item.getImage()==null){
            holder.image.setVisibility(View.GONE);
        }else{
            holder.image.setVisibility(View.VISIBLE);
            String imageURL = getImageURL(item.getImage());
            Picasso.with(context)
                    .load(imageURL)
                    .resize(parent.getWidth(),0)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(holder.image);

        }
        holder.image.setOnClickListener(this);
        holder.funny.setText(item.getFunny());
        holder.comments.setText(item.getComments());
        holder.share.setText(item.getShare());
        Log.d("151229MY", "postion=" + position + " Name=" + item.getUserName() + " content=" + item.getContent());

        return convertView;
    }


    public static String getIconURL(long id, String icon){
        String url = "http://pic.qiushibaike.com/system/avtnew/%s/%s/thumb/%s";
        return String.format(url, id / 10000, id, icon);

    }
    public static String getImageURL(String image){
        String url = "http://pic.qiushibaike.com/system/pictures/%s/%s/%s/%s";
        Pattern pattern = Pattern.compile("(\\d+)\\d{4}");
        Matcher matcher = pattern.matcher(image);
        matcher.find();
        Log.d("151229MY", "getImageURL: " + matcher.group());
        return String.format(url, matcher.group(1), matcher.group(), "medium", image);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context,Detail.class);
        Bundle bundle =new Bundle();
        bundle.putSerializable("detail",currentItem);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    private static class ViewHolder{
        private ImageView icon;
        private ImageView image;
        private TextView content;
        private TextView name;
        private TextView comments;
        private TextView share;
        private TextView funny;
        public ViewHolder(View itemView){
            icon =(ImageView)itemView.findViewById(R.id.user_icon);
            image =(ImageView)itemView.findViewById(R.id.image);
            content =(TextView)itemView.findViewById(R.id.content);
            name=(TextView)itemView.findViewById(R.id.user_name);
            comments=(TextView)itemView.findViewById(R.id.comments);
            share=(TextView)itemView.findViewById(R.id.share);
            funny=(TextView)itemView.findViewById(R.id.funny);
        }
    }

}
