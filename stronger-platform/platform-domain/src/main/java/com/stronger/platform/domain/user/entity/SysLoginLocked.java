package com.stronger.platform.domain.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 登录账户锁定信息表
 * </p>
 *
 * @author stronger
 * @since 2025-09-17
 */
@Getter
@Setter
@ToString
@TableName("sys_login_locked")
public class SysLoginLocked implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "increment_id", type = IdType.AUTO)
    private Long incrementId;

    /**
     * 用户UUID
     */
    private String userId;

    /**
     * 锁定状态
     */
    private Boolean locked;

    /**
     * 登录错误次数
     */
    private Integer loginFailedCount;

    /**
     * 登录失败时间
     */
    private LocalDateTime loginFailedTime;

    /**
     * 最后失败时间
     */
    private LocalDateTime lastFailedTime;
}
