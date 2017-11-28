package com.whaleread.showcase.fasterxml;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Dolphin on 2017/11/28
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TextMessage extends Message {
    @JacksonXmlCData
    @JsonProperty("Content")
    private String content;
}
