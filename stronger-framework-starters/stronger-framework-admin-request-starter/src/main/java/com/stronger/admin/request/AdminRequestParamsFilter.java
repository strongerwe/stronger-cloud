package com.stronger.admin.request;


import com.stronger.admin.request.wrapper.BodyRequestWrapper;
import com.stronger.admin.request.wrapper.ParameterRequestWrapper;
import com.stronger.commons.base.BaseRequestAttributeEnums;
import com.stronger.commons.utils.StringUtils;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpMethod;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * @author stronger
 * @version release-1.0.0
 * @class AdminRequestParamsFilter.class
 * @department Platform R&D
 * @date 2025/9/15
 * @desc AdminRequestParamsFilter
 */
public class AdminRequestParamsFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        ParameterRequestWrapper trimReqParamWrapper = createWrapper(request);
        if (null != trimReqParamWrapper) {
            if (!HttpMethod.GET.name().equals(request.getMethod())) {
                BodyRequestWrapper bodyRequestWrapper = new BodyRequestWrapper(trimReqParamWrapper);
                filterChain.doFilter(bodyRequestWrapper, servletResponse);
                return;
            }
            filterChain.doFilter(trimReqParamWrapper, servletResponse);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    /**
     * 封装请求入参
     *
     * @param request request
     * @return {@link ParameterRequestWrapper }
     * @throws IOException e
     */
    private ParameterRequestWrapper createWrapper(HttpServletRequest request) throws IOException {
        ParameterRequestWrapper trimReqParamWrapper = null;
        String frameUserId = request.getHeader(BaseRequestAttributeEnums.FRAME_USER_ID.getHeaderName());
        String frameUserName = request.getHeader(BaseRequestAttributeEnums.FRAME_USER_NAME.getHeaderName());
        String frameUserVersion = request.getHeader(BaseRequestAttributeEnums.FRAME_USER_VERSION.getHeaderName());
        String frameOrgCode = request.getHeader(BaseRequestAttributeEnums.FRAME_ORG_CODE.getHeaderName());
        String frameClientId = request.getHeader(BaseRequestAttributeEnums.FRAME_CLIENT_ID.getHeaderName());
        if (StringUtils.isNotEmpty(frameUserId)
                || StringUtils.isNotEmpty(frameUserName)
                || StringUtils.isNotEmpty(frameUserVersion)
                || StringUtils.isNotEmpty(frameOrgCode)
                || StringUtils.isNotEmpty(frameClientId)) {
            trimReqParamWrapper = new ParameterRequestWrapper(request);
            trimReqParamWrapper.addParameter(BaseRequestAttributeEnums.FRAME_USER_ID.getType(),
                    StringUtils.isEmpty(frameUserId) ? null : frameUserId);
            trimReqParamWrapper.addParameter(BaseRequestAttributeEnums.FRAME_USER_NAME.getType(),
                    StringUtils.isEmpty(frameUserName) ? null :
                            URLDecoder.decode(frameUserName, StandardCharsets.UTF_8));
            trimReqParamWrapper.addParameter(BaseRequestAttributeEnums.FRAME_USER_VERSION.getType(),
                    StringUtils.isEmpty(frameUserVersion) ? null : frameUserVersion);
            trimReqParamWrapper.addParameter(BaseRequestAttributeEnums.FRAME_ORG_CODE.getType(),
                    StringUtils.isEmpty(frameOrgCode) ? null : frameOrgCode);
            trimReqParamWrapper.addParameter(BaseRequestAttributeEnums.FRAME_CLIENT_ID.getType(),
                    StringUtils.isEmpty(frameClientId) ? null : frameClientId);
        }
        return trimReqParamWrapper;
    }
}
