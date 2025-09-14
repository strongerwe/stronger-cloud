package com.stronger.platform.client.admin.frame.response;


import com.stronger.commons.framework.CmdExeResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author stronger
 * @version release-1.0.0
 * @class LoginResponse.class
 * @department Platform R&D
 * @date 2025/9/13
 * @desc do what?
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class LoginResponse extends CmdExeResponse {
    /**
     * token
     */
    private String accessToken;

    /**
     * token有效期
     */
    private Long accessExp;

    public LoginResponse(String accessToken, Long accessExp, String cmdExe) {
        super(cmdExe);
        this.accessToken = accessToken;
        this.accessExp = accessExp;
    }
}
