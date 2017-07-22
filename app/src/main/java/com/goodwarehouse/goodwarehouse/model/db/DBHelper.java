package com.goodwarehouse.goodwarehouse.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.goodwarehouse.goodwarehouse.model.table.Commodity;

/**
 * Created by HaoMeng on 2017-07-20.
 */

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context, int version) {
        super(context, "cartinfo.db", null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Commodity.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
