package com.goodwarehouse.goodwarehouse.bean;

import java.io.Serializable;
import java.util.IdentityHashMap;

/**
 * Created by HaoMeng on 2017-07-10.
 */

public class ExpertDataBean implements Serializable {
    private String id;
    private String name;
    private String duty;
    private String User_images;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ExpertDataBean(String id, String name, String duty, String user_images) {

        this.id = id;
        this.name = name;
        this.duty = duty;
        User_images = user_images;
    }

    public ExpertDataBean(String name, String duty, String user_images) {
        this.name = name;
        this.duty = duty;
        User_images = user_images;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getUser_images() {
        return User_images;
    }

    public void setUser_images(String user_images) {
        User_images = user_images;
    }
}
