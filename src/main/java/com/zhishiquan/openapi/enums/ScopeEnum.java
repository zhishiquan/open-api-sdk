package com.zhishiquan.openapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 权限范围枚举
 * @author jl
 */
@AllArgsConstructor
@Getter
public enum ScopeEnum {

    /**
     * 获取训练营权限
     */
    ATTENDANCE("attendance");

    private String scope;
}
