package com.example.fetchdata;

public class Url{
    String UserUrl,UserTopic;
    String UrlId;
    int count;

    public String getUrlId() {
        return UrlId;
    }

    public void setUrlId(String urlId) {
        UrlId = urlId;
    }

    public String getUserUrl() {
        return UserUrl;
    }

    public void setUserUrl(String userUrl) {
        UserUrl = userUrl;
    }

    public String getUserTopic() {
        return UserTopic;
    }

    public void setUserTopic(String userTopic) {
        UserTopic = userTopic;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
