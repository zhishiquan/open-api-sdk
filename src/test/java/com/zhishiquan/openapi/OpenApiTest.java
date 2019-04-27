package com.zhishiquan.openapi;

import com.alibaba.fastjson.JSON;
import com.zhishiquan.openapi.core.OpenApi;
import com.zhishiquan.openapi.model.AttendanceCreate;
import com.zhishiquan.openapi.model.AttendanceHideCtrl;
import com.zhishiquan.openapi.responses.AttendanceCreateResponse;
import com.zhishiquan.openapi.responses.AttendanceHideCtrlResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
                .clientId("7OivOtcc7dBdyvi9")
                .clientSecret("4JLvRaT4sOYqHlJ1j2NVGZm0oLfWVq4U")
                .isSandbox(false)
                .build();
    }

    /**
     * 测试创建训练营
     * name
     */
    @Test
    public void testAttendanceCreate() {
        Long now = System.currentTimeMillis();
        AttendanceCreateResponse response = openApi.createAttendance(AttendanceCreate
                .builder()
                .name("JL测试创建训练营4")
                .creator("JL")
                .startTime(now)
                .endTime(now + 3600000L * 24L * 30L)
                .failDay(2)
                .money(200)
                .ratio(50)
                .mobile("13428888367")
                .build());
        System.out.println(JSON.toJSONString(response));
        Assert.assertEquals(0, response.getState().getCode());
    }

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
}
