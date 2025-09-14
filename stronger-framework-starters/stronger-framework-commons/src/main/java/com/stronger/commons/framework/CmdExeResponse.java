package com.stronger.commons.framework;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author stronger
 * @version release-1.0.0
 * @class CmdExeResponse.class
 * @department Platform R&D
 * @date 2025/9/13
 * @desc 抽象命令执行结果
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public abstract class CmdExeResponse implements Serializable {

    /**
     * 业务真正执行器
     */
    private String cmdExe;
}
