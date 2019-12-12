package com.mihua.yangben.interfaces;

/**
 * Created by 李浩 on 2019/4/28.
 */

public interface OkhttpRequest {
    void requestSuccess(String Message);

    void requestErr(String errMessage);
}
