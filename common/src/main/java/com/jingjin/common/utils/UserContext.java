package com.jingjin.common.utils;

/**
 * ClassName: UserContext
 * Description:
 *
 * @Author zjm
 */
public class UserContext {
    private static final ThreadLocal<String> tl = new ThreadLocal<>();

//    保存当前登录用户信息到ThreadLocal
//    Params:userld-用户id
    public static void setUserId(String userId){ tl.set(userId);}

//    获取当前登录用户信息
//    Returns:用户id
    public static String getUserId(){ return tl.get();}

//    移除当前登录用户信息
    public static void removeUser(){ tl.remove();}
}
