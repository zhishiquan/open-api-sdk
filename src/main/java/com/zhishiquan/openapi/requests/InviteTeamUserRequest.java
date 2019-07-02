package com.zhishiquan.openapi.requests;

import com.zhishiquan.openapi.model.InviteTeamUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class InviteTeamUserRequest extends Request {

    private InviteTeamUser data;
}
