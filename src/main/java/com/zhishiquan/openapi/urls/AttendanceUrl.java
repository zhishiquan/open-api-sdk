package com.zhishiquan.openapi.urls;

import com.zhishiquan.openapi.common.URLConstants;

/**
 * 训练营接口url
 * @author jl
 */
public final class AttendanceUrl extends AbstractBaseUrl {

    private AttendanceUrl(String name, String url) {
        super(name, url);
    }

    /**
     * 创建训练营
     */
    public static AttendanceUrl create(boolean isSandbox) {
        String host = URLConstants.PRODUCT;
        if(isSandbox) {
            host = URLConstants.SANDBOX;
        }
        return new AttendanceUrl("创建训练营", host.concat("/auth/proxy/attendance/v1/create"));
    }

    /**
     * 隐藏/展示训练营
     */
    public static AttendanceUrl hideCtrl(boolean isSandbox) {
        String host = URLConstants.PRODUCT;
        if(isSandbox) {
            host = URLConstants.SANDBOX;
        }
        return new AttendanceUrl("训练营隐藏/显示", host.concat("/auth/proxy/attendance/v1/hideCtrl"));
    }
}
