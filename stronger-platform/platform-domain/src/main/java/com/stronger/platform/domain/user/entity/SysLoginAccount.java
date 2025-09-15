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
 * 系统登录账户表
 * </p>
 *
 * @author stronger
 * @since 2025-09-15
 */
@Getter
@Setter
@ToString
@TableName("sys_login_account")
public class SysLoginAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "increment_id", type = IdType.AUTO)
    private Long incrementId;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 账号名称
     */
    private String accountName;

    /**
     * 密码
     */
    private String accountPassword;

    /**
     * 是否默认密码
     */
    private Boolean isDefaultPassword;

    /**
     * 密码设置时间
     */
    private LocalDateTime passwordSettingTime;

    /**
     * 账户类型(0.工号，1.手机号，2.邮箱，3.自定义)
     */
    private Boolean accountType;

    /**
     * 是否启用(is_enable:1.启用；0.禁用)
     */
    private Boolean isEnable;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 最后更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 删除标识is_deleted0.正常；1.已删除
     */
    private Boolean isDeleted;
}
