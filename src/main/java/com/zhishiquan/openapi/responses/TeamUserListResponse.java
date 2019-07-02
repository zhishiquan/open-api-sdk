package com.zhishiquan.openapi.responses;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TeamUserListResponse extends Response {
    private TeamUSerListData data;
}
