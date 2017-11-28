package com.whaleread.showcase.fasterxml;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Dolphin on 2017/11/28
 */
@Getter
@Setter
public class Response<D> {
    @JsonUnwrapped
    private D data;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int code;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("err")
    private String error;

    public Response() {
    }

    public Response(D data) {
        this.data = data;
    }

    public Response(int code, String error) {
        this.code = code;
        this.error = error;
    }
}
