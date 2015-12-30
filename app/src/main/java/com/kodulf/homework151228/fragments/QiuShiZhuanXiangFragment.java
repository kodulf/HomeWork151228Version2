package com.kodulf.homework151228.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.kodulf.homework151228.Item;
import com.kodulf.homework151228.ItemAdapter;
import com.kodulf.homework151228.R;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 15-12-29.
 */
public class QiuShiZhuanXiangFragment extends Fragment implements Callback {
    private Call call;
    private TextView textView;
    private ListView listView;
    private ItemAdapter adapter;
    private List<Item> list;

    public QiuShiZhuanXiangFragment() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View ret = inflater.inflate(R.layout.zhuanxiang_fragment,container,false);
        View ret = inflater.inflate(R.layout.zhuanxiang_fragment,container,false);

        //textView = (TextView) findViewById(R.id.main_text);
        listView = (ListView) ret.findViewById(R.id.main_listView);
        adapter = new ItemAdapter(this.getContext());
        //adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>());
        listView.setAdapter(adapter);

        OkHttpClient client = new OkHttpClient();
        //Request request = new Request.Builder().url("http://www.baidu.com/").get().build();
        Request request = new Request.Builder().url("http://m2.qiushibaike.com/article/list/suggest?page=1").get().build();
        call = client.newCall(request);
        //同步请求
        //Response response = call.execute();//这个方法使用频率较低。
        call.enqueue(this);//用的比较多的是这个异步请求。
        return ret;
        //return super.onCreateView(inflater, container, savedInstanceState);

    }


    @Override
    public void onFailure(Request request, IOException e) {

    }

    @Override
    public void onResponse(Response response) throws IOException {
        Log.d("151229MY", "onSuccessful" + Thread.currentThread().getName());
        final String s = response.body().string();//response.body().可以有很多参数I如内容长度，bytes等等
        try {
            JSONObject object = new JSONObject(s);
            JSONArray items = object.getJSONArray("items");
            //final List<String> list = new ArrayList<String>();
            list = new ArrayList<Item>();
            Log.d("151229MY", "item" + items.length());
            for (int i = 0; i <items.length(); i++) {
                //list.add(items.getJSONObject(i).getString("content"));
                list.add(new Item(items.getJSONObject(i)));
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
