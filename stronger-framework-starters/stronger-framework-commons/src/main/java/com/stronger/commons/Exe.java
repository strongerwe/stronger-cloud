package com.stronger.commons;


/**
 * @author stronger
 * @version release-1.0.0
 * @interface Exe.class
 * @department Platform R&D
 * @date 2025/8/7
 * @desc  Cola中有大量的Cmd活着Qry的Exe存在  特此定义一个interface Exe 并通过E R严格控制入参和回调
 */
public interface Exe<E, R> {

    /**
     * execute
     * @param e enter entity
     * @return Return
     */
    R execute(E e);
}
