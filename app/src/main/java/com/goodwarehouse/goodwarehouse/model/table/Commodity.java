package com.goodwarehouse.goodwarehouse.model.table;

/**
 * Created by HaoMeng on 2017-07-20.
 */

public class Commodity {
    public static final String TABLE_NAME = "commodity";
    public static final String COMM_NAME = "comm_name";
    public static final String COMM_ID = "comm_id";
    public static final String COMM_PRICE = "comm_price";
    public static final String COMM_IMAGE = "comm_image";
    public static final String COMM_DISCOUNT = "comm_discount";
    public static final String COMM_COUNT = "comm_count";
    public static final String COMM_SUM = "comm_sum";
    public static final String COMM_COLOUR = "comm_colour";
    public static final String COMM_STANDARD = "comm_standard";
    public static final String COMM_BRAND_NAME = "brandName";
    public static final String COMM_ORIGINAL_PRICE = "comm_original_price";
    public static final String COMM_ISCHECKED = "comm_ischecked";

    public static final String CREATE_TABLE = "create table " + TABLE_NAME + "("

            + COMM_ID + " text primary key, "
            + COMM_BRAND_NAME + " text, "
            + COMM_NAME + " text, "
            + COMM_PRICE + " text, "
            + COMM_ORIGINAL_PRICE + " text, "
            + COMM_DISCOUNT + " text, "
            + COMM_IMAGE + " text, "
            + COMM_COUNT + " integer, "
            + COMM_COLOUR + " text, "
            + COMM_STANDARD + " text, "
            + COMM_ISCHECKED + " integer, "
            + COMM_SUM + " text )";
}
