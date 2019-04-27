## 知识圈开放API SDK文档

#### Usage:

```java
class OpenApiTest {
    /**
    * open api客户端 应缓存留着下次使用
    */
    private OpenApi openApi;
    
    @Before
    public void setUp() {
        this.openApi = OpenApi.builder()
                            .clientId("client_1")
                            .clientSecret("jl")
                            .isSandbox(true)
                            .build();    
    }
        
    @Test
    public void testAttendanceCreate() { 
        Long now = System.currentTimeMillis();
        AttendanceCreateResponse response = openApi.createAttendance(AttendanceCreate
            .builder()
            .name("JL测试创建训练营2")
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
                    .activityId("9cba96652cdb455d8bc7986783c69314")
                    .isHide(true)
                    .build());
            System.out.println(JSON.toJSONString(response));
            Assert.assertEquals(0, response.getState().getCode());
        }
}
```
