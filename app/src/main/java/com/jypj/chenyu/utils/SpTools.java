package com.jypj.chenyu.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ChenYu on 28/06/2016.
 */
public class SpTools {

    /**
     * 设置spboolean
     * @param context
     * @param key 关键字
     * @param value 值
     */
    public static void setSpBoolean(Context context,String key,boolean value){
        SharedPreferences sp = context.getSharedPreferences(Constants.SP_CONFIG, Context.MODE_PRIVATE);
        sp.edit().putBoolean(key,value).commit();
    }

    /**
     *  获取设置的boolean值
     * @param context
     * @param key
     * @param defaultValue 默认值
     * @return
     */
    public static boolean getSpBoolean(Context context,String key,boolean defaultValue){
        SharedPreferences sp = context.getSharedPreferences(Constants.SP_CONFIG, Context.MODE_PRIVATE);
        return sp.getBoolean(key,defaultValue);
    }

    /**
     * 设置spString
     * @param context
     * @param key 关键字
     * @param value 值
     */
    public static void setSpString(Context context,String key,String value){
        SharedPreferences sp = context.getSharedPreferences(Constants.SP_CONFIG, Context.MODE_PRIVATE);
        sp.edit().putString(key,value).commit();
    }

    /**
     *
     * @param context
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getSpString(Context context,String key,String defaultValue){
        SharedPreferences sp = context.getSharedPreferences(Constants.SP_CONFIG, Context.MODE_PRIVATE);
        return sp.getString(key,defaultValue);
    }

}
