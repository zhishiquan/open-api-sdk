## ZhiShiQuan OpenAPI SDK Document

### [文档](https://github.com/zhishiquan/open-api-sdk/wiki/%E7%9F%A5%E8%AF%86%E5%9C%88OpenApi%E5%BF%AB%E9%80%9F%E5%BC%80%E5%A7%8B)
### [Document](https://github.com/zhishiquan/open-api-sdk/wiki/ZhiShiQuan-Open-Api-Quickstart)


#### Quickstart:

```java
class OpenApiTest {
    /**
    * open api客户端 应缓存留着下次使用
    */
    private OpenApi openApi;
    
    /**
    * 初始化OpenApiClient
    */
    @Before
    public void setUp() {
        this.openApi = OpenApi.builder()
                            .clientId("your client id")
                            .clientSecret("your client secret")
                            .isSandbox(true)
                            .build();    
    }
    
    /**
    * 创建训练营
    */
    @Test
    public void testAttendanceCreate() { 
        Long now = System.currentTimeMillis();
        AttendanceCreateResponse response = openApi.createAttendance(AttendanceCreate
            .builder()
            .name("JL测试创建训练营2") // 训练营名称
            .creator("JL") // 发起人名称
            .startTime(now) // 课程开始时间
            .endTime(now + 3600000L * 24L * 30L) // 课程结束时间
            .failDay(2) // 允许失败天数
            .money(200) // 课程单价
            .ratio(50) // 课程奖金比例
            .mobile("13428888367") // 联系手机号码
            .build());
            System.out.println(JSON.toJSONString(response));
            Assert.assertEquals(0, response.getState().getCode());
        }
    
    /**
    * 隐藏训练营
    */
    @Test
    public void testHideCtrl() {
        AttendanceHideCtrlResponse response = openApi.attendanceHideCtrl(AttendanceHideCtrl
                .builder()
                .activityId("9cba96652cdb455d8bc7986783c69314") // 需要隐藏的课程id
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
```
