package com.zhishiquan.openapi.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 创建训练营request数据
 * @author jl
 */
@Data
@Builder
public class AttendanceCreate {
    /**
     * 训练营名称
     */
    private String name;

    /**
     * 发起人
     */
    private String creator;

    /**
     * 开始时间戳
     */
    private Long startTime;

    /**
     * 结束时间戳
     */
    private Long endTime;

    /**
     * 训练营金额
     */
    private Integer money;

    /**
     * 奖金比例
     */
    private Integer ratio;

    /**
     * 失败天数
     */
    private Integer failDay;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 绑定管理员的userId
     */
    private List<Long> managerUserIds;
}
