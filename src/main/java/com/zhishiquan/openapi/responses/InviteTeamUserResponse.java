package com.zhishiquan.openapi.responses;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class InviteTeamUserResponse extends Response {
    private InviteTeamUserData data;
}
