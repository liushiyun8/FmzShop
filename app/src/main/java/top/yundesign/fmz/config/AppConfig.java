package top.yundesign.fmz.config;

import java.io.File;

import top.yundesign.fmz.utils.SDCardUtil;

public class AppConfig {
    public static final String MODULE_CATCHERR = "CRASH";
    public static final String MODULE_APP = "APP";
    public static final String MODULE_SERVER = "SERVER";
    public static final String WORK_PATH = SDCardUtil.getSDCardPath() + "com.yundesign.fmz";
    public static final String WORK_PATH_LOG = WORK_PATH + File.separator + "log";
    public static final String WORK_PHOTO_PATH = WORK_PATH + File.separator + "Photo";
    public static final boolean isDebug = true;
    public static final String APP_NAME = "Fmz";

}
