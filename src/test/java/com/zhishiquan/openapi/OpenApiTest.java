package com.zhishiquan.openapi;

import com.alibaba.fastjson.JSON;
import com.zhishiquan.openapi.core.OpenApi;
import com.zhishiquan.openapi.model.AttendanceCreate;
import com.zhishiquan.openapi.model.AttendanceHideCtrl;
import com.zhishiquan.openapi.model.InviteTeamUser;
import com.zhishiquan.openapi.model.TeamUserList;
import com.zhishiquan.openapi.responses.AttendanceCreateResponse;
import com.zhishiquan.openapi.responses.AttendanceHideCtrlResponse;
import com.zhishiquan.openapi.responses.InviteTeamUserResponse;
import com.zhishiquan.openapi.responses.TeamUserListResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class OpenApiTest {
    private OpenApi openApi;

    private String activityId;

    /**
     * 初始化OpenApi
     * clientId 由知识圈提供
     * clientSecret 由知识圈提供
     * isSandbox 是否沙盒环境 true - 是 false - 否
     */
    @Before
    public void setUp() {
        this.openApi = OpenApi.builder()
//                .clientId("7OivOtcc7dBdyvi9")
//                .clientSecret("4JLvRaT4sOYqHlJ1j2NVGZm0oLfWVq4U")
                .clientId("client_1")
                .clientSecret("jl")
                .isSandbox(false)
                .build();
    }

    /**
     * 测试创建训练营
     * name 训练营名称
     * creator 课程发起人(用于展示)
     * startTime 开始时间(时间戳)
     * endTime 结束时间(时间戳)
     * failDay 允许失败天数
     * money 课程价格
     * ratio 奖金比例
     * managerUserIds 需要绑定为管理员的userId
     * mobile 老师联系手机号码
     */
    @Test
    public void testAttendanceCreate() {
        List<Long> managerUserIds = new ArrayList<>();
        managerUserIds.add(151L);
        Long now = System.currentTimeMillis();
        AttendanceCreateResponse response = openApi.createAttendance(AttendanceCreate
                .builder()
                .name("JL测试创建训练营6")
                .creator("JL")
                .startTime(now)
                .endTime(now + 3600000L * 24L * 30L)
                .failDay(2)
                .money(200)
                .ratio(50)
                .managerUserIds(managerUserIds)
                .mobile("13428888367")
                .build());
        System.out.println(JSON.toJSONString(response));
        Assert.assertEquals(0, response.getState().getCode());
    }

    /**
     * 控制训练营隐藏/显示
     * activityId 训练营id
     * isHide 是否隐藏 true - 隐藏, false - 展示
     */
    @Test
    public void testHideCtrl() {
        AttendanceHideCtrlResponse response = openApi.attendanceHideCtrl(AttendanceHideCtrl
                .builder()
                .activityId("854f4861cbb840808fe97b4434b1e4f4")
                .isHide(true)
                .build());
        System.out.println(JSON.toJSONString(response));
        Assert.assertEquals(0, response.getState().getCode());
    }

    /**
     * 获取我的团队成员列表
     * mobile - 按手机号查找 (非必填)
     */
    @Test
    public void testTeamUsers() {
        TeamUserListResponse response = openApi.teamUsers(TeamUserList
                .builder()
                .mobile("13428888367")
                .build(), 1, 10);
        System.out.println(JSON.toJSONString(response));
        Assert.assertEquals(0, response.getState().getCode());
    }

    /**
     * 邀请团队成员
     * inviteNum - 邀请数量
     */
    @Test
    public void testInviteTeamUser() {
        InviteTeamUserResponse response = openApi.inviteTeamUser(InviteTeamUser
                .builder()
                .inviteNum(2)
                .build());
        System.out.println(JSON.toJSONString(response));
        Assert.assertEquals(0, response.getState().getCode());
    }
}
