package com.goodwarehouse.goodwarehouse.bean;

/**
 * Created by HaoMeng on 2017-07-20.
 */
/*
*   private String commID;　商品ID
    private String commName; 商品名称
    private String commPrice; 商品价格
    private String commImage; 商品图片
    private String commDiscount; 商品折扣金额
    private String commCount; 商品购买数量
    private String commSum; 商品库存数量
* */

public class CommodityInfo {
    private String commID;
    private String brandName;
    private String commPrice;
    private String commOriginalPrice;
    private String commName;
    private String commImage;
    private String commDiscount;
    private int commCount;
    private String commSum;
    private String commColocr;
    private String commStandard;
    private boolean isChecked = true;


    public CommodityInfo() {
    }

    public CommodityInfo(String commID,
                         String brandName,
                         String commPrice,
                         String commOriginalPrice,
                         String commName,
                         String commImage,
                         String commDiscount,
                         int commCount,
                         String commSum,
                         String commColocr,
                         String commStandard) {
//        this(commID,brandName, commPrice, commOriginalPrice, commName, commImage, commDiscount, commCount, commSum, commColocr, commStandard,true);
        this.commID = commID;
        this.brandName = brandName;
        this.commPrice = commPrice;
        this.commOriginalPrice = commOriginalPrice;
        this.commName = commName;
        this.commImage = commImage;
        this.commDiscount = commDiscount;
        this.commCount = commCount;
        this.commSum = commSum;
        this.commColocr = commColocr;
        this.commStandard = commStandard;
    }

    public CommodityInfo(String commID, String brandName, String commPrice, String commOriginalPrice, String commName, String commImage, String commDiscount, int commCount, String commSum, String commColocr, String commStandard, boolean isChecked) {
        this.commID = commID;
        this.brandName = brandName;
        this.commPrice = commPrice;
        this.commOriginalPrice = commOriginalPrice;
        this.commName = commName;
        this.commImage = commImage;
        this.commDiscount = commDiscount;
        this.commCount = commCount;
        this.commSum = commSum;
        this.commColocr = commColocr;
        this.commStandard = commStandard;
        this.isChecked = isChecked;
    }

    public boolean getChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getCommOriginalPrice() {
        return commOriginalPrice;
    }

    public void setCommOriginalPrice(String commOriginalPrice) {
        this.commOriginalPrice = commOriginalPrice;
    }

    public String getCommID() {
        return commID;
    }

    public void setCommID(String commID) {
        this.commID = commID;
    }

    public String getCommName() {
        return commName;
    }

    public void setCommName(String commName) {
        this.commName = commName;
    }

    public String getCommPrice() {
        return commPrice;
    }

    public void setCommPrice(String commPrice) {
        this.commPrice = commPrice;
    }

    public String getCommImage() {
        return commImage;
    }

    public void setCommImage(String commImage) {
        this.commImage = commImage;
    }

    public String getCommDiscount() {
        return commDiscount;
    }

    public void setCommDiscount(String commDiscount) {
        this.commDiscount = commDiscount;
    }

    public int getCommCount() {
        return commCount;
    }

    public void setCommCount(int commCount) {
        this.commCount = commCount;
    }

    public String getCommSum() {
        return commSum;
    }

    public void setCommSum(String commSum) {
        this.commSum = commSum;
    }

    public String getCommColocr() {
        return commColocr;
    }

    public void setCommColocr(String commColocr) {
        this.commColocr = commColocr;
    }

    public String getCommStandard() {
        return commStandard;
    }

    public void setCommStandard(String commStandard) {
        this.commStandard = commStandard;
    }

    @Override
    public String toString() {
        return "CommodityInfo{" +
                "commID='" + commID + '\'' +
                ", brandName='" + brandName + '\'' +
                ", commPrice='" + commPrice + '\'' +
                ", commOriginalPrice='" + commOriginalPrice + '\'' +
                ", commName='" + commName + '\'' +
                ", commImage='" + commImage + '\'' +
                ", commDiscount='" + commDiscount + '\'' +
                ", commCount=" + commCount +
                ", commSum='" + commSum + '\'' +
                ", commColocr='" + commColocr + '\'' +
                ", commStandard='" + commStandard + '\'' +
                ", isChecked=" + isChecked +
                '}';
    }
}
