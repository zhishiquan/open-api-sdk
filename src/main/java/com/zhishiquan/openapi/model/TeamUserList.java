package com.zhishiquan.openapi.model;

import lombok.Builder;
import lombok.Data;

/**
 * 团队成员实体
 */
@Data
@Builder
public class TeamUserList {

    /**
     * 手机号码
     */
    private String mobile;
}
