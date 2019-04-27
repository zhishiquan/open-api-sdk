package com.zhishiquan.openapi.responses;

import lombok.Data;

/**
 * 创建训练营返回值
 * @author jl
 */
@Data
public class AttendanceCreateResponse extends Response {
    private AttendanceCreateData data;
}
