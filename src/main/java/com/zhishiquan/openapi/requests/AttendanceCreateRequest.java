package com.zhishiquan.openapi.requests;

import com.zhishiquan.openapi.model.AttendanceCreate;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 创建训练营request
 * @author jl
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AttendanceCreateRequest extends Request {
    private AttendanceCreate data;
}
