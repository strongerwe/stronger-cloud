package com.stronger.admin.request.wrapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author stronger
 * @version release-1.0.0
 * @class AdminRequestFilterAutoConfiguration.class
 * @department Platform R&D
 * @date 2025/9/15
 * @desc RequestParamsWrapper
 */
public class ParameterRequestWrapper extends HttpServletRequestWrapper {

    private final Map<String, String[]> params = new HashMap<String, String[]>();


    public ParameterRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        this.params.putAll(request.getParameterMap());
    }

    /**
     * 重载一个构造方法
     *
     * @param request      request
     * @param extendParams extendParams
     */
    public ParameterRequestWrapper(HttpServletRequest request, Map<String, String[]> extendParams) throws IOException {
        this(request);
        addAllParameters(extendParams);
    }

    @Override
    public Enumeration<String> getParameterNames() {
        return Collections.enumeration(params.keySet());
    }

    @Override
    public String getParameter(String name) {
        String[] values = params.get(name);
        if (values == null || values.length == 0) {
            return null;
        }
        return values[0];
    }

    @Override
    public String[] getParameterValues(String name) {
        return params.get(name);
    }

    public void addAllParameters(Map<String, String[]> otherParams) {
        for (Map.Entry<String, String[]> entry : otherParams.entrySet()) {
            addParameter(entry.getKey(), entry.getValue());
        }
    }


    public void addParameter(String name, Object value) {
        if (value != null) {
            if (value instanceof String[]) {
                params.put(name, (String[]) value);
            } else if (value instanceof String) {
                params.put(name, new String[]{(String) value});
            } else {
                params.put(name, new String[]{String.valueOf(value)});
            }
        }
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return this.params;
    }
}
