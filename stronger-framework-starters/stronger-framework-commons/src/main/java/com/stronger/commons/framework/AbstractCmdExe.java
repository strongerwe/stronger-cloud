package com.stronger.commons.framework;


import com.stronger.commons.Exe;

/**
 * @author stronger
 * @version release-1.0.0
 * @class AbstractCmdExe.class
 * @department Platform R&D
 * @date 2025/9/14
 * @desc cmd执行器抽象类
 */
public abstract class AbstractCmdExe<E, R> implements Exe<E, R> {

    @Override
    public R execute(E e) {
        validate(e);
        return cmdExecute(e);
    }

    /**
     * 真正执行器
     *
     * @param e 入参
     * @return {@link R }
     */
    protected abstract R cmdExecute(E e);

    /**
     * 校验入参
     *
     * @param e 入参
     */
    protected abstract void validate(E e);
}
