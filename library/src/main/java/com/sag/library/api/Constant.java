package com.sag.library.api;

/**
 * 应用配置文件
 * <p>
 * Created by SAG on 2017/6/12 0012.
 */

public interface Constant {

    boolean isDebug = true;
    String baseURL = "http://www.debug.com";//使用全路径url，该值无效

    boolean isEncrypt = false;
    //加密类型
    int Encrypt_TYPE = 0;
    int Encrypt_ONE = 0;//加密参数
    int Encrypt_TWO = 1;//加密url
    String Encrypt_KEY = "json";//加密后的参数对应的字段

    interface Prompt {
        int ALWAYS = 0x001;
        int NEVER = 0x002;
        int ON_ERROR = 0x003;
    }

    String debug_path = "/sdcard/debug/";

    String DEFAULT_FILE_NAME = "data";

}
