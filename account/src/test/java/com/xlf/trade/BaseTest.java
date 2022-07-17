package com.xlf.trade;

import com.xlf.common.exception.ErrorCodeEnum;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;

@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class BaseTest {
    protected String userId1 = "U1657678518703";
    protected String userId2 = "U1657711042817";

    private MockMvc mockMvc;

    @Resource
    private WebApplicationContext context;

    @Before
    public void initMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    public String get(String url, Map<String, String> map) throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(url)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED);
        if (map != null) {
            map.forEach(requestBuilder::param);
        }
        return executeRequestBuilder(requestBuilder);
    }

    public String post(String url, MultiValueMap<String, String> params) throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(url)
                .accept(MediaType.APPLICATION_JSON_VALUE);
        if (Objects.nonNull(params) && params.size() > 0) {
            requestBuilder.params(params);
        }

        return executeRequestBuilder(requestBuilder, ErrorCodeEnum.SUCCESS.getCode());
    }

    public String postJson(String url, String json) throws Exception {
        return postJson(url, json, ErrorCodeEnum.SUCCESS.getCode());
    }

    public String postJson(String url, String json, int expectCode) throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(json);
        return executeRequestBuilder(requestBuilder, expectCode);
    }

    private String executeRequestBuilder(RequestBuilder requestBuilder) throws Exception {
        return mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    private String executeRequestBuilder(RequestBuilder requestBuilder, int expectCode) throws Exception {
        return mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(expectCode))
                .andReturn()
                .getResponse()
                .getContentAsString();
    }
}
