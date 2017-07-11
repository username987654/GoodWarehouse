package com.goodwarehouse.goodwarehouse.bean;

import java.io.Serializable;

/**
 * Created by HaoMeng on 2017-07-11.
 */

public class BreanInfoBean implements Serializable {
    private int id;
    private String name;
    private String imageUrl;

    public BreanInfoBean(int id, String name, String imageUrl) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
