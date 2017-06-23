package com.sag.library.model.impl;

import com.sag.library.model.Model;

/**
 * 网络访问实体基类
 * <p>
 * Created by SAG on 2017/6/12 0012.
 */

public class BaseModel implements Model {

    private int status;
    private String out_txt;

    @Override
    public boolean isOk() {
        return status == 1;
    }

    @Override
    public String getMessage() {
        return out_txt;
    }

}
