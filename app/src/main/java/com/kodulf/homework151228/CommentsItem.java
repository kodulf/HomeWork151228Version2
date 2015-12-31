package com.kodulf.homework151228;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by XUE on 2015/12/31.
 */
public class CommentsItem {
    private int floor;

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    private long createdTime;
    private String content;
    private int like_count;
    private long userId;
    private String userName;
    private String userIcon;

    @Override
    public String toString() {
        return "CommentsItem{" +
                "floor=" + floor +
                ", createdTime=" + createdTime +
                ", content='" + content + '\'' +
                ", like_count=" + like_count +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userIcon='" + userIcon + '\'' +
                '}';
    }

    public CommentsItem() {
    }
    public CommentsItem(JSONObject object) throws JSONException {
        floor=object.getInt("floor");
        createdTime=object.getLong("created_at");
        like_count=object.getInt("like_count");
        content=object.getString("content");
        if(!object.isNull("user")) {

            userId = object.getJSONObject("user").getLong("id");
            userName = object.getJSONObject("user").getString("login");
            userIcon = object.getJSONObject("user").getString("icon");
        }
    }



    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }



    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLike_count() {
        return like_count;
    }

    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }
}
