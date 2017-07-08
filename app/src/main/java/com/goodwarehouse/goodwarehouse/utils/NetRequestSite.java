package com.goodwarehouse.goodwarehouse.utils;

/**
 * Created by HaoMeng on 2017-07-06.
 */

public class NetRequestSite {
    public static final int PAGE = 1;
    //商店 - 分类
    public static final String TYPE_URL = "http://mobile.iliangcang.com/goods/goodsCategory?app_key=Android&sig=430BD99E6C913B8B8C3ED109737ECF15%7C830952120106768&v=1.0";
    //商店 - 品牌
    public static final String BRAND_FROEPART_URL = "http://mobile.iliangcang.com/brand/brandList?app_key=Android&count=20&page=";
    public static final String BRAND_END_URL = "&sig=430BD99E6C913B8B8C3ED109737ECF15%7C830952120106768&v=1.0";
    public static final String BRAND_URL = BRAND_FROEPART_URL + PAGE + BRAND_END_URL;
    //商店 - 品牌 - 详情页
    public static final String BRAND_INFO_FROEPART_URL = "http://mobile.iliangcang.com/brand/brandShopList?app_key=Android&brand_id=";
    public static final String BRAND_INFO_END_URL = "&count=20&page=1&sig=430BD99E6C913B8B8C3ED109737ECF15%7C830952120106768&v=1.0";

    //商店 - 专题
    public static final String SPECIAL_FROEPART_URL = "http://mobile.iliangcang.com/goods/shopSpecial?app_key=Android&count=10&page=";
    public static final String SPECIAL_END_URL = "&sig=3780CB0808528F7CE99081D295EE8C0F%7C116941220826768&uid=626138098&user_token=0516ed9429352c8e1e3bd11c63ba6f54&v=1.0";
    public static final String SPECIAL_URL = SPECIAL_FROEPART_URL + PAGE + SPECIAL_END_URL;
    /*
    * 首页
    * */
    public static final String HOME_PAGE_URL = "http://mobile.iliangcang.com/goods/newShopHome?app_key=Android&sig=3780CB0808528F7CE99081D295EE8C0F%7C116941220826768&uid=626138098&user_token=0516ed9429352c8e1e3bd11c63ba6f54&v=1.0";

    //达人
    public static final String EXPERT_FROEPART_URL = "http://mobile.iliangcang.com/user/masterList?app_key=Android&count=18&page=";
    public static final String EXPERT_END_URL = "&sig=79F01B94B8EBEFAC8EEB344EE2B20AA2%7C383889010803768&v=1.0";
    public static final String EXPERT_URL = EXPERT_FROEPART_URL + PAGE + EXPERT_END_URL;

    //商店 - 分类 - 家居
    public static final String TYPE_HOME_FROEPART_URL = "http://mobile.iliangcang.com/goods/goodsShare?app_key=Android&cat_code=0045&count=10&coverId=1&page=";
    public static final String TYPE_HOME_END_URL = "&sig=3D3968703BE211CC6D931C9D4F737FB4%7C290216330933368&v=1.0";
    public static final String HOME_URL = TYPE_HOME_FROEPART_URL + PAGE + TYPE_HOME_END_URL;
    //商店 - 分类 - 家具
    public static final String TYPE_FITMENT_FROEPART_URL = "http://mobile.iliangcang.com/goods/goodsShare?app_key=Android&cat_code=0055&count=10&coverId=1&page=";
    public static final String TYPE_FITMENT_END_URL = "&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    public static final String FITMENT_URL = TYPE_FITMENT_FROEPART_URL + PAGE + TYPE_FITMENT_END_URL;
    //商店 - 分类 - 文具
    public static final String TYPE_STATIONERY_FROEPART_URL = "http://mobile.iliangcang.com/goods/goodsShare?app_key=Android&cat_code=0062&count=10&coverId=1&page=";
    public static final String TYPE_STATIONERY_END_URL = "&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    public static final String STATIONERY_URL = TYPE_STATIONERY_FROEPART_URL + PAGE + TYPE_STATIONERY_END_URL;
    //商店 - 分类 - 数码
    public static final String TYPE_NUMERICAL_FROEPART_URL = "http://mobile.iliangcang.com/goods/goodsShare?app_key=Android&cat_code=0069&count=10&coverId=1&page=";
    public static final String TYPE_NUMERICAL_END_URL = "&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    public static final String NUMERICAL_URL = TYPE_NUMERICAL_FROEPART_URL + PAGE + TYPE_NUMERICAL_END_URL;

    //商店 - 分类 - 玩乐
    public static final String TYPE_PLAY_FROEPART_URL = "http://mobile.iliangcang.com/goods/goodsShare?app_key=Android&cat_code=0077&count=10&coverId=1&page=";
    public static final String TYPE_PLAY_END_URL = "&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    public static final String PLAY_URL = TYPE_PLAY_FROEPART_URL + PAGE + TYPE_PLAY_END_URL;

    //商店 - 分类 - 厨卫
    public static final String TYPE_KITCHEN_FROEPART_URL = "http://mobile.iliangcang.com/goods/goodsShare?app_key=Android&cat_code=0082&count=10&coverId=1&page=";
    public static final String TYPE_KITCHEN_END_URL = "&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    public static final String KITCHEN_URL = TYPE_KITCHEN_FROEPART_URL + PAGE + TYPE_KITCHEN_END_URL;

    //商店 - 分类 - 美食
    public static final String TYPE_CATE_FROEPART_URL = "http://mobile.iliangcang.com/goods/goodsShare?app_key=Android&cat_code=0092&count=10&coverId=1&page=";
    public static final String TYPE_CATE_END_URL = "&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    public static final String CATE_URL = TYPE_CATE_FROEPART_URL + PAGE + TYPE_CATE_END_URL;

    //商店 - 分类 - 男装
    public static final String TYPE_MENWEAR_FROEPART_URL = "http://mobile.iliangcang.com/goods/goodsShare?app_key=Android&cat_code=0101&count=10&coverId=1&page=";
    public static final String TYPE_MENWEAR_END_URL = "&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    public static final String MENWEAR_URL = TYPE_MENWEAR_FROEPART_URL + PAGE + TYPE_MENWEAR_END_URL;
    //商店 - 分类 - 女装
    public static final String TYPE_FROCK_FROEPART_URL = "http://mobile.iliangcang.com/goods/goodsShare?app_key=Android&cat_code=0112&count=10&coverId=1&page=";
    public static final String TYPE_FROCK_END_URL = "&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    public static final String FROCK_URL = TYPE_FROCK_FROEPART_URL + PAGE + TYPE_FROCK_END_URL;

    //商店 - 分类 - 童装
    public static final String TYPE_BABYWEAR_FROEPART_URL = "http://mobile.iliangcang.com/goods/goodsShare?app_key=Android&cat_code=0125&count=10&coverId=1&page=";
    public static final String TYPE_BABYWEAR_END_URL = "&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    public static final String BABYWEAR_URL = TYPE_BABYWEAR_FROEPART_URL + PAGE + TYPE_BABYWEAR_END_URL;

    //商店 - 分类 - 鞋包
    public static final String TYPE_SHOE_FROEPART_URL = "http://mobile.iliangcang.com/goods/goodsShare?app_key=Android&cat_code=0129&count=10&coverId=1&page=";
    public static final String TYPE_SHOE_END_URL = "&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    public static final String SHOE_URL = TYPE_SHOE_FROEPART_URL + PAGE + TYPE_SHOE_END_URL;

    //商店 - 分类 - 配饰
    public static final String TYPE_ACC_FROEPART_URL = "http://mobile.iliangcang.com/goods/goodsShare?app_key=Android&cat_code=0141&count=10&coverId=1&page=";
    public static final String TYPE_ACC_END_URL = "&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    public static final String ACC_URL = TYPE_ACC_FROEPART_URL + PAGE + TYPE_ACC_END_URL;

    //商店 - 分类 - 美护
    public static final String TYPE_BEAUTY_FROEPART_URL = "http://mobile.iliangcang.com/goods/goodsShare?app_key=Android&cat_code=0154&count=10&coverId=1&page=";
    public static final String TYPE_BEAUTY_END_URL = "&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    public static final String BEAUTY_URL = TYPE_BEAUTY_FROEPART_URL + PAGE + TYPE_BEAUTY_END_URL;

    //商店 - 分类 - 户外
    public static final String TYPE_OUTDOORS_FROEPART_URL = "http://mobile.iliangcang.com/goods/goodsShare?app_key=Android&cat_code=0166&count=10&coverId=1&page=";
    public static final String TYPE_OUTDOORS_END_URL = "&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    public static final String OUTDOORS_URL = TYPE_OUTDOORS_FROEPART_URL + PAGE + TYPE_OUTDOORS_END_URL;
    //商店 - 分类 - 植物
    public static final String TYPE_PLANT_FROEPART_URL = "http://mobile.iliangcang.com/goods/goodsShare?app_key=Android&cat_code=0172&count=10&coverId=1&page=";
    public static final String TYPE_PLANT_END_URL = "&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    public static final String PLANT_URL = TYPE_PLANT_FROEPART_URL + PAGE + TYPE_PLANT_END_URL;

    //商店 - 分类 - 图书
    public static final String TYPE_BOOK_FROEPART_URL = "http://mobile.iliangcang.com/goods/goodsShare?app_key=Android&cat_code=0182&count=10&coverId=1&page=";
    public static final String TYPE_BOOK_END_URL = "&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    public static final String BOOK_URL = TYPE_BOOK_FROEPART_URL + PAGE + TYPE_BOOK_END_URL;
    //商店 - 分类 - 礼物
    public static final String TYPE_GIFT_FROEPART_URL = "http://mobile.iliangcang.com/goods/goodsShare?app_key=Android&cat_code=0190&count=10&coverId=1&page=";
    public static final String TYPE_GIFT_END_URL = "&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    public static final String GIFT_URL = TYPE_GIFT_FROEPART_URL + PAGE + TYPE_GIFT_END_URL;
    //商店 - 分类 - 推荐
    public static final String TYPE_RECOMMEND_FROEPART_URL = "http://mobile.iliangcang.com/goods/goodsShare?app_key=Android&cat_code=0198&count=10&coverId=1&page=";
    public static final String TYPE_RECOMMEND_END_URL = "&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    public static final String RECOMMEND_URL = TYPE_RECOMMEND_FROEPART_URL + PAGE + TYPE_RECOMMEND_END_URL;
    //商店 - 分类 - 艺术
    public static final String TYPE_ART_FROEPART_URL = "http://mobile.iliangcang.com/goods/goodsShare?app_key=Android&cat_code=0214&count=10&coverId=1&page=";
    public static final String TYPE_ART_END_URL = "&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    public static final String ART_URL = TYPE_ART_FROEPART_URL + PAGE + TYPE_ART_END_URL;

    /*
    * 礼物界面
    * */
    //商店 - 礼物 - 精选
    public static final String TYPE_GIFTSIFT_FROEPART_URL = "http://mobile.iliangcang.com/goods/goodsList?app_key=Android&count=10&list_id=7&page=";
    public static final String TYPE_GIFTSIFT_END_URL = "&sig=73760B2740FA36D5A273523FBC9295FE%7C285269230036268&v=1.0";
    public static final String GIFTSIFT_URL = TYPE_GIFTSIFT_FROEPART_URL + PAGE + TYPE_GIFTSIFT_END_URL;
    //商店 - 礼物 - 节日
    public static final String TYPE_FESTIVAL_FROEPART_URL = "http://mobile.iliangcang.com/goods/goodsList?app_key=Android&count=10&list_id=1&page=";
    public static final String TYPE_FESTIVAL_END_URL = "&sig=DFD7151CC9D607E396FE108FE270FFF3%7C366534120395468&v=1.0";
    public static final String FESTIVAL_URL = TYPE_FESTIVAL_FROEPART_URL + PAGE + TYPE_FESTIVAL_END_URL;
    //商店 - 礼物 - 爱情
    public static final String TYPE_LOVE_FROEPART_URL = "http://mobile.iliangcang.com/goods/goodsList?app_key=Android&count=10&list_id=2&page=";
    public static final String TYPE_LOVE_END_URL = "&sig=73760B2740FA36D5A273523FBC9295FE%7C285269230036268&v=1.0";
    public static final String LOVE_URL = TYPE_LOVE_FROEPART_URL + PAGE + TYPE_LOVE_END_URL;

    //商店 - 礼物 -  生日
    public static final String TYPE_BIRTHDAY_FROEPART_URL = "http://mobile.iliangcang.com/goods/goodsList?app_key=Android&count=10&list_id=3&page=";
    public static final String TYPE_BIRTHDAY_END_URL = "&sig=73760B2740FA36D5A273523FBC9295FE%7C285269230036268&v=1.0";
    public static final String BIRTHDAY_URL = TYPE_BIRTHDAY_FROEPART_URL + PAGE + TYPE_BIRTHDAY_END_URL;
    //商店 - 礼物 -  朋友
    public static final String TYPE_FRIEND_FROEPART_URL = "http://mobile.iliangcang.com/goods/goodsList?app_key=Android&count=10&list_id=4&page=";
    public static final String TYPE_FRIEND_END_URL = "&sig=73760B2740FA36D5A273523FBC9295FE%7C285269230036268&v=1.0";
    public static final String FRIEND_URL = TYPE_FRIEND_FROEPART_URL + PAGE + TYPE_FRIEND_END_URL;
    //商店 - 礼物 -  孩子
    public static final String TYPE_BABY_FROEPART_URL = "http://mobile.iliangcang.com/goods/goodsList?app_key=Android&count=10&list_id=5&page=";
    public static final String TYPE_BABY_END_URL = "&sig=73760B2740FA36D5A273523FBC9295FE%7C285269230036268&v=1.0";
    public static final String BABY_URL = TYPE_BABY_FROEPART_URL + PAGE + TYPE_BABY_END_URL;
    //商店 - 礼物 -  父母
    public static final String TYPE_PARENT_FROEPART_URL = "http://mobile.iliangcang.com/goods/goodsList?app_key=Android&count=10&list_id=6&page=";
    public static final String TYPE_PARENT_END_URL = "&sig=73760B2740FA36D5A273523FBC9295FE%7C285269230036268&v=1.0";
    public static final String PARENT_URL = TYPE_PARENT_FROEPART_URL + PAGE + TYPE_PARENT_END_URL;

}
