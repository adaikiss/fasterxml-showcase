package com.whaleread.showcase.fasterxml;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.Test;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * Created by Dolphin on 2017/11/28
 */
public class XmlTest {
    @Test
    public void serialize() throws Exception {
        XmlMapper xmlMapper = new XmlMapper();
        TextMessage msg = new TextMessage();
        msg.setFrom("Jack");
        msg.setTo("Jackson");
        msg.setMsgId("10000001");
        msg.setContent("Hello!");
        String text = xmlMapper.writeValueAsString(msg);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setNamespaceAware(false);
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document xml = documentBuilder.parse(new ByteArrayInputStream(text.getBytes()));
        assertThat(xml, allOf(
                hasXPath("/xml/From", equalTo("Jack")),
                hasXPath("/xml/To", equalTo("Jackson")),
                hasXPath("/xml/MsgId", equalTo("10000001")),
                hasXPath("/xml/Content", equalTo("Hello!")))
        );
    }

    @Test
    public void deserialize() throws Exception {
        XmlMapper xmlMapper = new XmlMapper();
        String xml = "<xml><Content><![CDATA[Hello!]]></Content><From>Jack</From><To>Jackson</To><MsgId>10000001</MsgId></xml>";
        TextMessage msg = xmlMapper.readValue(xml, TextMessage.class);
        assertThat(msg, allOf(
                hasProperty("from", equalTo("Jack")),
                hasProperty("to", equalTo("Jackson")),
                hasProperty("msgId", equalTo("10000001")),
                hasProperty("content", equalTo("Hello!")))
        );
    }
}
