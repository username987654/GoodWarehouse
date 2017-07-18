package com.goodwarehouse.goodwarehouse.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HaoMeng on 2017-07-16.
 */

public class CommodityCartData implements Serializable {
    private String name;
    private String image;
    private String introduce;
    private String price;
    private List<CommodityDetailsBean.DataBean.ItemsBean.SkuInfoBean.AttrListBean> arrtList;

    public CommodityCartData(String name,
                             String image,
                             String introduce,
                             String price,
                             List<CommodityDetailsBean.DataBean.ItemsBean.SkuInfoBean.AttrListBean> arrtList) {
        this.name = name;
        this.image = image;
        this.introduce = introduce;
        this.price = price;
        this.arrtList = arrtList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<CommodityDetailsBean.DataBean.ItemsBean.SkuInfoBean.AttrListBean> getArrtList() {
        return arrtList;
    }

    public void setSkuInfos(List<CommodityDetailsBean.DataBean.ItemsBean.SkuInfoBean.AttrListBean> arrtList) {
        this.arrtList = arrtList;
    }
}
