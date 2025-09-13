package com.stronger.gateway.domain.route.entity;

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
 * 开放apiPath信息
 * </p>
 *
 * @author stronger
 * @since 2025-09-12
 */
@Getter
@Setter
@ToString
@TableName("gateway_open_path")
public class GatewayOpenPath implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * show|主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * show|注册中心服务注册名
     */
    private String application;

    /**
     * show|接口名称
     */
    private String interfaceName;

    /**
     * show|请求路径
     */
    private String pathUrl;

    /**
     * show|备注说明||
     */
    private String remarks;

    /**
     * show|是否启用|pf_is_enable|1:是，0:否
     */
    private Boolean isEnable;

    /**
     * 创建者
     */
    private String creator;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新者
     */
    private String lastModifier;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * show|逻辑删除||0.未删除1.已删除
     */
    private Boolean isDeleted;
}
