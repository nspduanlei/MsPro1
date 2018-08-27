package com.app.dl.networklib.server;

/**
 * Created by duanlei on 2018/4/24.
 */

public class ErrorCode {

    /*
     * 请求成功
     */
    public static final int RESP_SUCCESS=0;
    /*
     * 账号已登陆
     */
    public static final int RESP_LOGINED=1;
    /*
     * 账号或数据已存在
     */
    public static final int RESP_EXISTED=2;
    /**
     * 请求失败 查库异常
     */
    public static final int RESP_FAILED=-1;
    /**
     * 参数格式异常
     */
    public static final int RESP_FORMAT=-2;
    /*
     * 账号不存在
     */
    public static final int RESP_NOEXIST=-3;
    /**
     * 未登录
     */
    public static final int RESP_UNLOGIN=-4;
    /**
     * 缺少必要參數
     */
    public static final int RESP_LACKED=-5;
    /**
     * 请求超时
     */
    public static final int RESP_TIMEOUT=-6;
    /**
     * 无效参数
     */
    public static final int RESP_INVAILD=-7;
    /**
     * 权限不足
     */
    public static final int RESP_PERMISSION=-8;
}
