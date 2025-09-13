package com.stronger.commons.base;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author stronger
 * @version release-1.0.0
 * @class BaseRequest.class
 * @department Platform R&D
 * @date 2025/9/6
 * @desc BaseRequest
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class BaseRequest implements Serializable {

    /**
     * 登录客户端ID
     */
    private String frameClientId;

    /**
     * 登录用户ID
     */
    private String frameUserId;

    /**
     * 登录用户名
     */
    private String frameUserName;

    /**
     * 登录用户版本号
     */
    private Integer frameUserVersion;

    /**
     * 登录机构编码
     */
    private String frameOrgCode;
}
