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
 * 网关路由信息
 * </p>
 *
 * @author stronger
 * @since 2025-09-12
 */
@Getter
@Setter
@ToString
@TableName("gateway_dynamic_route")
public class GatewayDynamicRoute implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * routeId
     */
    private String routeId;

    /**
     * 注册中心服务注册名
     */
    private String application;

    /**
     * 路由开头
     */
    private String pathFront;

    /**
     * 路由规则表达式
     */
    private String pathPattern;

    /**
     * 是否启用|pf_is_enable|1:启用，0:禁用
     */
    private Boolean isEnable;

    /**
     * 路由规则来源||0.系统添加；1.系统默认（默认不可编辑）
     */
    private Boolean source;

    /**
     * 服务发现
     */
    private String serviceDiscovery;

    /**
     * 备注说明
     */
    private String remarks;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 最后操作人
     */
    private String lastModifier;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 是否删除(0.未删除,1.已删除,)
     */
    private Boolean isDeleted;
}
