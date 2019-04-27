package com.zhishiquan.openapi.urls;

import com.zhishiquan.openapi.common.URLConstants;

/**
 * 基础功能接口
 * @author jl
 */
public final class CommonUrl extends AbstractBaseUrl {

    private CommonUrl(String name, String url) {
        super(name, url);
    }

    /**
     * 获取accessToken
     */
    public static CommonUrl accessToken(boolean isSanbox) {
        String host = URLConstants.PRODUCT;
        if(isSanbox) {
            host = URLConstants.SANDBOX;
        }
        return new CommonUrl("获取accessToken", host.concat("/oauth/token"));
    }
}
