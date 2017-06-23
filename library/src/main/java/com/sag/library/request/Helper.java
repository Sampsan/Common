package com.sag.library.request;

import java.util.HashMap;

/**
 * Created by SAG on 2017/6/12
 */

interface Helper {

    void post(String method, HashMap<String, Object> parameters, ClientHelper subscriber);

    void get(String method, HashMap<String, Object> parameters, ClientHelper subscriber);

}
