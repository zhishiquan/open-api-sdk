package com.zhishiquan.openapi.responses;

import lombok.Data;

/**
 * 创建训练营返回数据
 * @author jl
 */
@Data
public class AttendanceCreateData {
    /**
     * 训练营id
     */
    private String activityId;

    /**
     * h5的url
     */
    private String h5Url;

    /**
     * 小程序的path
     */
    private String miniUrl;

    /**
     * 小程序码url
     */
    private String miniQrCodeUrl;
}
