package com.dw.entity;

public class ServiceConstant {

    /**
     * 正确，成功：'200'
     */
    public static final String STATUS_SUCCESS = "200";

    /**
     * 正确，成功：'200'-返回信息
     */
    public static final String MSG_SUCCESS = "服务调用成功！";
    /**
     * 数据处理正确，成功：'0000'
     */
    public static final String RESP_SUCCESS = "0000";

    /**
     * 数据处理正确，成功：'0000'-返回信息
     */
    public static final String MSG_RESP_SUCCESS = "成功";

    /**
     * 数据处理失败，成功：'9999'
     */
    public static final String STATUS_ERR = "9999";

    /**
     * 数据处理失败，成功：'9999'-返回信息
     */
    public static final String MSG_ERR = "系统异常";

    /**
     * 请求参数错误，失败：'1000'
     */
    public static final String STATUS_EINVAL = "1000";

    /**
     * 请求参数错误，失败：'1000'-返回信息
     */
    public static final String MSG_EINVAL = "出错啦！请求参数错误，请检查！";

    public static final String MSG_EINVALS = "时间至少必须要有一个参数不为空！";


    /**
     * 服务降级错误，失败:'2000'
     */
    public static final String STATUS_FALLBACK = "2000";

    /**
     * 服务降级错误，失败:'2000'-返回信息
     */
    public static final String MSG_FALLBACK = "服务降级";

    /**
     * 系统错误（网络连接错误/超时），失败:'3000'
     */
    public static final String STATUS_SYSERROR = "3000";

    /**
     * 系统错误（网络连接错误/超时），失败:'3000'-返回信息
     */
    public static final String MSG_SYSERROR = "出错啦！网络连接错误或者连接超时！";


    /**
     * 网络连接错误，失败:'3001'
     */
    public static final String STATUS_CONNECERROR = "3001";

    /**
     * 网络连接错误，失败:'3001'-返回信息
     */
    public static final String MSG_CONNECERROR = "网络连接出错啦！";

    /**
     * 网络连接超时，失败:'3002'
     */
    public static final String STATUS_CONNECTIMEOUT = "3002";

    /**
     * 网络连接超时，失败:'3002'-返回信息
     */
    public static final String MSG_CONNECTIMEOUT = "网络连接超时啦！";

    /**
     * 网络连接超时，失败:'3002'-返回信息
     */
    public static final String TXID_FAIL = "TxidError0000!";

    public static final String USER_CENTER_YUNYII_ACCESSTOKEN = "USER_CENTER_YUNYII_ACCESSTOKEN";//账户中心在云易侧是accesstoken
    public static final String USER_CENTER_CGATEWAY_ACCESSTOKEN = "USER_CENTER_CGATEWAY_ACCESSTOKEN";//账户中心在云易侧是accesstoken

    public static final String oauth_user_name = "oauth_user_name:";

    private ServiceConstant(){
    }


    public static final String ERROR_1001 = "1001";
    public static final String ERROR_1001_DESC = "手机号下有多个固话号码！";
    public static final String ERROR_1002 = "1002";
    public static final String ERROR_1002_DESC = "手机号下有多个音响！";
    public static final String ERROR_1003 = "1003";
    public static final String ERROR_1003_DESC = "固话的归属地和音箱上报的归属地不一致！";
    public static final String ERROR_1004 = "1004";
    public static final String ERROR_1004_DESC = "终端信息未上报";

    public static final String ERROR_1005 = "1005";
    public static final String ERROR_1005_DESC = "家庭共享失败";

    public static final String ERROR_1006 = "1006";
    public static final String ERROR_1006_DESC = "操作失败";

    public static final String ERROR_1007 = "1007";
    public static final String ERROR_1007_DESC = "手机号下有设备关联！";

    public static final String ERROR_1008 = "1008";
    public static final String ERROR_1008_DESC = "该音箱已经被绑定！";


    public static final String ERROR_2020 = "2020";
    public static final String ERROR_2020_DESC = "操作权限不足！";

    public static final String ERROR_2021 = "2021";
    public static final String ERROR_2021_DESC = "此渠道必传空间类型！";

    public static final String NO_DEVINFO = "DEV00001";
    public static final String NO_DEVINFO_DESC = "未查询到发展人信息";

    //宽带提速
    public static final String LAN_ACTIVATING_CODE = "4000";
    public static final String LAN_ACTIVATING_DESC = "已有提速包正在提速中，请勿重复提速。";
    public static final String LAN_ACTIVATED_CODE = "4001";
    public static final String LAN_ACTIVATED_DESC = "已经自提速";
    public static final String LAN_ACTIVATE_FAIL_CODE = "4002";
    public static final String LAN_ACTIVATE_FAIL_DESC = "激活失败";
    public static final String LAN_AUTO_ACTIVATE_FAIL_CODE = "4003";
    public static final String LAN_AUTO_ACTIVATE_FAIL_DESC = "当前宽带自动提速失败";
    public static final String LAN_ACTIVATING_ERROR_CODE = "4004";
    public static final String LAN_ACTIVATING_ERROR_DESC = "请连接订购该提速包的WiFi";
    public static final String LAN_ACTIVATED_CODE_4005 = "4005";
    public static final String LAN_ACTIVATED_DESC_4005 = "已经由其他用户提速";

    /**
     * 设备密钥不存在
     */
    public static final String DEVICE_SECRET_NO_EXIST = "5001";
    /**
     * 签名错误
     */
    public static final String SIGN_FAIL_CODE = "5002";
    /**
     * 设备没有绑定手机号
     */
    public static final String DEVICE_PHONE_NO_BIND = "5003";
    /**
     * 获取at失败
     */
    public static final String AT_GET_FAIL = "5004";

}
