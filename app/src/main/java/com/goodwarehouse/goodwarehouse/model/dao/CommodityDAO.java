package com.goodwarehouse.goodwarehouse.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

import com.goodwarehouse.goodwarehouse.bean.CommodityInfo;
import com.goodwarehouse.goodwarehouse.model.db.DBHelper;
import com.goodwarehouse.goodwarehouse.model.table.Commodity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HaoMeng on 2017-07-20.
 */

public class CommodityDAO {
    private final DBHelper dbHelper;

    public CommodityDAO(Context context) {
        dbHelper = new DBHelper(context, 1);
    }

    /*
    * 添加商品
    * */
    public void addCommodity(CommodityInfo commInfo) {
        if (commInfo == null) {
            throw new NullPointerException("添加商品不可为空");
        }
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(Commodity.COMM_ID, commInfo.getCommID());
        content.put(Commodity.COMM_NAME, commInfo.getCommName());
        content.put(Commodity.COMM_PRICE, commInfo.getCommPrice());
        content.put(Commodity.COMM_IMAGE, commInfo.getCommImage());
        content.put(Commodity.COMM_DISCOUNT, commInfo.getCommDiscount());
        content.put(Commodity.COMM_COUNT, commInfo.getCommCount());
        content.put(Commodity.COMM_SUM, commInfo.getCommSum());
        content.put(Commodity.COMM_COLOUR, commInfo.getCommColocr());
        content.put(Commodity.COMM_STANDARD, commInfo.getCommStandard());
        content.put(Commodity.COMM_BRAND_NAME, commInfo.getBrandName());
        content.put(Commodity.COMM_ORIGINAL_PRICE, commInfo.getCommOriginalPrice());
        database.replace(Commodity.TABLE_NAME, null, content);
    }

    /*
    * 根据id删除一条数据
    * */
    public void removeCommodity(String commId) {
        if (TextUtils.isEmpty(commId)) {
            throw new NullPointerException("commId 不能为空");
        }
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.delete(Commodity.TABLE_NAME, Commodity.COMM_ID + "=?", new String[]{commId});
    }

    /*
    * 查询所有商品信息
    * */
    public List<CommodityInfo> getCommAll() {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        String sql = "select * from " + Commodity.TABLE_NAME;
        Cursor cursor = database.rawQuery(sql, null);
        List<CommodityInfo> commodities = new ArrayList<>();
        while (cursor.moveToNext()) {
            CommodityInfo comm = new CommodityInfo();
            comm.setCommID(cursor.getString(cursor.getColumnIndex(Commodity.COMM_ID)));
            comm.setCommName(cursor.getString(cursor.getColumnIndex(Commodity.COMM_NAME)));
            comm.setCommPrice(cursor.getString(cursor.getColumnIndex(Commodity.COMM_PRICE)));
            comm.setCommDiscount(cursor.getString(cursor.getColumnIndex(Commodity.COMM_DISCOUNT)));
            comm.setCommCount(cursor.getInt(cursor.getColumnIndex(Commodity.COMM_COUNT)));
            comm.setCommSum(cursor.getString(cursor.getColumnIndex(Commodity.COMM_SUM)));
            comm.setCommColocr(cursor.getString(cursor.getColumnIndex(Commodity.COMM_COLOUR)));
            comm.setCommStandard(cursor.getString(cursor.getColumnIndex(Commodity.COMM_STANDARD)));
            comm.setCommOriginalPrice(cursor.getString(cursor.getColumnIndex(Commodity.COMM_ORIGINAL_PRICE)));
            comm.setBrandName(cursor.getString(cursor.getColumnIndex(Commodity.COMM_BRAND_NAME)));
            comm.setCommImage(cursor.getString(cursor.getColumnIndex(Commodity.COMM_IMAGE)));
            commodities.add(comm);
        }
        cursor.close();
        return commodities;
    }
    /*
    * 修改商品
    * */
    public void updataCommodity(String commId, String count) {
        if (TextUtils.isEmpty(commId)) {
            throw new NullPointerException("commID 不能为空");
        }
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        ContentValues content = new ContentValues();
        content.put(Commodity.COMM_COUNT, count);
        database.update(Commodity.TABLE_NAME, content, Commodity.COMM_ID + "=?", new String[]{commId});
    }
}