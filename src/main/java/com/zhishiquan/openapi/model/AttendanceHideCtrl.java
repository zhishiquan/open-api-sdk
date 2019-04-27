package com.zhishiquan.openapi.model;

import lombok.Builder;
import lombok.Data;

/**
 * 创建训练营request
 * @author jl
 */
@Data
@Builder
public class AttendanceHideCtrl {
    /**
     * 课程id
     */
    private String activityId;

    /**
     * 是否隐藏
     */
    private Boolean isHide;
}
