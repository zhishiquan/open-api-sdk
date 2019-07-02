package com.zhishiquan.openapi.view;

import lombok.Data;

/**
 * 团队成员列表View
 * @author weiys
 * @since 2018/12/22 11:45
 */
@Data
public class TeamUserView {
    /**
     * 团队id
     */
    private Long teamId;
    /**
     * 团队中的成员名称
     */
    private String name;
    /**
     * 电话
     */
    private String mobile;
    /**
     * 微信
     */
    private String wechat;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 地区
     */
    private String region;
    /**
     * 邀请人
     */
    private String inviter;
    /**
     * 用户
     */
    private UserLessView userLessView;
    /**
     * 关联课程数
     */
    private Long activityCount;
}
