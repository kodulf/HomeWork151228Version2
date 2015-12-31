package com.kodulf.homework151228;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.kodulf.homework151228.adapter.CommentsItemAdapter;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Detail extends AppCompatActivity implements Callback {
    private ImageView icon;
    private ImageView image;
    private TextView content;
    private TextView name;
    private TextView comments;
    private TextView share;
    private TextView funny;
    private ListView listView;
    private Call call;
    private CommentsItemAdapter adapter;
    private ArrayList<CommentsItem> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        icon =(ImageView)findViewById(R.id.detail_user_icon);
        image =(ImageView)findViewById(R.id.detail_image);
        content =(TextView)findViewById(R.id.detail_content);
        name=(TextView)findViewById(R.id.detail_user_name);
        comments=(TextView)findViewById(R.id.detail_comments);
        share=(TextView)findViewById(R.id.detail_share);
        funny=(TextView)findViewById(R.id.detail_funny);


        Intent result = getIntent();
        Bundle bundle = result.getExtras();
        Item detail = (Item)bundle.getSerializable("detail");
        if(detail.getUserName()!=null){
                name.setText(detail.getUserName());
            Picasso.with(this).load(getIconURL(detail.getUserId(),detail.getUserIcon()))
                    .transform(new CircleTransformation())
                    .into(icon);
        }else{
            name.setText("匿名用户");
            icon.setImageResource(R.mipmap.ic_launcher);
        }

        content.setText(detail.getContent());
        if(detail.getImage()==null){
            image.setVisibility(View.GONE);
        }else{
            image.setVisibility(View.VISIBLE);
            String imageURL = getImageURL(detail.getImage());
            Picasso.with(this)
                    .load(imageURL)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(image);
        }

        funny.setText(detail.getFunny());
        comments.setText(detail.getComments());
        share.setText(detail.getShare());

        listView = (ListView) findViewById(R.id.comments_listView);
        adapter = new CommentsItemAdapter(this);
        listView.setAdapter(adapter);

        OkHttpClient client = new OkHttpClient();
        String commentUrl = getCommentsURL(detail.getQiushiId());
        Request request = new Request.Builder().url(commentUrl).get().build();
        call = client.newCall(request);
        //同步请求
        //Response response = call.execute();//这个方法使用频率较低。
        call.enqueue(this);//用的比较多的是这个异步请求。

    }


    public static String getCommentsURL(String id){
        String url = "http://m2.qiushibaike.com/article/%s/comments?page=1";
        return String.format(url, id);

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
    public void onFailure(Request request, IOException e) {

    }

    @Override
    public void onResponse(Response response) throws IOException {
        final String s = response.body().string();//response.body().可以有很多参数I如内容长度，bytes等等
        try {
            JSONObject object = new JSONObject(s);
            JSONArray items = object.getJSONArray("items");
            //final List<String> list = new ArrayList<String>();
            list = new ArrayList<CommentsItem>();
            Log.d("151229MY", "item" + items.length());
            for (int i = 0; i <items.length(); i++) {
                //list.add(items.getJSONObject(i).getString("content"));
                list.add(new CommentsItem(items.getJSONObject(i)));
                Log.d("151229MY",items.getJSONObject(i).toString());
            }
            new AsycTaskZhuanXiang().execute();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public class AsycTaskZhuanXiang extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... params) {
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            adapter.addAll(list);
        }
    }
}
