package com.zhishiquan.openapi.requests;

import com.zhishiquan.openapi.model.PageEntity;
import com.zhishiquan.openapi.model.TeamUserList;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TeamUserListRequest extends Request {

    private TeamUserList data;

    private PageEntity page;
}
