package com.zhishiquan.openapi.responses;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 创建训练营返回值
 * @author jl
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AttendanceCreateResponse extends Response {
    private AttendanceCreateData data;
}
