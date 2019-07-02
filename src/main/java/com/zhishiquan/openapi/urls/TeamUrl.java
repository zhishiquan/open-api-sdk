package com.zhishiquan.openapi.urls;

import com.zhishiquan.openapi.common.URLConstants;

/**
 * 训练营接口url
 * @author jl
 */
public final class TeamUrl extends AbstractBaseUrl {

    private TeamUrl(String name, String url) {
        super(name, url);
    }

    /**
     * 团队成员列表
     */
    public static TeamUrl users(boolean isSandbox) {
        String host = URLConstants.PRODUCT;
        if(isSandbox) {
            host = URLConstants.SANDBOX;
        }
        return new TeamUrl("团队成员列表", host.concat("/auth/proxy/team/v1/users"));
    }
}
