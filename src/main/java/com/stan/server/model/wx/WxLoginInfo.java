package com.stan.server.model.wx;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonProperty;

@Data
public class WxLoginInfo {
    private String openid;
    @JsonProperty("session_key")
    private String sessionKey;
    private String unionid;
    private Integer errcode;
    private String errmsg;
}
