package com.zhishiquan.openapi.view;

import lombok.Data;

/**
 * @author liuyu.lu
 * @since 26/11/2018
 */
@Data
public class UserLessView {

    private Long id;
    private Long userId;
    private String nickName;
    private String bRemark;
    private String cRemark;
    private String headImgUrl;
    private Boolean isManager;
    private String mobile;

}
