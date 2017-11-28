package com.whaleread.showcase.fasterxml;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Dolphin on 2017/11/28
 */
@Getter
@Setter
@JsonRootName("xml")
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    @JsonProperty("From")
    private String from;

    @JsonProperty("To")
    private String to;

    @JsonProperty("MsgId")
    private String msgId;
}
