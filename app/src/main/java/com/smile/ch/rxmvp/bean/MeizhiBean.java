package com.smile.ch.rxmvp.bean;

import java.io.Serializable;

/**
 * Author：CHENHAO
 * date：2018/3/25
 * desc：
 */

public class MeizhiBean implements Serializable {
    private String url;
    private String desc;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
