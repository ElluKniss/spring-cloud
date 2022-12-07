package com.dw.domain.base;

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


    /**
     * 越权校验：错误返回信息
     */
    public static final String CODE = "1111";

    public static final String DESC = "权限不足";
    
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
    
    
    public static final String STATUS_NO_ACCESS_RIGHT = "3003";
    
    
    public static final String MSG_NO_ACCESS_RIGHT = "无访问权限！";
    
    
    
    public static final String STATUS_AUTH_FAIL = "3004";
    
    
    public static final String MSG_AUTH_FAIL = "鉴权失败！";
    
    
    public static final String STATUS_PAY_ORD_EXISTS = "3005";
    
    
    public static final String MSG_PAY_ORD_EXISTS = "支付订单重复创建！";
    
    public static final String STATUS_PAY_ORD_NOT_EXISTS = "3006";
    
    
    public static final String MSG_PAY_ORD_NOT_EXISTS = "支付订单不存在！";
    
    
    public static final String STATUS_PAY_ORD_PAY_DUPLICATE = "3007";
    
    
    public static final String MSG_PAY_ORD_PAY_DUPLICATE = "订单重复支付！";
    
    public static final String STATUS_REQ_DECRYPT_FAIL = "3008";
    
    public static final String MSG_REQ_DECRYPT_FAIL = "请求消息解密失败！";

    public static final String MSG_WOGANME_FAIL = "释放云盘空间需要objectId";

    public static final String MSG_CLOUD_FAIL = "成功";
    
    /**
     * 网络连接超时，失败:'3002'-返回信息
     */
    public static final String TXID_FAIL = "TxidError0000!";

    public static final String CUEI_UNBIND_TEL_FALSE = "6003";
    public static final String CUEI_UNBIND_TEL_FALSE_MSG= "该设备未绑定固话！";

    public static final String TEL_IS_CLOSE = "4005";
    public static final String TEL_IS_CLOSE_MSG= "固话已销户";

    public static final String CUEI_BIND_NULL = "4017";
    public static final String CUEI_BIND_NULL_MSG= "未查询到绑定关系！";

    public static final String TOKEN_FAIL = "1001";
    public static final String TOKEN_FAIL_MSG = "无效登录信息";

    public static final String PUSH_NOTOKEN= "1002";
    public static final String PUSH_NOTOKEN_MSG= "没有设备token";


    /**
     * 请求参数错误，失败：'1005'  云盘空间操作
     */
    public static final String CODE_EINVAL = "0000";

    public static final String PUSH_NOTEMPALTE = "1003";
    public static final String PUSH_NOTEMPALTE_MSG = "没有推送模板或无权限";

    public static final String PUSH_ERRORTEMPALTE = "1004";
    public static final String PUSH_ERRORTEMPALTE_MSG = "推送模板参数有误";

    public static final String NO_PHONE_INFO = "1006";
    public static final String NO_PHONE_INFO_MSG = "未查询到号码信息";


    public static final String NO_SPUID_INFO = "1007";
    public static final String NO_SPUID_INFO_MSG = "SP商用户唯一标识不能为空";

    public static final String NO_SPUIP_INFO = "1008";
    public static final String NO_SPUIP_INFO_MSG = "SP用户上网时的公网IP不能为空";

    public static final String NO_SPUPORT_INFO = "1009";
    public static final String NO_SPUPORT_INFO_MSG = "SP用户上网时公网端口不能为空";

    public static final String NO_PRODUCT_INFO = "1010";
    public static final String NO_PRODUCT_INFO_MSG = "提速产品ID不能为空";

    public static final String NO_ADCODE_INFO = "1011";
    public static final String NO_ADCODE_INFO_MSG = "SP用户所属区域编号不能为空";

    public static final String NO_SNCODE_INFO = "1012";
    public static final String NO_SNCODE_INFO_MSG = "设备SN码不能为空";

    public static final String NO_ORDERTIME_INFO = "1013";
    public static final String NO_ORDERTIME_INFO_MSG = "设备SN码不能为空";

    public static final String NO_STARTTIME_INFO = "1014";
    public static final String NO_STARTTIMR_INFO_MSG = "设备SN码不能为空";

    public static final String NO_ENDTIME_INFO = "1015";
    public static final String NO_ENDTIME_INFO_MSG = "设备SN码不能为空";


    /**
     * 光猫测速
     */
    public static final String OPTICAL_MODEM_SPEED_DEVICE_INFO = "1016";
    public static final String OPTICAL_MODEM_SPEED_DEVICE_INFO_MSG = "设备不在线,指令发送失败";
    public static final String OPTICAL_MODEM_SPEED_SEND_INFO = "1017";
    public static final String OPTICAL_MODEM_SPEED_SEND_INFO_MSG = "发起测速成功";
    public static final String OPTICAL_MODEM_SPEED_SEND_AGAIN = "1018";
    public static final String OPTICAL_MODEM_SPEED_SEND_AGAIN_MSG= "请继续发起测速";

    public static final String oauth_user_name = "oauth_user_name:";
}
