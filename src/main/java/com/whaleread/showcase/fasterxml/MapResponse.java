package com.whaleread.showcase.fasterxml;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dolphin on 2017/11/28
 */
@Getter
@Setter
@NoArgsConstructor
public class MapResponse {
    @JsonAnySetter
    private Map<String, String> data = new HashMap<>();
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int code;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("err")
    private String error;

    public MapResponse(Map<String, String> data) {
        this.data = data;
    }

    public MapResponse(int code, String error) {
        this.code = code;
        this.error = error;
    }

    @JsonAnyGetter
    public Map<String, String> any() {
        return data;
    }

//    @JsonAnySetter
//    public void any(String name, Object value) {
//        data.put(name, String.valueOf(value));
//    }
}
