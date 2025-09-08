package com.stronger.log;


/**
 * @author stronger
 * @version release-1.0.0
 * @enum BusinessType.class
 * @department Platform R&D
 * @date 2025/9/8
 * @desc 业务操作类型
 */
public enum BusinessType {
    /**
     * 查询
     */
    QUERY,

    /**
     * 其它
     */
    OTHER,

    /**
     * 新增
     */
    INSERT,

    /**
     * 修改
     */
    UPDATE,

    /**
     * 删除
     */
    DELETE,

    /**
     * 授权
     */
    GRANT,

    /**
     * 导出
     */
    EXPORT,

    /**
     * 导入
     */
    IMPORT,

    /**
     * 强退
     */
    FORCE,

    /**
     * 生成代码
     */
    GENCODE,

    /**
     * 清空数据
     */
    CLEAN;
}
