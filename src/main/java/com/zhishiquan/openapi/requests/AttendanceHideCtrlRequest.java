package com.zhishiquan.openapi.requests;

import com.zhishiquan.openapi.model.AttendanceCreate;
import com.zhishiquan.openapi.model.AttendanceHideCtrl;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 创建训练营request
 * @author jl
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AttendanceHideCtrlRequest extends Request {
    private AttendanceHideCtrl data;
}
