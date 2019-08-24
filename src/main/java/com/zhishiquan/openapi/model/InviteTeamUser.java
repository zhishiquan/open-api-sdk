package com.zhishiquan.openapi.model;

import lombok.Builder;
import lombok.Data;

/**
 * 邀请团队成员
 * @author jl
 */
@Data
@Builder
public class InviteTeamUser {

    /**
     * 邀请数量
     */
    private Integer inviteNum;

    /**
     * 手机号码
     */
    private String mobile;
}
