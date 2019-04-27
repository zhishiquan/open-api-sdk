package com.zhishiquan.openapi.responses;

import com.zhishiquan.openapi.model.State;
import lombok.Data;

@Data
public class Response {
    private String id;

    private String serverTime;

    private State state;
}
