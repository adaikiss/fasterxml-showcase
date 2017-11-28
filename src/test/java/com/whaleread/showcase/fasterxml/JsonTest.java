package com.whaleread.showcase.fasterxml;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static com.jayway.jsonpath.matchers.JsonPathMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * Created by Dolphin on 2017/11/28
 */
public class JsonTest {
    @Test
    public void serializeUnwrapped() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        TokenResponse token = new TokenResponse("mock_token", 2700);
        Response<TokenResponse> response = new Response<>(token);
        String json = objectMapper.writeValueAsString(response);
        assertThat(json, isJson(allOf(
                withJsonPath("$.access_token", equalTo("mock_token")),
                withJsonPath("$.expires_in", equalTo(2700)),
                withoutJsonPath("$.code"),
                withoutJsonPath("$.error"),
                withoutJsonPath("$.data")
        )));
    }

    @Test
    public void deserializeUnwrapped() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "{\"access_token\":\"mock_token\",\"expires_in\":2700}";
        Response<TokenResponse> response = objectMapper.readValue(json, new TypeReference<Response<TokenResponse>>() {
        });
        assertThat(response, allOf(hasProperty("code", is(0)), hasProperty("error", nullValue())));
        assertThat(response.getData(), allOf(
                hasProperty("accessToken", is("mock_token")),
                hasProperty("expiresIn", is(2700))
        ));
    }

    @Test
    public void serializeMapAny() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> data = new HashMap<>();
        data.put("access_token", "mock_token");
        data.put("expires_in", "2700");
        MapResponse response = new MapResponse(data);
        String json = objectMapper.writeValueAsString(response);
        assertThat(json, isJson(allOf(
                withJsonPath("$.access_token", equalTo("mock_token")),
                withJsonPath("$.expires_in", equalTo("2700")),
                withoutJsonPath("$.code"),
                withoutJsonPath("$.error"),
                withoutJsonPath("$.data")
        )));
    }

    @Test
    public void deserializeMapAny() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "{\"access_token\":\"mock_token\",\"expires_in\":2700}";
        MapResponse response = objectMapper.readValue(json, MapResponse.class);
        assertThat(response, allOf(hasProperty("code", is(0)), hasProperty("error", nullValue())));
        assertThat(response.getData(), allOf(
                hasEntry("access_token", "mock_token"),
                hasEntry("expires_in", "2700")
        ));
    }
}
