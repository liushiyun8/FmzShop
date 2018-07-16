package top.yundesign.fmz.Manager;

public class HttpManager {
    public static final String HOST="";
    public static final String LOGINURL=HOST+"/api/user/login";
    public static final String REGISTERURL=HOST+"/api/user/register";
    public static final String SENDEMSG=HOST+"/api/user/sendMessage";
    public static final String UPDATE_PWD=HOST+"/api/user/updatePassword";
    private static final String GET_USERINFO =HOST+"/api/user/getUserInfo";
    public static final String  UPDATE_USERINFO=HOST+"/api/user/updateUserInfo";
}
