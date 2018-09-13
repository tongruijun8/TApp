package com.trj.tlib.assist;

/**
 * Created by Administrator on 2017/10/9.
 */

public class Constants {

    public static final String NET_NONE = "无网络";
    public static final String NET_MOBILE = "移动数据";
    public static final String NET_WIFI = "wifi";

    /** 上下线参数 */
    public static boolean upDownLine = false;
    /** 是否抢单成功 */
//    public static boolean qdCuccess = false;

    public static String token = "";
    public static String helperCode = "";

    public static int isHealthApprove  = 0;
    public static int isIdentityApprove = 0;
    public static int isQualificationApprove  = 0;

    //雇工点击到达的距离
    public static final float DISTANCE =1000;

    public static final String wxAppid = "wx0fb6ca61036c7376";
    public static final String wxMchid = "1495160652";

    //科大讯飞的appid
    public static final String kdAppid = "5a67f463";

    public static class CInt{

        public static final int requestCode = 100;
        public static final int resultCode = 200;
        public static final int resultCode_city = 201;
        public static final int resultCode_pjinfo = 210;
        public static final int resultCode_lookimg = 211;
        public static final int resultCode_jkz = 212;
        public static final int resultCode_zgz = 213;
        public static final int resultCode_head = 214;
        public static final int resultCode_sqfb = 215;
        public static final int resultCode_user_base_info = 216;
        public static final int resultCode_addjn = 217;
        public static final int resultCode_community = 218;
        public static final int resultCode_gzjl = 219;
        public static final int resultCode_pxjl = 220;
        public static final int resultCode_rzzs = 221;
        // 技能信息
        public static final int resultCode_jninfo = 222;
        // 认证信息
        public static final int resultCode_rzinfo = 223;

        /**
         * 地址相关
         */
        public static final int resultCode_new_address = 220;

        public static final int TX_ZFB = 1;
        public static final int TX_WX = 2;

        /**
         * 位置权限
         */
        public static final int LOCATION  = 1001;
        public static final int CALL_PHONE = 1002;

        /**
         * 加载刷新时间
         */
        public static final int TIME_LR = 2000;


    }

    public static class CString{

        public static final String QD_SUCCESS = "您已经抢过一个单了";

        public static final String NO_UP_LINE = "还未上线...";
        public static final String UP_LINE = "上线中...";
        public static final String DOWN_LINE = "下线中...";

        public static final String NET_NONE = "无网络";
        public static final String UP_LOAD_SUCCESS = "上传成功";

        public static final String PHONE_ERROR = "请填写正确的手机号码";
        public static final String PHONE_ERROR_1 = "请填写当前用户的手机号码";

        public static final String NULL = "手机号不能为空";
        public static final String PASSWORD_NULL = "密码不能为空";
        public static final String NAME_OR_PASSWORD_ERROR = "用户名或密码错误";

        public static final String PHONE_EXIST = "此手机号已经存在";

        public static final String DEFAULT_SELECT = "请选择";

        public static final String NOT_OPEN = "暂未开通";

        public static final String FAILE_REQUEST = "请求失败";

        public static final String DATA_ERROR = "数据异常";


        public static final String NAME_NULL = "姓名不能为空";
        public static final String HEIGHT_NULL = "身高不能为空";
        public static final String WEIGHT_NULL = "体重不能为空";
        public static final String EVALUATION_NULL = "个人评价不能为空";


        public static final String DIALOG_MSG_UP_LOAD = "上传中...";
        public static final String DIALOG_MSG_LOAD = "加载中...";
        public static final String DIALOG_MSG_REQUEST = "请求中...";
        public static final String DIALOG_MSG_DELETE= "删除中...";
        public static final String DIALOG_MSG_SAVE = "保存中...";
        public static final String DIALOG_MSG_SUBMIT = "提交中...";
        public static final String DIALOG_MSG_QD = "抢单中...";
        public static final String DIALOG_MSG_ZHIFU = "支付中...";
        public static final String DIALOG_MSG_TIXIAN = "提现中...";
        public static final String DIALOG_MSG_BINDING = "绑定中...";
        public static final String DIALOG_MSG_HONGBAO = "领奖中…";

        public static final String NO_MONEY = "没有可提现的余额";

        public static final String NO_MORE = "没有更多了";

        /* 推送相关 */
        //有雇主下单
        public static final String order_new = "new-Order";
        //雇主预约雇工
        public static final String order_appointment = "appointment-InterviewTime";
        //雇主更改了面试时间
        public static final String order_change_time = "Change-InterviewTime";
        //雇主取消面试时间
        public static final String order_cancle_time = "cacel-InterviewTime";
        //雇工已到达
        public static final String started_InterviewTime = "started-InterviewTime";

        //面试失败
        public static final String order_faile = "fail-InterviewTime";
        //面试成功
        public static final String order_success = "appointment-InterviewTime";

    }

    public static class UserInfoString{

        /** 初始化数据 */
        public static final String INIT_DATA = "initdata";

        public static final String PHONE = "user_phone";
        public static final String LOGO = "user_logo";
        public static final String SERVICE_TYPE = "user_servicetype";
        public static final String INIT_PSW = "user_initpsw";
        public static final String token = "user_token";
        public static final String helperCode = "user_helpercode";
        public static final String isHealthApprove = "user_isHealthApprove";
        public static final String isIdentityApprove = "user_isIdentityApprove";
        public static final String isQualificationApprove = "user_isQualificationApprove";
        public static final String rating = "user_rating";
        public static final String orderNumber = "user_ordernumber";

        public static final String kcSearchHistory = "user_kc_search_history";
        public static final String lifeSearchHistory = "user_life_search_history";
        public static final String scSearchHistory = "user_sc_search_history";

        //判断第一次进入App
        public static final String appVersions = "app_versions";
        public static final String appFirstIn = "app_first_in";

    }

    public static class DialogString{
        public static final String HONGBAO = "计算中…";
    }

}
