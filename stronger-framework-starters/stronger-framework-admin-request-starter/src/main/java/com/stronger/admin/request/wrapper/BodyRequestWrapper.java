package com.stronger.admin.request.wrapper;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.stronger.commons.base.BaseRequestAttributeEnums;
import com.stronger.commons.utils.StringUtils;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author stronger
 * @version release-1.0.0
 * @class BodyRequestWrapper.class
 * @department Platform R&D
 * @date 2025/9/15
 * @desc BodyRequestWrapper
 */
public class BodyRequestWrapper extends HttpServletRequestWrapper {

    public BodyRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    /**
     * 重写getInputStream方法
     */
    @Override
    public ServletInputStream getInputStream() throws IOException {
        //非json类型，直接返回
        if (!super.getHeader(HttpHeaders.CONTENT_TYPE).contains(MediaType.APPLICATION_JSON_VALUE)) {
            return super.getInputStream();
        }
        //从输入流中取出body串, 如果为空，直接返回
        StringBuilder sb = new StringBuilder();
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(super.getInputStream(), StandardCharsets.UTF_8));
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        String reqBodyStr = sb.toString();
        if (StringUtils.isEmpty(reqBodyStr)) {
            reqBodyStr = "{}";
        }
        //reqBodyStr转为Map对象
        Map<String, Object> paramMap = new ObjectMapper()
                .readValue(reqBodyStr, new TypeReference<HashMap<String, Object>>() {
                });
        //--------------- 这里结合geteway 封装的用户信息设置到请求参数中
        String frameUserId = super.getHeader(BaseRequestAttributeEnums.FRAME_USER_ID.getHeaderName());
        String frameUserName = super.getHeader(BaseRequestAttributeEnums.FRAME_USER_NAME.getHeaderName());
        String frameUserVersion = super.getHeader(BaseRequestAttributeEnums.FRAME_USER_VERSION.getHeaderName());
        String frameOrgCode = super.getHeader(BaseRequestAttributeEnums.FRAME_ORG_CODE.getHeaderName());
        String frameClientId = super.getHeader(BaseRequestAttributeEnums.FRAME_CLIENT_ID.getHeaderName());

        paramMap.put(BaseRequestAttributeEnums.FRAME_USER_ID.getType(), frameUserId);
        paramMap.put(BaseRequestAttributeEnums.FRAME_USER_NAME.getType(),
                StringUtils.isNotEmpty(frameUserName) ?
                        URLDecoder.decode(frameUserName, StandardCharsets.UTF_8) : null);
        paramMap.put(BaseRequestAttributeEnums.FRAME_USER_VERSION.getType(), frameUserVersion);
        paramMap.put(BaseRequestAttributeEnums.FRAME_ORG_CODE.getType(), frameOrgCode);
        paramMap.put(BaseRequestAttributeEnums.FRAME_CLIENT_ID.getType(), frameClientId);
        //--------------- 获取header参数设置到body中
        // 去首尾空格
        // Map<String,Object> trimedMap = recursiveTrim(paramMap);
        // 重新构造一个输入流对象
        byte[] bytes = new Gson().toJson(paramMap).getBytes(StandardCharsets.UTF_8);
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        return new MyServletInputStream(bis);
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

}
