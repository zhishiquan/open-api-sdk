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
    
    @Before
    public void setUp() {
        this.openApi = OpenApi.builder()
                            .clientId("your client id")
                            .clientSecret("your client secret")
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
