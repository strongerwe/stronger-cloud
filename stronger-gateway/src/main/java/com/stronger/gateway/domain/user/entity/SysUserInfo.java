package com.stronger.gateway.domain.user.entity;

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
 * 系统用户信息表
 * </p>
 *
 * @author stronger
 * @since 2025-09-12
 */
@Getter
@Setter
@ToString
@TableName("sys_user_info")
public class SysUserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "increment_id", type = IdType.AUTO)
    private Long incrementId;

    /**
     * 用户uuid
     */
    private String userId;

    /**
     * 用户版本号
     */
    private Integer userVersion;

    /**
     * 用户姓名
     */
    private String realName;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 是否启用(is_enable:1.启用；0.禁用)
     */
    private Boolean isEnable;

    /**
     * 性别(sys_sex:0.未知,1.女,2.男)
     */
    private Byte sex;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱地址
     */
    private String email;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建者
     */
    private String creator;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 更新者
     */
    private String lastModifier;

    /**
     * 删除标识(is_deleted:0.正常；1.已删除)
     */
    private Boolean isDeleted;
}
