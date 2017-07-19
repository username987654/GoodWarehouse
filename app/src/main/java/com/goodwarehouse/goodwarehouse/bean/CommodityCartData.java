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
    private List<CommodityDetailsBean.DataBean.ItemsBean.SkuInfoBean> skuinfobean;
    private List<CommodityDetailsBean.DataBean.ItemsBean.SkuInvBean> skuInvBeen;


    public CommodityCartData(String name,
                             String image,
                             String introduce,
                             String price,
                             List<CommodityDetailsBean.DataBean.ItemsBean.SkuInfoBean> skuinfobean,
                             List<CommodityDetailsBean.DataBean.ItemsBean.SkuInvBean> skuInvBeen) {

        this.name = name;
        this.image = image;
        this.introduce = introduce;
        this.price = price;
        this.skuinfobean = skuinfobean;
        this.skuInvBeen = skuInvBeen;
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

    public List<CommodityDetailsBean.DataBean.ItemsBean.SkuInfoBean> getSkuInfoBean() {
        return skuinfobean;
    }

    public void setSkuInfos(List<CommodityDetailsBean.DataBean.ItemsBean.SkuInfoBean> skuinfobean) {
        this.skuinfobean = skuinfobean;
    }

    public List<CommodityDetailsBean.DataBean.ItemsBean.SkuInvBean> getSkuInvBeen() {
        return skuInvBeen;
    }

    public void setSkuInvBeen(List<CommodityDetailsBean.DataBean.ItemsBean.SkuInvBean> skuInvBeen) {
        this.skuInvBeen = skuInvBeen;
    }
}
